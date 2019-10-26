package com.jiaoyu.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiaoyu.bos.domain.Noticebill;
import com.jiaoyu.cust.Customer;
import com.jiaoyu.cust.ICustomerService;
import com.jiaoyu.service.INoticebillService;

@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
	
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private INoticebillService noticebillService;
	
	public String findCustomerByTelephone() throws Exception{
		Customer c = customerService.findCustomerByTelephone(model.getTelephone());
		this.java2Json(c, new String[] {});
		return NONE;
	}
	
	public String add() throws Exception{	
		noticebillService.save(model);		
		return "noticebill_add";
	}
}
