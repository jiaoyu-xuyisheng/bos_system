package com.jiaoyu.service;

import java.util.List;

import com.jiaoyu.bos.domain.Workordermanage;
import com.jiaoyu.bos.domain.pageBean;

public interface IWorkordermanageService {

	void add(Workordermanage model);

	void delete(Workordermanage model);

	void pageQuery(pageBean pagebean);

}
