package com.jiaoyu.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiaoyu.bos.domain.Role;
import com.jiaoyu.bos.domain.User;
import com.jiaoyu.bos.domain.pageBean;
import com.jiaoyu.dao.impl.IUserDao;
import com.jiaoyu.service.IUserService;
import com.jiaoyu.utils.MD5Utils;



@Service
@Transactional
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	
	@Override
	public User login(User u) {
		String password=MD5Utils.md5(u.getPassword());	
		return userDao.findUserByUsernameAndPassWord(u.getUsername(),password);
	}

	@Override
	public void save(User model) {
		userDao.save(model);
	}

	@Override
	public void editPassword(String id, String psd) {
		System.out.println(id+"||||"+psd);
		userDao.executeUpdate("user.editpassword",psd,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(User user, String[] roleids) {
		String password = user.getPassword();
		user.setPassword(MD5Utils.md5(password));	
		userDao.save(user);
		if(roleids!=null&&roleids.length>0) {
			for (String rid : roleids) {
				Role role = new Role(rid);
				user.getRoles().add(role);
			}
		}
	}

	@Override
	public void pagQuery(pageBean pagebean) {
		userDao.pageQuery(pagebean);
		
	}

}
