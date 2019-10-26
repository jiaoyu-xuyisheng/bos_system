package com.jiaoyu.web.action;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import com.jiaoyu.bos.domain.Staff;
import com.jiaoyu.bos.domain.pageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	public static final String LIST="list";
	 public static final String HOME = "home";
	 
	 //声明pageBean对象！！为以后注入属性做准备。
	 protected 	pageBean pagebean=new pageBean();
	 
	public void setPage(Integer page) {
		//注入page
		pagebean.setCurrentPage(page);
	}
	public void setRows(Integer rows) {
		//注入rows
		pagebean.setPageSize(rows);
	}
	
	public void java2Json(Object o,String[] mystring) {
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(mystring);
		String json = JSONObject.fromObject(o, jsonConfig).toString();
		//将json写到页面中;
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void java2Json(List list,String[] mystring) {
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(mystring);
		String json = JSONArray.fromObject(list, jsonConfig).toString();
		//将json写到页面中;
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void setModel(T model) {
		this.model = model;
	}
	//对象类型T 和model对象; 
	protected T model;
	//对象封装！！！
	@Override
	public T getModel() {
		
		return model;
	}
	@SuppressWarnings("all")
	public BaseAction(){
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class clazz = (Class) superclass.getActualTypeArguments()[0];
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		pagebean.setDetachedCriteria(dc);
		try {
			model = (T) clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	
	}
	
	
}
