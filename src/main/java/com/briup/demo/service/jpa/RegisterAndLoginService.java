package com.briup.demo.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.bean.Customer;
import com.briup.demo.bean.CustomerExample;
import com.briup.demo.mapper.JapMapper.CustomerDao;
import com.briup.demo.utils.CustomerException;
import com.briup.demo.utils.StatusCodeUtil;

@Service
public class RegisterAndLoginService {
  
	@Autowired
	private CustomerDao customerDao;
	
	//保存用户账号密码
	public void addCustomer(Customer customer) {
		List<Customer> list = customerDao.findByUsername(customer.getUsername());
	    if(list.size()<=0) {
	    	//如果数据库中没有这个用户名 则保存该用户
	    	customerDao.save(customer);
	    }else {
	    	//数据库中有该用户名  抛出异常
	    	throw new CustomerException(StatusCodeUtil.ERROR_COOE, "账号有问题");
	    }
	}
	
	//登录功能
	public void loginSystem(Customer customer) {
		if(customer==null) {
			throw new CustomerException(StatusCodeUtil.ERROR_COOE, "账号或密码为空");
		}
		List<Customer> list = customerDao.findByUsernameAndPassword(customer.getUsername(), customer.getPassword());
		if(list!=null&&list.size()>0) {
		}else {
	    	throw new CustomerException(StatusCodeUtil.ERROR_COOE, "账号或密码出错，请修改！");
	    }
		
	}
}
