package com.jiaoyu.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jiaoyu.bos.domain.Function;


public interface IFunctionDao extends IBaseDao<Function> {
	public List<Function> findAll();

	public List<Function> findFunctionListByUserId(String id);

	public List<Function> findAllMenu();

	public List<Function> findMenuByUserId(String id);
}
