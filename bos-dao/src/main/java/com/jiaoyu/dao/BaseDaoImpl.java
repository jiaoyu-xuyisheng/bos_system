package com.jiaoyu.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.jiaoyu.bos.domain.Staff;
import com.jiaoyu.bos.domain.pageBean;
import com.jiaoyu.dao.impl.IBaseDao;
import com.opensymphony.xwork2.ActionContext;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	private Class<T> clazz;
	
	
	public BaseDaoImpl() {
		//得到父类的类型！！也就是BaseDaoImpl的类型！！
		ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
		//得到的是尖括号中所有的类型！！
		Type[] typeArrray = superclass.getActualTypeArguments();
		clazz = (Class<T>) typeArrray[0];				
	}
	
	
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void saveOrUpdate(T t) {
		this.getHibernateTemplate().saveOrUpdate(t);		
	}

	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(Serializable id) {
		return  this.getHibernateTemplate().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		//getSimpleName();就是得到简单的类型的名字比名user！！！
		String hql="  FROM   "+clazz.getSimpleName();		
		return (List<T>)this.getHibernateTemplate().find(hql);
	}

	@Override
	public Integer getTotalCount(DetachedCriteria dc) {
		dc.setProjection(Projections.rowCount());
		@SuppressWarnings("unchecked")
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(dc);
		dc.setProjection(null);
		if(list!=null&&list.size()>0) {
			Long count=list.get(0);
			return count.intValue();
		}else {
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc, start, pageSize);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getListByDc(DetachedCriteria dc) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
	}


	@Override
	public void executeUpdate(String queryname, Object... objects) {
		Session session = this.getSessionFactory().getCurrentSession();
		Query query = session.getNamedQuery(queryname);
		int i=0;
		for(Object object : objects) {
			query.setParameter(i++, object);
		}
		query.executeUpdate();
		
	}


	@SuppressWarnings("all")
	@Override
	public void pageQuery(pageBean pagebean) {
		Integer currentPage = pagebean.getCurrentPage();
		Integer pageSize = pagebean.getPageSize();
		
		DetachedCriteria dc = pagebean.getDetachedCriteria();
		dc.setProjection(Projections.rowCount());
		//得到total;
		List<Long> count = (List<Long>) this.getHibernateTemplate().findByCriteria(dc);
		pagebean.setTotal(count.get(0).intValue());
		//得到rows
		dc.setProjection(null);
		dc.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		int firstResult=(currentPage-1)*pageSize;
		int maxResults=pageSize;
		List rows= this.getHibernateTemplate().findByCriteria(dc, firstResult, maxResults);
		pagebean.setRows(rows);
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByDc(DetachedCriteria dc) {
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
	}

}
