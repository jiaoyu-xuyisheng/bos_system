package com.jiaoyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiaoyu.bos.domain.Workordermanage;
import com.jiaoyu.bos.domain.pageBean;
import com.jiaoyu.dao.impl.IWorkordermanageDao;
import com.jiaoyu.service.IWorkordermanageService;

@Service
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {
	
	@Autowired
	private IWorkordermanageDao WorkordermanageDao;

	@Override
	public void add(Workordermanage model) {
		WorkordermanageDao.save(model);
	}

	@Override
	public void delete(Workordermanage model) {
		WorkordermanageDao.delete(model);
	}

	@Override
	public void pageQuery(pageBean pagebean) {
		
	 WorkordermanageDao.pageQuery(pagebean);
	}

}
