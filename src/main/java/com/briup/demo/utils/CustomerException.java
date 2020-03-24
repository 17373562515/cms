package com.briup.demo.utils;
/*
 * 自定义异常
 */
public class CustomerException extends RuntimeException{

	/**
	 * 添加的id
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer code;  //状态码

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public CustomerException(Integer code,String message) {
		super(message); //调用父类的构造器
		this.code = code;
	}
	

}
