package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Category;
import com.briup.demo.bean.ex.CategoryEx;
import com.briup.demo.service.ICategoryService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 与栏目有关的web层
 *
 */
@RestController
@Api(description="与栏目有关的接口")
public class CategoryController {
	
	@Autowired
	private ICategoryService  categoryService;
	
	@PostMapping("/addCategory")
	@ApiOperation("/增加栏目信息")
	public Message<String> saveCategory(Category category){
		
		try {
			categoryService.saveOrUpdateCategory(category);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_COOE, "系统错误！"+e.getMessage());
		}
		
	}
	
	@PostMapping("/updateCategory")
	@ApiOperation("/修改栏目信息")
	public Message<String> updateCategory(Category category){
		try {
			categoryService.saveOrUpdateCategory(category);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_COOE, "系统错误！"+e.getMessage());
		}
		
	}	
	@GetMapping("/findAllCategorys")
	@ApiOperation("/查找所有的栏目")
	public Message<List<Category>> findAllCategorys(){
		
		List<Category> list = categoryService.findAllCategorys();
		return MessageUtil.success(list);
	
	}

	@GetMapping("/deleteCategoryById")
	@ApiOperation("通过id删除栏目")
	public Message<String> deleteCategoryById(int id){
		//调用的是service层的删除方法 级联删除操作是在service层做的业务逻辑
		categoryService.deleteCategoryById(id);
		return MessageUtil.success();
	}
	
	@GetMapping("/findCategoryById")
	@ApiOperation("通过id查找栏目")
	public Message<Category> findCategoryById(int id){
		Category category = categoryService.findCategoryById(id);
		return MessageUtil.success(category);
	
	}
	
	/**
	 * 根据ID查找栏目及其包含的所有文章信息
	 */
	
	@GetMapping("/findCategoryExById")
	@ApiOperation("根据ID查找栏目及其包含的所有文章信息")
	public Message<CategoryEx> findCategoryExById(int id){
		return MessageUtil.success(categoryService.findCategoryExById(id));
	}
}
