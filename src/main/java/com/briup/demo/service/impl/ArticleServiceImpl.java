package com.briup.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Article;
import com.briup.demo.bean.ArticleExample;
import com.briup.demo.bean.ArticleExample.Criteria;
import com.briup.demo.bean.Category;
import com.briup.demo.bean.CategoryExample;
import com.briup.demo.mapper.ArticleMapper;
import com.briup.demo.mapper.CategoryMapper;
import com.briup.demo.service.IAtricleService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;
/**
 * 实现文章管理相关的逻辑类
 */
@Service
public class ArticleServiceImpl implements IAtricleService {
    
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public void saveOrUpdateAritcle(Article article) throws CustomerException {
		if(article==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_COOE, "参数为空");
			
		}
		
		if(article.getId()==null) {
			//需要额外添加两条数据
			article.setPublishdate(new Date());
			article.setClicktimes(0);
			articleMapper.insert(article);
		}else {
			article.setPublishdate(new Date());
			articleMapper.updateByPrimaryKey(article);
		}
	}

	@Override
	public void deleteArticleById(int id) throws CustomerException {
		articleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Article> findArticleByCondition(String keyStr, String condition) throws CustomerException {
		//根据条件查询
		//能不写if就不写if 三目运算符
		keyStr=keyStr==null?"":keyStr.trim();
		condition=condition==null?"":condition.trim();
		//创建空白的模板对象
		ArticleExample example = new ArticleExample();
		if("".equals(keyStr)&&"".equals(condition)) {
			//1.没有添加任何条件，默认查询所有文章
			return articleMapper.selectByExample(example);
		}else if(!"".equals(keyStr)&&"".equals(condition)) {
			//2.没有指定栏目，但是指定了栏目关键字，则根据关键字查询满足条件的文章
			  example.createCriteria().andTitleLike("%"+keyStr+"%");
			  return articleMapper.selectByExample(example);
			  
		}else if(!"".equals(condition)&&"".equals(keyStr)) {
			//情况3
			//3.指定栏目，没有指定 查询的关键字，则根据关键字查询满足条件的文章
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameEqualTo(condition);
			List<Category> category = categoryMapper.selectByExample(categoryExample);
		
			if(category.size()>0) {
				
				example.createCriteria().andCategoryIdEqualTo(category.get(0).getId());
			}else {
				throw new CustomerException(StatusCodeUtil.ERROR_COOE, "没有指定的搜索栏目");
			}
			
			return articleMapper.selectByExample(example);
		
		}else{
			//4..指定栏目，同时指定查询的关键字，则根据栏目和关键字查询满足条件的文章
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameEqualTo(condition);
			List<Category> category = categoryMapper.selectByExample(categoryExample);
			if(category.size()>0) {
				//and拼接方式
				example.createCriteria().andCategoryIdEqualTo(category.get(0).getId()).andTitleLike("%"+keyStr+"%");;
			}else {
				throw new CustomerException(StatusCodeUtil.ERROR_COOE, "没有指定的搜索栏目");
			}

			//or拼接方式
			//example.or().andTitleLike("%"+keyStr+"%");
			return articleMapper.selectByExample(example);
			
		}
		
	}

	/*@Override
	public Article findArticleById(int id) throws CustomerException {
		return articleMapper.selectByPrimaryKey(id);
	}*/

	
	@Override
	public Article findArticleById(int id) throws CustomerException {
		Article article = articleMapper.selectByPrimaryKey(id);
		//添加点击次数
		Integer clickTime=article.getClicktimes()==null?0:article.getClicktimes();
		article.setClicktimes(clickTime+1);
		//同时更新数据库中的内容
		this.saveOrUpdateAritcle(article);
		return article;
	}

	

}
