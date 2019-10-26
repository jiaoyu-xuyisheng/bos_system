package com.jiaoyu.dao.impl;

import com.jiaoyu.bos.domain.User;

public interface IUserDao extends IBaseDao<User> {

	User findUserByUsernameAndPassWord(String username, String password);
	User findUserByUsername(String username);
}
