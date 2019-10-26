package com.jiaoyu.service;

import java.util.List;

import com.jiaoyu.bos.domain.Region;
import com.jiaoyu.bos.domain.pageBean;

public interface IRegionService {

	void saveList(List<Region> regionList);

	void pageQuery(pageBean pagebean);

	List<Region> findAll();

	List<Region> findListByQ(String q);

}
