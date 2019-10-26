package com.jiaoyu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jiaoyu.bos.domain.Function;
import com.jiaoyu.dao.impl.IFunctionDao;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao{
	
	@SuppressWarnings("all")
	public List<Function> findAll() {
		String hql="FROM Function f WHERE f.parentFunction IS NULL";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findFunctionListByUserId(String id) {
		String hql="SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
				+" r LEFT OUTER JOIN r.users u WHERE u.id=?";
		return (List<Function>) this.getHibernateTemplate().find(hql, id);
	}

	@Override
	public List<Function> findAllMenu() {
		String hql="FROM Function f WHERE f.generatemenu='1' ";
		List<Function> list = (List<Function>) this.getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findMenuByUserId(String id) {
		String hql="SELECT DISTINCT f FROM Function f LEFT OUTER JOIN f.roles"
				+" r LEFT OUTER JOIN r.users u WHERE u.id=? AND  f.generatemenu='1' ORDER BY f.zindex DESC ";
		return (List<Function>) this.getHibernateTemplate().find(hql, id);
	}
}
