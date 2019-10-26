package com.jiaoyu.service;

import java.util.List;

import com.jiaoyu.bos.domain.Role;
import com.jiaoyu.bos.domain.pageBean;

public interface IRoleService {

	void save(Role model, String ids);

	void pageQuery(pageBean pagebean);

	List<Role> findAll();

}
