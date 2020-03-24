package com.briup.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Article;
import com.briup.demo.service.IAtricleService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(description="文章相关的链接")
public class ArticleController {
   
	@Autowired
	private IAtricleService articleService;
	
	@ApiOperation("根据条件查找文章")
	@GetMapping("/findArticleByCondition")
	
	public Message<List<Article>> getArticleByCondition(String keyStr, String condition){
		List<Article> list; 
		try {
			list= articleService.findArticleByCondition(keyStr, condition);
			return MessageUtil.success(list);
		} catch (CustomerException e) {
			e.printStackTrace();
			return MessageUtil.error(StatusCodeUtil.ERROR_COOE, "没有相应的栏目");
		}
		
	}
	
	@ApiOperation("添加文章信息")
	@PostMapping("/addArticle")
	public Message<String> saveArticle(Article article){
		try {
			articleService.saveOrUpdateAritcle(article);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_COOE, "系统错误"+e.getMessage());
		}
		
	}
	
	@ApiOperation("修改文章信息")
	@PostMapping("/updateArticle")
	public Message<String> updateArticle(Article article){
		try {
			articleService.saveOrUpdateAritcle(article);
			return MessageUtil.success();
		} catch (CustomerException e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_COOE, "系统错误"+e.getMessage());
		}
		
	}
	
	@ApiOperation("根据id删除文章")
	@GetMapping("/deleteArticleById")
	public Message<String> deleteArticleById(int id){
		articleService.deleteArticleById(id);
		return MessageUtil.success();
	}
	
	@ApiOperation("根据id查找文章")
	@GetMapping("/findArticleById")
	public Message<Article> getArticleById(int id){
		return MessageUtil.success( articleService.findArticleById(id));
	}
	
	
}
