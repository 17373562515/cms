package com.briup.demo.service;

import com.briup.demo.bean.IndexResult;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.utils.CustomerException;

/**
 * 首页首页的所有数据
 */
public interface IIndexResultService {
	/**
	 * 查询首页需要的所有数据
	 * @return
	 */
	IndexResult findIndexAllResult();
	
	
}
