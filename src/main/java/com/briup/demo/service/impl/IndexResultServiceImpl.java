package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSException;

import com.briup.demo.bean.IndexResult;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.service.IIndexResultService;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;
/**
 * 查询首页所有数据的实现类
 * @author Administrator
 *
 */
@Service
public class IndexResultServiceImpl implements IIndexResultService {
	//关联超链接的service层接口
	@Autowired
	private ILinkService linkService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Override
	public IndexResult findIndexAllResult() {
		IndexResult indexResult = new IndexResult();
		//设置所有的超链接信息
		indexResult.setLinks(linkService.findAllLinks());
		
		//设置栏目及其所有文章内容
		List<CategoryEx> categoryEx = categoryService.findAllCategoryEx();
		indexResult.setCategoryEx(categoryEx);
		return indexResult;
	}

	
	
	

}
