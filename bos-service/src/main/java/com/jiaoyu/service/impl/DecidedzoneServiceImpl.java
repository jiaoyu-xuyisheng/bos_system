package com.jiaoyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiaoyu.bos.domain.Decidedzone;
import com.jiaoyu.bos.domain.Subarea;
import com.jiaoyu.bos.domain.pageBean;
import com.jiaoyu.dao.impl.IDecidedzoneDao;
import com.jiaoyu.dao.impl.ISubareaDao;
import com.jiaoyu.service.IDecidedzoneService;

@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {
	
	@Autowired
	private IDecidedzoneDao decidedzoneDao;
	@Autowired
	private ISubareaDao subareaDao;

	@Override
	public void add(Decidedzone model, String[] subareaid) {
		decidedzoneDao.save(model);
		for (String id : subareaid) {
			Subarea subarea = subareaDao.findById(id);
			subarea.setDecidedzone(model);
			
		}
		

	}

	@Override
	public void pageQuery(pageBean pagebean) {
		
		 decidedzoneDao.pageQuery(pagebean);
	}

	@Override
	public Decidedzone getById(String decidedzoneId) {
		Decidedzone dd = decidedzoneDao.findById(decidedzoneId);
		return dd;
	}

}
