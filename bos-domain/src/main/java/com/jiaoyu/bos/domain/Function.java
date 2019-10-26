package com.jiaoyu.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 权限实体
 */

public class Function implements java.io.Serializable {

	// Fields

	private String id;
	private Function parentFunction;//当前权限的上级权限
	private String name;//功能名字
	private String code;//关键字
	private String description;//描述
	private String page;//访问路径
	private String generatemenu;//是否生成菜单，1：是 0：否
	private Integer zindex;//排序位置
	private Set roles = new HashSet(0);//当前权限对应的多个角色
	private Set children = new HashSet(0);//当前权限的下级权限
	
	
	
	
	public Function() {

	}
	
	
	public Function(String id) {
		this.id = id;
	}


	public Function(String id, Function parentFunction, String name, String code, String description, String page,
			String generatemenu, Integer zindex, Set roles, Set children) {
		this.id = id;
		this.parentFunction = parentFunction;
		this.name = name;
		this.code = code;
		this.description = description;
		this.page = page;
		this.generatemenu = generatemenu;
		this.zindex = zindex;
		this.roles = roles;
		this.children = children;
	}
	
	public String getpId() {
		if(parentFunction==null) {
			return "0";
		}
		return parentFunction.getId();
	}

	public String getText() {
		return name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Function getParentFunction() {
		return parentFunction;
	}
	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getGeneratemenu() {
		return generatemenu;
	}
	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}
	public Integer getZindex() {
		return zindex;
	}
	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}
	public Set getRoles() {
		return roles;
	}
	public void setRoles(Set roles) {
		this.roles = roles;
	}
	public Set getChildren() {
		return children;
	}
	public void setChildren(Set children) {
		this.children = children;
	}
}