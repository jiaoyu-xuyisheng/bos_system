package com.jiaoyu.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiaoyu.bos.domain.Workordermanage;
import com.jiaoyu.bos.domain.pageBean;
import com.jiaoyu.service.IWorkordermanageService;

@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {
	@Autowired
	private IWorkordermanageService workordermanageService;
	
	
	public String add() throws Exception {
		workordermanageService.add(model);
		return NONE;
	}
	
	public String delete() throws Exception {
		workordermanageService.delete(model);
		return NONE;
	}
	
	public String pageQuery() throws Exception{
		workordermanageService.pageQuery(pagebean);
		this.java2Json(pagebean,new String[] {"currentPage","detachedCriteria","pageSize","decidedzones"});	
		return NONE;
	}

}
