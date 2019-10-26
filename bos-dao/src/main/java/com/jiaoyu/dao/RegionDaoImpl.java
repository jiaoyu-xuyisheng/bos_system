package com.jiaoyu.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jiaoyu.bos.domain.Region;
import com.jiaoyu.dao.impl.IRegionDao;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Region> findListByQ(String q) {
		String hql= "  FROM Region r WHERE r.shortcode LIKE ? "
				+ "	OR r.citycode LIKE ? OR r.province LIKE ? "
				+ "OR r.city LIKE ? OR r.district LIKE ?";
		List<Region> list = (List<Region>) this.getHibernateTemplate().find(hql,"%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%","%"+q+"%");
		return list;
	}
	
	
}
