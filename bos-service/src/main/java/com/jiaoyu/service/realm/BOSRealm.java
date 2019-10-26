package com.jiaoyu.service.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiaoyu.bos.domain.Function;
import com.jiaoyu.bos.domain.User;
import com.jiaoyu.dao.impl.IFunctionDao;
import com.jiaoyu.dao.impl.IUserDao;

public class BOSRealm extends AuthorizingRealm {
	
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IFunctionDao functionDao;

	//认证！！！
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken passwordToken=(UsernamePasswordToken)token;
		String username=passwordToken.getUsername();
		User user=userDao.findUserByUsername(username);
		if(user==null) {
			return null;
		}
		AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
		return info;
	}

	//权限！！
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		//1.fist step new simpleAuthor
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		//
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		List<Function> list=null;
		if(user.getUsername().equals("gaogao")) {
			DetachedCriteria dc=DetachedCriteria.forClass(Function.class);
			list=functionDao.findByDc(dc);
		}else {
			list=functionDao.findFunctionListByUserId(user.getId());
		}
		
		for (Function function : list) {
			info.addStringPermission(function.getCode());
		}
		return info;
	}


}
