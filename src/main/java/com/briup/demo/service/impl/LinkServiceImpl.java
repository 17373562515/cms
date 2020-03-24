package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Link;
import com.briup.demo.bean.LinkExample;
import com.briup.demo.mapper.LinkMapper;
import com.briup.demo.service.ILinkService;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;
/**
 * 操作链接信息
 */
@Service   //放到ioc容器中生成bean对象   事务管理
public class LinkServiceImpl implements ILinkService {

	@Autowired
	private LinkMapper linkMapper; //映射接口
	
	//link是前台传递过来的数据进行封装之后的对象
	@Override
	public void saveOrUpdateLink(Link link) throws CustomerException {
		//参数为引用类型，要做判空处理
		//插入对象为空 数据库会报错  
		if(link==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_COOE, "参数为空");
		}
		//调用映射接口中的方法 执行操作
		
		//判断Link对象的id是否为空  id为空 插入 ;id不为空 修改
		if(link.getId()==null) {
			linkMapper.insert(link);
		}else {
			linkMapper.updateByPrimaryKey(link);
		}
	}

	@Override
	public List<Link> findAllLinks() throws CustomerException {
		return linkMapper.selectByExample(new LinkExample());
	}

	@Override
	public void deleteLinkById(int id) throws CustomerException {
		linkMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Link> findLinksByName(String name) throws CustomerException {
		name=name==null?"":name.trim();
		LinkExample example = new LinkExample();
		//name放在前面可能出现空指针异常
		if("".equals(name)) {
			//如果搜索条件没写，则返回所有数据
		return 	linkMapper.selectByExample(example);
		}else {
			//如果搜索条件不为null，则返回满足条件的数据
			//再模板里面添加数据
			example.createCriteria().andNameLike("%"+name+"%");
			return linkMapper.selectByExample(example);
		}
		
	}

	
	
}
