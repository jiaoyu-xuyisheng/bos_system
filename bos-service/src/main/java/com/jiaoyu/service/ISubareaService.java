package com.jiaoyu.service;

import java.util.List;

import com.jiaoyu.bos.domain.Subarea;
import com.jiaoyu.bos.domain.pageBean;

public interface ISubareaService {

	void save(Subarea model);

	void pageQuery(pageBean pagebean);

	List<Subarea> findAll();

	List<Subarea> findListNotAssociation();

	List<Subarea> findListByDecidedzoneId(String decidedzoneId);

}
