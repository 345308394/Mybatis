package com.xin.mybatis.main;

import java.io.Reader;
import java.text.MessageFormat;
import java.util.List;

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
			//testInsert();
			//getUserList();
			//deleteUser();
			updateUser();
		}finally {
			session.close();
		}
	}
	private static void updateUser() {
		// TODO Auto-generated method stub
		try{
			SqlSession session = sqlSessionFactory.openSession();
			IUser iuser = session.getMapper(IUser.class);
			System.out.println("Test update start ...");
			printUsers(iuser.getUserList());
			User user = iuser.getUser(1);
			user.setName("New Name");
			iuser.updateUser(user);
			session.commit();
			System.out.println("After update");
			printUsers(iuser.getUserList());
			System.out.println("Test update finished ...");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private static void deleteUser() {
		// TODO Auto-generated method stub
		try{
			SqlSession session = sqlSessionFactory.openSession();
			IUser iuser = session.getMapper(IUser.class);
			System.out.println("Test delete start ...");
			System.out.println("Before delete");
			printUsers(iuser.getUserList());
			iuser.deleteUser(2);
			session.commit();
			System.out.println("After delete");
			printUsers(iuser.getUserList());
			System.out.println("Test delete finished ...");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void testInsert(){
		try{
			SqlSession session = sqlSessionFactory.openSession();
			IUser userMapper = session.getMapper(IUser.class);
			System.out.println("Test insert start...");
			User user = new User();
			user.setId(0);
			user.setName("Google");
			user.setDept("Tech");
			user.setWebsite("http:www.google.com");
			user.setPhone("17853728804");
			userMapper.insertUser(user);
			session.commit();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void getUserList(){
		try{
			SqlSession session = sqlSessionFactory.openSession();
			IUser iuser = session.getMapper(IUser.class);
			System.out.println("Test Get start");
			printUsers(iuser.getUserList());
			System.out.println("Test Get finish");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	private static void printUsers(final List<User> users) {
		// TODO Auto-generated method stub
		int count = 0;
		for (User user: users){
			System.out.println(MessageFormat.format("++++++++++", ++count));
			System.out.println("User Id:"+ user.getId());
			System.out.println("User Name:"+ user.getName());
			System.out.println("User Dept:"+ user.getDept());
			System.out.println("User Website:"+ user.getWebsite());
		}
	}
}
