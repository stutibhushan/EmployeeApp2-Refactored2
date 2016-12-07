package com.EmployeeApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.EmployeeApp.daoInterface.EmployeeTypeDAO;

public class EmployeeTypeDAOImpl implements EmployeeTypeDAO {
    
	private Connection connection=DBUtils.getConnection();
	
	public EmployeeTypeDAOImpl() {
		super();
	}
	
	public static EmployeeTypeDAO getEmployeeTypeDAO()
	{
		return new EmployeeTypeDAOImpl();
	}

	@Override
	public int addEmployeeType(int employeeId, int typeId) {
		
		    int status=-1;
		    try
		    {
			PreparedStatement ps=connection.prepareStatement("insert into employee_type_detail(employee_id,type_id)values(?,?)");
			ps.setInt(1,employeeId);
			ps.setInt(2, typeId);
			status=ps.executeUpdate();
		    }
		    catch(Exception e)
		    {
		    
		    }
		    return status;
	}

	@Override
	public int updateEmployeeType(int employeeId, int typeId) {
		
		int status=0;
		PreparedStatement ps;
		try
		{
			ps=connection.prepareStatement("update employee_type_detail set type_id=? where employee_id=?");
		    ps.setInt(1, typeId);
		    ps.setInt(2, employeeId);
		    status=ps.executeUpdate();
		}
		catch(Exception e)
		{
			
		}
		return status;
	}

	@Override
	public int deleteEmployeeType(int employeeId) {
		
		int status=0;
		PreparedStatement ps;
		try
		{
		    ps= connection.prepareStatement("delete from employee_type_detail where employee_id=?");
    		ps.setInt(1, employeeId);
    		status=ps.executeUpdate();
    		System.out.println("employee type delete dao"+status);
		}
		catch(Exception e)
		{
			
		}
		return status;
	}

}
