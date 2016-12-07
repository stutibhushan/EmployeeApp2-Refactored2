package com.EmployeeApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.EmployeeApp.dao.DBUtils;
import com.EmployeeApp.daoInterface.UserDAOInterface;



public class UserDAO implements UserDAOInterface {
	
	static Logger log = Logger.getLogger(UserDAO.class.getName());
	private Connection connection=DBUtils.getConnection();
	
	public UserDAO()
	{
		//TODO Intialize resources here
	}
	
	public static  UserDAO getDAOObject() {
		
		return new UserDAO();
	}
	
	public int authenticate(String username,String password)
	{
		log.info(new Date()+ " in authenticate "+UserDAO.class);
		int status=0;
		try
		{
			PreparedStatement ps=connection.prepareStatement("select * from user where username=? and userPassword=?");
			ps.setString(1,username);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
			status=1;
			}
		}catch(SQLException se)
		{
			log.error("unable to authenticate user..."+se.getMessage());
			System.out.println("unable to authenticate user...");
			se.printStackTrace();
		}
	return status;
	}
	
	
	

}
