package com.jiaoyu.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiaoyu.bos.domain.Subarea;
import com.jiaoyu.bos.domain.pageBean;
import com.jiaoyu.dao.impl.ISubareaDao;
import com.jiaoyu.service.ISubareaService;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {
	@Autowired
	private ISubareaDao subareaDao;

	@Override
	public void save(Subarea model) {
		subareaDao.save(model);

	}

	@Override
	public void pageQuery(pageBean pagebean) {
		subareaDao.pageQuery(pagebean);		
	}

	@Override
	public List<Subarea> findAll() {
		return  subareaDao.findAll();
	}

	@Override
	public List<Subarea> findListNotAssociation() {
		DetachedCriteria dc=DetachedCriteria.forClass(Subarea.class);
		dc.add(Restrictions.isNull("decidedzone"));
		return subareaDao.findByDc(dc);
	}

	@Override
	public List<Subarea> findListByDecidedzoneId(String decidedzoneId) {
		DetachedCriteria dc=DetachedCriteria.forClass(Subarea.class);
		dc.add(Restrictions.eq("decidedzone.id", decidedzoneId));
		
		return subareaDao.findByDc(dc);
	}

}
