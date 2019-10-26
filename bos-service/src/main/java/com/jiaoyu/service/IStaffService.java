package com.jiaoyu.service;

import java.util.List;

import com.jiaoyu.bos.domain.Staff;
import com.jiaoyu.bos.domain.pageBean;

public interface IStaffService {
	void save(Staff staff);

	void pageQuery(pageBean pagebean);

	Staff getById(String id);

	void update(Staff staff);

	void delStaff(String ids);

	List<Staff> findListNotDel();
}
