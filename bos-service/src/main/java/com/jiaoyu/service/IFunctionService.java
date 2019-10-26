package com.jiaoyu.service;

import java.util.List;

import com.jiaoyu.bos.domain.Function;
import com.jiaoyu.bos.domain.pageBean;

public interface IFunctionService {

	List<Function> findAll();

	void save(Function model);

	void pageQuery(pageBean pagebean);

	List<Function> findMenu();

}
