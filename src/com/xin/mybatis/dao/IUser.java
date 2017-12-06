package com.xin.mybatis.dao;
import java.util.List;

import org.apache.ibatis.annotations.*;

import com.xin.mybatis.models.User;

public interface IUser {
	//@Select("select * from user where id = #{id}")
	//public User getUserById(int id);
	public void insertUser(User user);
	public List<User> getUserList();
	public void deleteUser(int id);
	public void updateUser(User user);
	public User getUser(int id);
}
