package com.jiaoyu.web.action;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiaoyu.bos.domain.Staff;
import com.jiaoyu.bos.domain.pageBean;
import com.jiaoyu.service.IStaffService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{
	@Autowired
	private IStaffService staffService;
	
	
	
	public String save() throws Exception{
		staffService.save(model);
		return LIST;
	}
	
	public String getList() throws Exception{
		staffService.pageQuery(pagebean);
		this.java2Json(pagebean,new String[] {"currentPage","detachedCriteria","pageSize","decidedzones"});	
		return NONE;
	}
	
	public String edit() throws Exception{
		Staff staff = staffService.getById(model.getId());
		staff.setDeltag(model.getDeltag());
		staff.setHaspda(model.getHaspda());
		staff.setName(model.getName());
		staff.setStandard(model.getStandard());
		staff.setTelephone(model.getTelephone());
		staff.setStation(model.getStation());
		staffService.update(staff);
		
		return LIST;
	}
	
	
	private String ids;
	
	@RequiresPermissions("staff-delete")
	public String delStaff() {
		staffService.delStaff(ids);
		return LIST;
	}
	
	
	public String listajax() {
		List<Staff> list=staffService.findListNotDel();
		this.java2Json(list, new String[] {"decidedzones"});
		return NONE;
	}
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	
	
	

	
	
	
	
	

}
