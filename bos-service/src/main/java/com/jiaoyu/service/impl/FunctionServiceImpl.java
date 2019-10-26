package com.jiaoyu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiaoyu.bos.domain.Function;
import com.jiaoyu.bos.domain.User;
import com.jiaoyu.bos.domain.pageBean;
import com.jiaoyu.dao.impl.IFunctionDao;
import com.jiaoyu.service.IFunctionService;
import com.jiaoyu.utils.BOSUtils;

@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService {
	
	@Autowired
	private IFunctionDao functionDao;

	@Override
	public List<Function> findAll() {
		
		return functionDao.findAll();
	}

	@Override
	public void save(Function model) {
		Function p = model.getParentFunction();
		String id = p.getId();
		if(id.equals("")&&p!=null) {
			model.setParentFunction(null);
		}
		functionDao.save(model);		
	}

	@Override
	public void pageQuery(pageBean pagebean) {
		functionDao.pageQuery(pagebean);
	}

	@Override
	public List<Function> findMenu() {
		List<Function> list=null;
		User u = BOSUtils.getLoginUser();
		if(u.getUsername().equals("gaogao")) {
			list=functionDao.findAllMenu();
		}else {
			list=functionDao.findMenuByUserId(u.getId());
		}
		return list;
	}

}
