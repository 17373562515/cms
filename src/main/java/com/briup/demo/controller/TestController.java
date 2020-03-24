package com.briup.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description="测试接口")  //当前接口是测试接口
@RestController  //不是返回jsp  返回的是响应体
public class TestController {
	@GetMapping("/test")
	//@RequestMapping(value="/test",method=RequestMethod.GET)  //指定url地址 指定请求方式
	@ApiOperation("这是无敌飞猪接口")                              //当前资源只能通过get方式获取
	public String test() {
		return "飞猪！！！";
	}

}
