package com.xin.mybatis.main;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.xin.mybatis.dao.IUser;
import com.xin.mybatis.models.User;

public class Main {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	static {
		try{
			reader = Resources.getResourceAsReader("config/Configure.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			sqlSessionFactory.getConfiguration().addMapper(IUser.class);
		}catch (Exception E)
		{
			E.printStackTrace();
		}
	}
	public static SqlSessionFactory getSessionFactory(){
		return sqlSessionFactory;
	}
	public static void main(String[] args){
		SqlSession session=sqlSessionFactory.openSession();
		try{
			IUser iuser = session.getMapper(IUser.class);
			User user =iuser.getUserById(1);
			System.out.println("名字:"+user.getName());
			System.out.println("所属部门:"+user.getDept());
			System.out.println("主页:"+user.getWebsite());
		}finally {
			session.close();
		}
	}
}
