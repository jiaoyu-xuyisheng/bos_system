package com.jiaoyu.service;

import com.jiaoyu.bos.domain.User;
import com.jiaoyu.bos.domain.pageBean;

public interface IUserService {
	User login(User u);

	void save(User model);

	void editPassword(String id, String psd);

	void add(User model, String[] roleids);

	void pagQuery(pageBean pagebean);
}
