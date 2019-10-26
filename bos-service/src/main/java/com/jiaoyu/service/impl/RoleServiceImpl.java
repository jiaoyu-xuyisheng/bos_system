package com.jiaoyu.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiaoyu.bos.domain.Function;
import com.jiaoyu.bos.domain.Role;
import com.jiaoyu.bos.domain.pageBean;
import com.jiaoyu.dao.impl.IRoleDao;
import com.jiaoyu.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IRoleDao roleDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public void save(Role model, String ids) {
		roleDao.save(model);
		if(StringUtils.isNotBlank(ids)) {
			String[] idsarray = ids.split(",");		
			for (String id : idsarray) {
				Function function = new Function(id);
				model.getFunctions().add(function);		
			}
		}
	}

	@Override
	public void pageQuery(pageBean pagebean) {
		roleDao.pageQuery(pagebean);		
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleDao.findAll();		
	}

}
