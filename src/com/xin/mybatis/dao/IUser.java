package com.xin.mybatis.dao;
import org.apache.ibatis.annotations.*;
import com.xin.mybatis.models.User;

public interface IUser {
	@Select("select * from user where id = #{id}")
	public User getUserById(int id);
}
