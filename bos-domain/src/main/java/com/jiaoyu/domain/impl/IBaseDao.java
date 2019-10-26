package com.jiaoyu.domain.impl;

import java.io.Serializable;
import java.util.List;



import org.hibernate.criterion.DetachedCriteria;

public interface IBaseDao<T> {
	//保存或修改！！！
	public void saveOrUpdate(T t);
	//保存
	public void save(T entity) ;
	//删除
    public void delete(T entity) ;
    //修改
    public void update(T entity) ;
    //查询对象通过id!!
    public T findById(Serializable id) ;
    //查询集合
    public List<T> findAll() ;
    //查询元素个数
	public Integer getTotalCount(DetachedCriteria dc);
	//查分页
	public List<T> getPageList(DetachedCriteria dc,Integer start,Integer pageSize);
	  //查询集合
	public List<T> getListByDc(DetachedCriteria dc);
                     
}   
    