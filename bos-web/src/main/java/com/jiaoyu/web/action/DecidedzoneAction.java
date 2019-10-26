package com.jiaoyu.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiaoyu.bos.domain.Decidedzone;
import com.jiaoyu.cust.Customer;
import com.jiaoyu.cust.ICustomerService;
import com.jiaoyu.service.IDecidedzoneService;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {
	
	@Autowired
	private IDecidedzoneService decidedzoneService;
	@Autowired
	private ICustomerService customerService;
	
	private String[] subareaid;
	
	private List<Integer> customerIds;
	

	public void setCustomerIds(List<Integer> customerIds) {
		this.customerIds = customerIds;
	}

	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}
	
	public String add() throws Exception{		
		decidedzoneService.add(model, subareaid);	
		return LIST;
		
	}
	
	
	public String pageQuery() throws Exception{	
		decidedzoneService.pageQuery(pagebean);		
		this.java2Json(pagebean, new String[] {"currentPage","detachedCriteria","pageSize","subareas","decidedzones"});	
		return NONE;
	}
	
	public String findListNotAssociation() throws Exception{
		List<Customer> list = customerService.findListNotAssociation();
		java2Json(list, new String[]{});		
		return NONE;
	}

	public String  findListHasAssociation() throws Exception{
		String id = model.getId();
		List<Customer> list = customerService.findListHasAssociation(id);
		this.java2Json(list,new String[] {});		
		return NONE;
	}
	
	public String assigncustomerstodecidedzone() throws Exception{
		String id = model.getId();
		customerService.assigncustomerstodecidedzone(id, customerIds);
		return LIST;
	}
			
}
