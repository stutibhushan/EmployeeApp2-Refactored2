package com.EmployeeApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.EmployeeApp.model.UserPOJO;






public class LoginDao {
	
	private Connection connection=DBUtils.getConnection();
	
	public LoginDao()
	{
		super();
	}

	public int verifyUser(String userName, String password)
	{
		UserPOJO user= new UserPOJO();
		int status=0;
		try
		{
			PreparedStatement ps;
			ps= connection.prepareStatement("select * from user where username=? && userPassword=?");
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				
				status=1;
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return status;
	}
}
