package com.jiaoyu.web.action;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiaoyu.bos.domain.User;
import com.jiaoyu.service.IUserService;
import com.jiaoyu.utils.BOSUtils;
import com.jiaoyu.utils.MD5Utils;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	private String checkcode;
	
	@Autowired
	private  IUserService userService;
	
	private String[] roleids;
	
	
	
	
	
	public void setRoleids(String[] roleids) {
		this.roleids = roleids;
	}
	
	public String pageQuery() {
		userService.pagQuery(pagebean);
		this.java2Json(pagebean, new String[] {"noticebills","roles"});
		
		return NONE;
	}
	public String adduser() {
		userService.add(model,roleids);
		return LIST;
	}
	/**
	 * 
	* @Title: login  
	* @Description: 这是一个登录方法！！  
	* @param @return
	* @param @throws Exception    参数  
	* @return String    返回类型  
	* @throws
	 */
	public String login() throws Exception {
		//得到保存在session中的验证码！！
		String validatecode=(String)ServletActionContext.getRequest().getSession().getAttribute("key");
		if(StringUtils.isNotBlank(checkcode)&&checkcode.equals(validatecode)) {
			//使用shiro框架提供的方式进行认证操作
			//得到未认证的对象;
			Subject subject = SecurityUtils.getSubject();
			//将参数传给UsernamePasswordToken
			AuthenticationToken token = new UsernamePasswordToken(model.getPassword(),MD5Utils.md5(model.getUsername()));
			try {
				subject.login(token);
			} catch (Exception e) {
				e.printStackTrace();
				return LOGIN;
			}
		User user=	(User) subject.getPrincipal();
		ServletActionContext.getRequest().getSession().setAttribute("user", user);
		return HOME;
		}else {
			this.addActionError("输入的验证码错误！！");
			return LOGIN;
		}
		
	}
	
	
	public String regist() throws Exception{
		model.setPassword(MD5Utils.md5(model.getPassword()));
		userService.save(model);
		
		return LOGIN;
	}
	//注销用户！！！
	public String logout() {
		ServletActionContext.getRequest().getSession().setAttribute("user", null);
		return LOGIN;
	}
	
	public String editPassword() throws IOException{
		String f="1";
		String psd=MD5Utils.md5(model.getPassword());
		User u=BOSUtils.getLoginUser();
		try {
			userService.editPassword(u.getId(),psd);
		} catch (Exception e) {
			f="0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(f);
		return NONE;
	}
	
	
	

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
}
