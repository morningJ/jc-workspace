package com.java.smart.mybatis.mapper;

import com.java.smart.mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	
	/**
	 * 用户登录  用户id 密码password
	 * @param id
	 * @param password
	 * @return
	 */
	public User login(String id, String password);
	
	
}
