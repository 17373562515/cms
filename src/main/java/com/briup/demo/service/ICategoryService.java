package com.briup.demo.service;

import java.util.List;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.utils.CustomerException;
/**
 * 栏目相关的Service层
 * @author Administrator
 *
 */
public interface ICategoryService {
	/**
	 * 查询所有栏目信息
	 * @return
	 * @throws CustomerException
	 */
	public List<Category> findAllCategorys()  throws CustomerException;
	/**
	 * 添加栏目信息
	 * @param category
	 * @throws CustomerException
	 */
	public void saveOrUpdateCategory(Category category) throws CustomerException;
	/**
	 * 通过id删除栏目信息
	 * @param id
	 * @throws CustomerException
	 */
	public void deleteCategoryById(int id) throws CustomerException;
	/**
	 * 通过id查找指定的栏目信息
	 * @param id
	 * @return
	 * @throws CustomerException
	 */
	public Category findCategoryById(int id) throws CustomerException;
    
    /**
             *查询栏目并且级联查询的文章信息
     * @return
     * @throws CustomerException
     */
	List<CategoryEx> findAllCategoryEx() throws CustomerException;
	
	/**
	 * 查询栏目及其包含的文章的所有数据
	 * （本来应该定义在ICategoryService）
	 */
	CategoryEx findCategoryExById(int id)  throws CustomerException;
}
