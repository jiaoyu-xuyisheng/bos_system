package com.jiaoyu.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiaoyu.bos.domain.Function;
import com.jiaoyu.service.IFunctionService;

@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {
	
	@Autowired
	private IFunctionService functionService;
	
	public String functionlistAjax() throws Exception{
		List<Function> list=functionService.findAll();
		this.java2Json(list,new String[] {"parentFunction","roles"});
		return NONE;
	}
	
	public String add() throws Exception{
		functionService.save(model);
		return LIST;
	}
	
	public String pageQuery() {
		String page = model.getPage();
		pagebean.setCurrentPage(Integer.parseInt(page));
		functionService.pageQuery(pagebean);
		this.java2Json(pagebean,new String[] {"children","roles","parentFunction"});
		return NONE;
	}
	
	public String findMenu() {
		List<Function> list=functionService.findMenu();
		this.java2Json(list, new String[] {"children","roles","parentFunction"});
		return NONE;
	}
}
