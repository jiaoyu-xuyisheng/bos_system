package com.jiaoyu.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiaoyu.bos.domain.Region;
import com.jiaoyu.bos.domain.Subarea;
import com.jiaoyu.service.ISubareaService;
import com.jiaoyu.utils.FileUtils;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	
	@Autowired
	private ISubareaService subareaService;
	private String decidedzoneId;
	
	
	public void setDecidedzoneId(String decidedzoneId) {
		this.decidedzoneId = decidedzoneId;
	}

	public String add() throws Exception{
		subareaService.save(model);
		return LIST;
	}
	
	public String findListByDecidedzoneId() throws Exception{
		List<Subarea> list=subareaService.findListByDecidedzoneId(decidedzoneId);
		this.java2Json(list, new String[] {"decidedzone","subareas"});
		return NONE;
	}
	
	public String pageQuery() throws Exception{
		DetachedCriteria dc = pagebean.getDetachedCriteria();
		String addresskey = model.getAddresskey();
		if(StringUtils.isNotBlank(addresskey)) {
			dc.add(Restrictions.like("addresskey","%"+addresskey+"%"));
		}
		Region region = model.getRegion();
		if(region!=null) {
			String city = region.getCity();
			String province = region.getProvince();
			String district = region.getDistrict();
			dc.createAlias("region", "r");
			if(StringUtils.isNotBlank(province)) {
				dc.add(Restrictions.like("r.province", "%"+province+"%"));
			}
			if(StringUtils.isNotBlank(city)) {
				dc.add(Restrictions.like("r.city",  "%"+city+"%"));
			}
			if(StringUtils.isNotBlank(district)) {
				dc.add(Restrictions.like("r.district",  "%"+district+"%"));
			}
		}
		subareaService.pageQuery(pagebean);
		this.java2Json(pagebean,new String[] {"currentPage","detachedCriteria","pageSize","decidedzone","subareas"});	
		return NONE;
	}

	
	@SuppressWarnings("resource")
	public String loadXls() throws IOException {
		List<Subarea> list=subareaService.findAll();	
		//create a excel file;
		HSSFWorkbook workbook=new HSSFWorkbook();
		//create a sheet page!!
		HSSFSheet sheet = workbook.createSheet();
		//create a row for a header;
		HSSFRow header = sheet.createRow(0);
		//create cell and  set cell value;
		header.createCell(0).setCellValue("分区编号");
		header.createCell(1).setCellValue("开始编号");
		header.createCell(2).setCellValue("结束编号");
		header.createCell(3).setCellValue("位置编号");
		header.createCell(4).setCellValue("省市区");		
		//fill the subarea date in the dategrid;
		for (Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());		
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(3).setCellValue(subarea.getPosition());
			dataRow.createCell(4).setCellValue(subarea.getRegion().getName());			
		}
		//the step of 3  
		//use outPutStream to load file;
		String filename="分区数据.xls";
		//you can get a contenType of you file that use to set contentType;				
		String contentType=ServletActionContext.getServletContext().getMimeType(filename);
		ServletActionContext.getResponse().setContentType(contentType);
		//you can get a outPutStream;		
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		//get you clientWeb type;
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		filename=FileUtils.encodeDownloadFilename(filename, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		workbook.write(out);		
		return NONE;
	}
	
	public String listajax() {
		List<Subarea>  list=subareaService.findListNotAssociation();
		this.java2Json(list, new String[] {"decidedzone","region"});		
		return NONE;
	}
}

