package com.jiaoyu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jiaoyu.dao.impl.IUserDao;
import com.opensymphony.xwork2.ActionContext;
import com.jiaoyu.bos.domain.User;

/**
 * Repository就是spring 创建对象！！
* @ClassName: UserDaoImpl  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author xuyisheng  
* @date 2018年6月11日  
*
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	@Override
	public User findUserByUsernameAndPassWord(String username, String password) {
		String hql="FROM User u WHERE u.username=? AND u.password=?";
		@SuppressWarnings("unchecked")
		List<User> list= (List<User>) this.getHibernateTemplate().find(hql,username,password);
		if(list!=null&&list.size()>0) {
			return	list.get(0);
		}
		return null;
	}

	@Override
	public User findUserByUsername(String username) {
		String hql="FROM User u WHERE u.username=?";
		@SuppressWarnings("unchecked")
		List<User> list= (List<User>) this.getHibernateTemplate().find(hql,username);
		if(list!=null&&list.size()>0) {
			return	list.get(0);
		}
		return null;
	}




}
