package com.jiaoyu.service;

import java.util.List;

import com.jiaoyu.bos.domain.Decidedzone;
import com.jiaoyu.bos.domain.pageBean;

public interface IDecidedzoneService {

	void add(Decidedzone model, String[] subareaid);

	void pageQuery(pageBean pagebean);

	Decidedzone getById(String decidedzoneId);
}
