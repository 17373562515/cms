package com.briup.demo.mapper.JapMapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.briup.demo.bean.Customer;
import com.briup.demo.bean.CustomerExample;
/**
 * 用户dao
 *
 */
public interface CustomerDao extends JpaRepository<Customer, Integer>{
   //根据用户账号查找
	public List<Customer> findByUsername(String username);

	public List<Customer> findByUsernameAndPassword(String username,String password);
}
