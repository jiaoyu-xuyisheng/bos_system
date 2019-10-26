package com.jiaoyu.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiaoyu.bos.domain.Region;
import com.jiaoyu.bos.domain.Staff;
import com.jiaoyu.bos.domain.pageBean;
import com.jiaoyu.service.IRegionService;

import com.jiaoyu.utils.PinYin4jUtils;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
	
	private File regionFile;
	
	@Autowired
	private IRegionService regionService;

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}
	
	/**
	 * 
	* @Title: uploadXML  
	* @Description: readMLS File and save region Object;
	* @param @return
	* @param @throws Exception    参数  
	* @return String    返回类型  
	* @throws
	 */
	public String uploadXML() throws Exception{
		List<Region> regionList=new ArrayList<Region>();
		HSSFWorkbook workbook=new HSSFWorkbook(new FileInputStream(regionFile));
		HSSFSheet sheet = workbook.getSheetAt(0);
		//封装通过读取 sheet中的每一行中的数据,
		//和使用pinyin4j来封装region对象！！
		//然后将其添加到regionList集合中。
		for (Row row : sheet) {
			int rowNum = row.getRowNum();
			if(rowNum==0) {
				continue;
			}
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			//包装成一区域对象！！
			Region region=new Region(id, province, city, district, postcode, null, null, null);
			//去掉省市区的最后一个字;
			province = province.substring(0,province.length()-1);
			city = city.substring(0,city.length()-1);
			district = district.substring(0,district.length()-1);
			String info=province+city+district;
			String[] headByString = PinYin4jUtils.getHeadByString(info);
			//得到简码！！
			String shotCode = StringUtils.join(headByString);
			String cityCode = PinYin4jUtils.hanziToPinyin(city,"");
			region.setCitycode(cityCode);
			region.setShortcode(shotCode);
			regionList.add(region);
		}
		
		//调用 regionService对象在sevice层处理保存操作！
		regionService.saveList(regionList);
		return NONE;
		
	}
	
	
	public String pageQuery() throws Exception{			
				//得到有数据的pagebean;
				regionService.pageQuery(pagebean);			
				this.java2Json(pagebean,new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
					
				return NONE;
	}
	
	
	private String q;
	
	public String selRegion() throws Exception{
		List<Region> list =null;
		if(StringUtils.isNotBlank(q)) {
		list=regionService.findListByQ(q);
		}else {
		list=regionService.findAll();
		}
		this.java2Json(list, new String[] {"subareas"});
		return NONE;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
	
	
	
}
