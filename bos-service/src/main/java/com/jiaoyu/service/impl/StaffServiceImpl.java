package com.jiaoyu.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiaoyu.bos.domain.Staff;
import com.jiaoyu.bos.domain.pageBean;
import com.jiaoyu.dao.impl.IStaffDao;
import com.jiaoyu.service.IStaffService;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

	@Autowired
	private IStaffDao staffDao;
	@Override
	public void save(Staff staff) {
		staffDao.save(staff);

	}
	@Override
	public void pageQuery(pageBean pagebean) {
		staffDao.pageQuery(pagebean);		
	}
	@Override
	public Staff getById(String id) {
		return staffDao.findById(id);
	}
	@Override
	public void update(Staff staff) {
		staffDao.update(staff);		
	}
	@Override
	public void delStaff(String ids) {
		String[] myids = ids.split(",");
		for (String id : myids) {
			staffDao.executeUpdate("staff.delStaff",id);
		}
		
		
	}
	@Override
	public List<Staff> findListNotDel() {
		DetachedCriteria dc=DetachedCriteria.forClass(Staff.class);
		dc.add(Restrictions.eq("deltag", "0"));
		return 	staffDao.findByDc(dc);
	}

}
