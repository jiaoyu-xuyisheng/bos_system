package com.jiaoyu.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiaoyu.bos.domain.Role;
import com.jiaoyu.service.IRoleService;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	
	@Autowired
	private IRoleService roleService;
	private String ids;
	
	public void setIds(String ids) {
		this.ids = ids;
	}

	public String add() throws Exception{
		roleService.save(model,ids);
		return LIST;
	}
	
	public String pageQuery() throws Exception{
		roleService.pageQuery(pagebean);
		this.java2Json(pagebean, new String[] {"users","functions"});
		return NONE;
	}
	
	public String listAjax() {
		List<Role> list=roleService.findAll();
		this.java2Json(list, new String[] {"users","functions"});
		return NONE;
	}
	

}
