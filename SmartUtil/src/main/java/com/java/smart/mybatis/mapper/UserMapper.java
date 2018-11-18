package com.java.smart.mybatis.mapper;

import java.util.List;

import com.java.smart.mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	/**
	 * 新增用户
	 * @param user
	 */
	public void add(User user);
	
	/**
	 * 修改用户信息
	 * @param user
	 */
	public void update(User user);
	
	/**
	 * 删除用户
	 * @param user
	 */
	public void delete(String id);
	
	/**
	 * 通过用户id查询用户信息
	 * @param id
	 * @return
	 */
	public User getUserInfoById(String id);
	
	/**
	 * 查询所有用户信息
	 * @return
	 */
	public List<User> getAllUserInfo();
	
}
