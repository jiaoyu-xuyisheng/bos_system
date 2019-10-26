package com.jiaoyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiaoyu.bos.domain.Region;
import com.jiaoyu.bos.domain.pageBean;
import com.jiaoyu.dao.impl.IRegionDao;
import com.jiaoyu.service.IRegionService;

@Service
@Transactional
public class RegionServiceImpl implements IRegionService {
	
	@Autowired
	private IRegionDao regionDao;

	@Override
	public void saveList(List<Region> regionList) {
		
		for (Region region : regionList) {
			regionDao.saveOrUpdate(region);
		}

	}

	@Override
	public void pageQuery(pageBean pagebean) {
		regionDao.pageQuery(pagebean);
		
	}

	@Override
	public List<Region> findAll() {
		return regionDao.findAll();
	}

	@Override
	public List<Region> findListByQ(String q) {
		return regionDao.findListByQ(q);
	}

}
