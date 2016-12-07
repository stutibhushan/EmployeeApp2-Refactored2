package com.EmployeeApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.EmployeeApp.daoInterface.DepartmentDAO;
import com.EmployeeApp.model.Department;

public class DepartmentDAOImpl implements DepartmentDAO {
	
	private Connection connection;
	
	public DepartmentDAOImpl() {
		super();
		connection=DBUtils.getConnection();
	}
	
	
	//Make use of static factory method instead of constructors
	public static DepartmentDAO getDepartmentDAO()
	{
		return new DepartmentDAOImpl();
	}

	@Override
	public List<Department> getDepartmentList() {
		
          List<Department> departmentList= new ArrayList<>();
		
		
		      try
		        {
			        PreparedStatement ps;
			        ps= connection.prepareStatement("select * from department");
			        ResultSet rs= ps.executeQuery();
			        while(rs.next())
			           {
				
				        Department department=new Department();
				        department.setId(rs.getInt("id"));
				        department.setDepartmentName(rs.getString("department_name"));
				        departmentList.add(department);
				
			           }
			    }
		       catch(Exception e)
		       {

			      System.out.println("Unable to get department list");
			      e.printStackTrace();
			
		       }
		
		return departmentList;
		
	}

	@Override
	public int updateEmployeeDepartmentDetail(ArrayList<Integer> departmentId, int id) {
		
		  int status=-1;
		  
		  try
		  {
			  PreparedStatement ps= connection.prepareStatement("delete from employee_department_detail where employee_id=?");
			  ps.setInt(1, id);
			  ps.executeUpdate();
			  for(int i=0; i<departmentId.size();i++)
			  {
				  ps=connection.prepareStatement("insert into employee_department_detail(employee_id,department_id) values(?,?)");
				  ps.setInt(1, id);
				  ps.setInt(2, departmentId.get(i));
				  status=ps.executeUpdate();
			  }
		  }
		  catch(Exception e)
		  {
			  
		  }
		  
		  return status;

	}

	@Override
	public int addEmployeeDepartmentDetail(ArrayList<Integer> departmentList, int employeeId){
	    
		int status=0;
		
		try{
		  for(int i=0; i< departmentList.size(); i++)
		     {
			
		      
			        PreparedStatement ps;
			       /* ps= connection.prepareStatement("select id from employee where name=? ");
			        ps.setString(1, name);
			        ResultSet rs1= ps.executeQuery();
			        if(rs1.next())
			          {
			            System.out.println(rs1.getInt("id"));
				        id=rs1.getInt("id");
			          }
			        else
		           	{
				        System.out.println("No employee with corresponding name exits");
			           	throw new Exception();
		        	}*/
			
			
			        ps= connection.prepareStatement("insert into employee_department_detail(employee_id,department_id) values(?,?) ");
			        ps.setInt(1,employeeId);
			        ps.setInt(2, departmentList.get(i));
			        status=ps.executeUpdate();
		
	          }
		}
		 catch(Exception e)
		{
			 
		}
		
		return status;
	}


	@Override
	public int deleteEmployeeDepartmentDetail(int employeeId) {
		int status=0;
		PreparedStatement ps;
		try
		{
			ps=connection.prepareStatement("delete from employee_department_detail where employee_id=?");
    		ps.setInt(1, employeeId);
    		status=ps.executeUpdate();
    		System.out.println("employee department delete dao"+status);
		}
		catch(Exception e)
		{
			
		}
		return status;
	}

}
