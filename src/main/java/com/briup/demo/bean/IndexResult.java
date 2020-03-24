package com.briup.demo.bean;

import java.io.Serializable;
import java.util.List;

import com.briup.demo.bean.ex.CategoryEx;

/**
 * 保存首页的所有数据  最后返回的是这一个对象
 */
public class IndexResult implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<CategoryEx> categoryEx;
	private List<Link> links;
	public List<CategoryEx> getCategoryEx() {
		return categoryEx;
	}
	public void setCategoryEx(List<CategoryEx> categoryEx) {
		this.categoryEx = categoryEx;
	}
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public IndexResult(List<CategoryEx> categoryEx, List<Link> links) {
		super();
		this.categoryEx = categoryEx;
		this.links = links;
	}
	public IndexResult() {
		super();
	}
	
 }
