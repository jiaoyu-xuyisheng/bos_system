package com.jiaoyu.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.jiaoyu.bos.domain.User;

public class BOSUtils {
	
	//获取session对象！！
	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	public static User getLoginUser() {
		return (User) getSession().getAttribute("user");
	}

}
