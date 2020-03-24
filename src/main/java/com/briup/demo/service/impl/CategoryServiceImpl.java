package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryExMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

@Service  //将该实现类放到ioc容器中 并进行事务管理
public class CategoryServiceImpl implements ICategoryService {
	
	@Autowired
	private CategoryMapper categorymapper;
	
	@Autowired
	private ArticleMapper articleMapper;
	
	//栏目的拓展dao
	@Autowired
	private CategoryExMapper categoryExMapper;
	
	
	@Override
	public void saveOrUpdateCategory(Category category) throws CustomerException {
		//如果传过来的category对象为空 则向web层抛异常
		if(category==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_COOE, "参数为空");
		}
		//如果传过来的栏目名字和数据库中数据一样 则抛异常
		List<Category> list = findAllCategorys();
		for (Category category2 : list) {
			if(category.getName().equals(category2.getName())) {
				throw new CustomerException(StatusCodeUtil.ERROR_COOE, "栏目名称已经存在");
			}
		}
		if(category.getId()==null) {
			//进行插入操作                                     
			categorymapper.insert(category);
		}else {
			//有id值 进行修改操作
			categorymapper.updateByPrimaryKey(category);
		}
		
		
	}
	
	
	
	
	@Override
	public List<Category> findAllCategorys() throws CustomerException {
		
		return categorymapper.selectByExample(new CategoryExample());

	}

	@Override
	public void deleteCategoryById(int id) throws CustomerException {
		
		//删除栏目的同时，需要先删除栏目中文章的信息
		ArticleExample example = new ArticleExample();
		example.createCriteria().andCategoryIdEqualTo(id);
		articleMapper.deleteByExample(example);
		categorymapper.deleteByPrimaryKey(id);
	}

	@Override
	public Category findCategoryById(int id) throws CustomerException {
		return categorymapper.selectByPrimaryKey(id);
	}




	@Override
	public List<CategoryEx> findAllCategoryEx() throws CustomerException {
		return categoryExMapper.findAllCategoryExs();
	}




	@Override
	public CategoryEx findCategoryExById(int id) throws CustomerException {

		return categoryExMapper.findCategoryExById(id);
	}

}
