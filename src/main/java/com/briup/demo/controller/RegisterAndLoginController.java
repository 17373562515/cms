package com.briup.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.demo.bean.Customer;
import com.briup.demo.mapper.JapMapper.CustomerDao;
import com.briup.demo.service.jpa.RegisterAndLoginService;
import com.briup.demo.utils.Message;
import com.briup.demo.utils.MessageUtil;
import com.briup.demo.utils.StatusCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 注册和登录
 *
 */
@RestController
@Api("注册和登录相关的接口")
public class RegisterAndLoginController {

	@Autowired
	private RegisterAndLoginService registerAndLoginService;
	
	@PostMapping("/register")
	@ApiOperation("用户注册")
	public Message<String>  register(Customer customer) {
		try {
			registerAndLoginService.addCustomer(customer);
			return MessageUtil.success();
		} catch (Exception e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_COOE, "账号有问题");
		}
		
	}
	
	@PostMapping("/login")
	@ApiOperation("用户登录")
	public Message<String>  login(Customer customer) {
		try {
			registerAndLoginService.loginSystem(customer);
			return MessageUtil.success();
		} catch (Exception e) {
			return MessageUtil.error(StatusCodeUtil.ERROR_COOE, "账号或密码错误！");
		}
		
	}
}
