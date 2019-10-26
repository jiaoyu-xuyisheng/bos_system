package com.jiaoyu.bos.domain;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("all")
public class Workbill implements Serializable {

	private String	id  ;
	private Noticebill  noticebill ;
	private Staff  staff ;
	private String  type ;
	private String  pickstate ;
	private Timestamp  buildtime ;
	private Integer  attachbilltimes ;
	private String  remark ;
	
	public static final String TYPE_1="新单";
	public static final String TYPE_2="追单";
	public static final String TYPE_3="改单";
	public static final String TYPE_4="销单";
	
	
	public static final String PICKSTATE_NO="未取件";
	public static final String PICKSTATE_YES="取件中";
	public static final String PICKSTATE_RUNNING="巳取件";
	
	
	public Workbill() {
		
	}
	
	
	public Workbill(String id, Timestamp buildtime) {
		this.id = id;
		this.buildtime = buildtime;
	}


	public Workbill(Noticebill noticebill, Staff staff, String type, String pickstate, Timestamp buildtime,
			Integer attachbilltimes, String remark) {
	
		this.noticebill = noticebill;
		this.staff = staff;
		this.type = type;
		this.pickstate = pickstate;
		this.buildtime = buildtime;
		this.attachbilltimes = attachbilltimes;
		this.remark = remark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Noticebill getNoticebill() {
		return noticebill;
	}
	public void setNoticebill(Noticebill noticebill) {
		this.noticebill = noticebill;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPickstate() {
		return pickstate;
	}
	public void setPickstate(String pickstate) {
		this.pickstate = pickstate;
	}
	public Timestamp getBuildtime() {
		return buildtime;
	}
	public void setBuildtime(Timestamp buildtime) {
		this.buildtime = buildtime;
	}
	public Integer getAttachbilltimes() {
		return attachbilltimes;
	}
	public void setAttachbilltimes(Integer attachbilltimes) {
		this.attachbilltimes = attachbilltimes;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}                         
