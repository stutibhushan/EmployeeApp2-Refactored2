


package com.EmployeeApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.EmployeeApp.daoInterface.DepartmentDAO;
import com.EmployeeApp.daoInterface.EmployeeDAO;
import com.EmployeeApp.model.Employee;
import com.EmployeeApp.model.EmployeeType;
import com.EmployeeApp.model.EmployeeVO;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	
	private Connection connection=DBUtils.getConnection();
	
	public EmployeeDAOImpl()
	{
		
	}
	
	//Make use of static factory method instead of constructors
	public static EmployeeDAO getEmployeeDAO()
	{
		return new EmployeeDAOImpl();
	}
	
	@Override
	public int addEmployee(Employee employee)
	{
	    int employeeId=0;
		int status=0;
		
		try
		{
			PreparedStatement ps=connection.prepareStatement("insert into employee(name,start_date,end_date,description,salary,address,city,state,country) values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1, employee.getName());
			ps.setDate(2, new java.sql.Date(employee.getStartDate().getTime()));
			ps.setDate(3, new java.sql.Date(employee.getEndDate().getTime()));
			ps.setString(4, employee.getDescription());
			ps.setDouble(5, employee.getSalary());
			ps.setString(6, employee.getAddress());
			ps.setString(7,employee.getCity());
			ps.setString(8, employee.getState());
			ps.setString(9,employee.getCountry());
			status=ps.executeUpdate();
			if( status !=0)
			{
				employeeId=getCurrentEmployeeId(employee.getName());
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return employeeId;
	}
	
	@Override
	public int getCurrentEmployeeId(String name)
	{
		int id=-1;
		try
		{
			PreparedStatement ps=connection.prepareStatement("select id from employee where name=?");
			ps.setString(1, name);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				id=rs.getInt("id");
			}
			
		}catch(Exception e)
		{
			System.out.println("unable to get current employee");
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	public List<EmployeeVO> getEmployeeByType(int employeeTypeId,int start,int limit)
	{
		System.out.println("-------------------------------------------------------------");

		System.out.println(employeeTypeId);
		
		List<EmployeeVO> employees=new ArrayList<>();
		
		boolean isFound=false;
		
		
		try{
			
			PreparedStatement ps;
			if(employeeTypeId==4)
			{
				ps=connection.prepareStatement("select employee.id as id, name , description, start_date as startDate, end_Date as endDate, type_name as type, salary, address, city, state, country from employee,employee_type,employee_type_detail where employee.id=employee_type_detail.employee_id and employee_type.id=employee_type_detail.type_id limit ?,?");
				ps.setInt(1, start);
				ps.setInt(2, limit);
			}
			else{
		ps=connection.prepareStatement("select employee.id as id, name , description, start_date as startDate, end_Date as endDate, type_name as type, salary, address, city, state, country from employee,employee_type,employee_type_detail where employee.id=employee_type_detail.employee_id and employee_type.id=employee_type_detail.type_id and  employee_type.id=? limit ?,?");
		ps.setInt(1, employeeTypeId);
		ps.setInt(2,start);
		ps.setInt(3, limit);
				}
			
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{   
				int count=10;
				isFound=false;
				EmployeeVO employeeVO=new EmployeeVO();
				employeeVO.setId(rs.getInt("id"));
				employeeVO.setName(rs.getString("name"));
				employeeVO.setDescription(rs.getString("description"));
				employeeVO.setStartDate(rs.getDate("startDate"));
				employeeVO.setEndDate(rs.getDate("endDate"));
				employeeVO.setType(rs.getString("type"));
				employeeVO.setSalary(rs.getDouble("salary"));
				employeeVO.setAddress(rs.getString("address"));
				employeeVO.setCity(rs.getString("city"));
				
				employeeVO.setState(rs.getString("state"));
				employeeVO.setCountry(rs.getString("country"));
				
				if(employeeVO.getName().equals(""))
				{
					count--;
				}
				if(employeeVO.getDescription().equals(""))
				{
					count--;
				}
				if(employeeVO.getAddress().equals(""))
				{
					count--;
				}
				if(employeeVO.getCity().equals(""))
				{
					count--;
				}
				if(employeeVO.getState().equals(""))
				{
					count--;
				}
				if(employeeVO.getCountry().equals(""))
				{
					count--;
				}
				if(employeeVO.getSalary()==0)
				{
					count--;
				}
				if(employeeVO.getStartDate().equals(""))
				{
					count--;
				}
				if(employeeVO.getEndDate().equals(""))
				{
					count--;
				}
				if(employeeVO.getType().equals(""))
				{
					count--;
				}
				
				System.out.println("count"+employeeVO.getId()+" "+count);
				double profileCompleteness= (count/10.00)*100;
				System.out.println("profile Completeness"+ profileCompleteness);
				employeeVO.setProfileCompleteness(profileCompleteness);
				employees.add(employeeVO);
				System.out.println("count"+employeeVO.getId()+" "+count);
				
				/*if(employees.size()!=0)
				{
					
					for(EmployeeVO e:employees)
					{
						if(e.getId()==rs.getInt("id"))
						{
							e.getDepartmentList().add(rs.getString("departmentName"));
							isFound=true;
							break;
						}
						
					}
					
					
					
				}
				
					if(!isFound)
					{
						
					employeeVO.getDepartmentList().add(rs.getString("departmentName"));
					employees.add(employeeVO);
					}*/
					
				
			}
			
			
			
		}catch(SQLException se)
		{
			System.out.println("unable to fetch...");
			se.printStackTrace();
		}
		
		return employees;
		
	}
	
	
	@Override
	public int getEmployeeCount(int employeeTypeId)
	{
		int count=0;
		try{
			
		PreparedStatement ps;
		
		if(employeeTypeId==4)
		{
		 ps=connection.prepareStatement("select count(*) from employee,employee_type,employee_type_detail where employee.id=employee_type_detail.employee_id and employee_type.id=employee_type_detail.type_id");
		
		 ResultSet rs=ps.executeQuery();
		 while(rs.next())
		 {
			 count=rs.getInt(1);
		 }
		 
		}
		
		else
		{
			ps=connection.prepareStatement("select count(*) from employee,employee_type,employee_type_detail where employee.id=employee_type_detail.employee_id and employee_type.id=employee_type_detail.type_id and employee_type.id=?");
			ps.setInt(1, employeeTypeId);
			ResultSet rs=ps.executeQuery();
			 while(rs.next())
			 {
				 count=rs.getInt(1);
			 }
		
		}
	}catch(SQLException se)
		{
		System.out.println("Unable to fetch count...");
		se.printStackTrace();
		}
		
		return count;
	}
	
	@Override
	public List<EmployeeType> getListTypes()
	{
		List<EmployeeType> listTypes=new ArrayList<>();
		
		try{
			
			PreparedStatement ps;
			ps=connection.prepareStatement("select * from employee_type");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				
				EmployeeType employeeType=new EmployeeType();
				employeeType.setId(rs.getInt("id"));
				employeeType.setTypeName(rs.getString("type_name"));
				listTypes.add(employeeType);
			}
			
			
		}catch(SQLException se)
		{
			System.out.println("Unable to get list types...");
			se.printStackTrace();
		}
		
		
		
		
		return listTypes;
	}
	
	/*@Override
	public int addType(int id,int type)
	{   
		int status=-1;
		try
		{
			PreparedStatement ps=connection.prepareStatement("insert into employee_type_detail(employee_id,type_id)values(?,?)");
			ps.setInt(1,id);
			ps.setInt(2, type);
			status=ps.executeUpdate();
			
			
		}catch(Exception e)
		{
			System.out.println("unable to add type...");
			e.printStackTrace();
		}
		return status;
	}
    */
	@Override
    public int updateEmployee(Employee employee, int id)
     {    
    	  int status=0;
    	  
	      try
	       {
		     PreparedStatement ps= connection.prepareStatement("update employee set name=?,start_date=?,end_date=?,description=?,salary=?,address=?,city=?,state=?,country=? where id=?");
		     ps.setString(1, employee.getName());
			 ps.setDate(2, new java.sql.Date(employee.getStartDate().getTime()));
			 ps.setDate(3, new java.sql.Date(employee.getEndDate().getTime()));
			 ps.setString(4, employee.getDescription());
			 ps.setDouble(5, employee.getSalary());
			 ps.setString(6, employee.getAddress());
			 ps.setString(7,employee.getCity());
			 ps.setString(8, employee.getState());
			 ps.setString(9,employee.getCountry());
			 ps.setInt(10, id);
		     status=ps.executeUpdate();
		  
	      }
	     catch(Exception e)
	      {
		  
	      }
	    return status;
    }
    
	
	
	@Override
    public int deleteEmployee(int employeeId)
    {
    	int status=0;
    	PreparedStatement ps;
    	try
    	{   
    		//FUNCTIONS SHOULD DO ONE THING. THEY SHOULD DO IT WELL.
    		/*PreparedStatement ps= connection.prepareStatement("delete from employee_type_detail where employee_id=?");
    		ps.setInt(1, employeeId);
    		ps.executeUpdate();*/
    		/*ps=connection.prepareStatement("delete from employee_department_detail where employee_id=?");
    		ps.setInt(1, employeeId);
    		ps.executeUpdate();*/
    		/*ps=connection.prepareStatement("delete from employee_leavebalance_detail where employee_id=?");
    		ps.setInt(1, employeeId);
    		ps.executeUpdate();
    		ps=connection.prepareStatement("delete from leave_display_admin where employee_id=?");
    		ps.setInt(1, employeeId);
    		ps.executeUpdate();
    		ps=connection.prepareStatement("delete from leave_display_employee where employee_id=?");
    		ps.setInt(1, employeeId);
    		ps.executeUpdate();*/
    		
    		ps=connection.prepareStatement("delete from employee where id=?");
    		ps.setInt(1,  employeeId);
    		status= ps.executeUpdate();
    		System.out.println("employee delete dao"+status);
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println("unable to delete employee"+e.getMessage());
    	}
    	return status;
    }
    
	
	@Override
    public int bulkDeleteEmployee(ArrayList<Integer> employeeIds)
    {  
    	int status=0;
    	for(int i=0; i<employeeIds.size();i++)
    	{
    	try
    	{

    		PreparedStatement ps= connection.prepareStatement("delete from employee_type_detail where employee_id=?");
    		ps.setInt(1, employeeIds.get(i));
    		ps.executeUpdate();
    		ps=connection.prepareStatement("delete from employee_department_detail where employee_id=?");
    		ps.setInt(1, employeeIds.get(i));
    		ps.executeUpdate();
    		ps=connection.prepareStatement("delete from employee_leavebalance_detail where employee_id=?");
    		ps.setInt(1, employeeIds.get(i));
    		ps.executeUpdate();
    		ps=connection.prepareStatement("delete from leave_display_admin where employee_id=?");
    		ps.setInt(1, employeeIds.get(i));
    		ps.executeUpdate();
    		ps=connection.prepareStatement("delete from leave_display_employee where employee_id=?");
    		ps.setInt(1, employeeIds.get(i));
    		ps.executeUpdate();
    		ps=connection.prepareStatement("delete from approved_leaves where employee_id=?");
    		ps.setInt(1, employeeIds.get(i));
    		ps.executeUpdate();
    		ps=connection.prepareStatement("delete from employee where id=?");
    		ps.setInt(1,  employeeIds.get(i));
    		status= ps.executeUpdate();
    		
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	}
    	return status;
    }


	
	
}
