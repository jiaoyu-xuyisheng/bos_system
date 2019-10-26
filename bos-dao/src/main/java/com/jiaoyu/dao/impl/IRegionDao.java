package com.jiaoyu.dao.impl;

import java.util.List;

import com.jiaoyu.bos.domain.Region;

public interface IRegionDao extends IBaseDao<Region>{

	List<Region> findListByQ(String q);

}
