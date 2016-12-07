
package com.EmployeeApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.EmployeeApp.daoInterface.DepartmentDAO;
import com.EmployeeApp.daoInterface.LeaveBalanceDAO;
//import com.EmployeeApp.model.AdminLeaveDisplayPOJO;
import com.EmployeeApp.model.EmployeeLeaveBalancePOJO;
//import com.EmployeeApp.model.EmployeeLeaveDisplayPOJO;
//import com.EmployeeApp.model.LeaveDisplayAdminPOJO;
import com.EmployeeApp.model.LeaveDisplayAdminPOJO;
import com.EmployeeApp.model.LeaveDisplayEmployeePOJO;
import java.util.Calendar;
//import java.util.Date;
import java.util.GregorianCalendar;

public class LeaveBalanceDAOImpl implements LeaveBalanceDAO {

	private Connection connection=DBUtils.getConnection();
	
	
	
	
	public LeaveBalanceDAOImpl() {
		super();
	}
    
	//Make use of static factory method instead of constructors
	public static LeaveBalanceDAO getLeaveBalanceDAO()
	{
		return new LeaveBalanceDAOImpl();
	}
	

	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
				
		return cal.getTime();
	}

	@Override
	public List<EmployeeLeaveBalancePOJO> listEmployeeLeaveBalance(int employeeId)
	{
		PreparedStatement ps;
		List<EmployeeLeaveBalancePOJO> employeeLeaveBalanceList= new ArrayList<>();
		
		try
		{   
			
			if(employeeId!=0)
			{
			ps=connection.prepareStatement("select * from employee_leavebalance_detail where employee_id=?");
			ps.setInt(1, employeeId);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{
				EmployeeLeaveBalancePOJO employeeLeaveBalancePOJO= new EmployeeLeaveBalancePOJO();
				employeeLeaveBalancePOJO.setEmployeeId(rs.getInt("employee_id"));
				employeeLeaveBalancePOJO.setLeaveType(rs.getString("leave_type"));
				employeeLeaveBalancePOJO.setCredit(rs.getInt("credit"));
				employeeLeaveBalancePOJO.setAvailed(rs.getInt("availed"));
				employeeLeaveBalancePOJO.setFinalBalance(rs.getInt("finalbalance"));
				employeeLeaveBalanceList.add(employeeLeaveBalancePOJO);
				
			}
			}
			else
			{
				ps=connection.prepareStatement("select * from employee_leavebalance_detail");
				
				ResultSet rs= ps.executeQuery();
				while(rs.next())
				{
					EmployeeLeaveBalancePOJO employeeLeaveBalancePOJO= new EmployeeLeaveBalancePOJO();
					employeeLeaveBalancePOJO.setEmployeeId(rs.getInt("employee_id"));
					employeeLeaveBalancePOJO.setLeaveType(rs.getString("leave_type"));
					employeeLeaveBalancePOJO.setCredit(rs.getInt("credit"));
					employeeLeaveBalancePOJO.setAvailed(rs.getInt("availed"));
					employeeLeaveBalancePOJO.setFinalBalance(rs.getInt("finalbalance"));
					employeeLeaveBalanceList.add(employeeLeaveBalancePOJO);
					
				}
			}
			
		}
		catch(Exception e)
		{
			
		}
		return employeeLeaveBalanceList;
		
	}
	
	@Override
	public int applyLeave(String leaveType, int employeeId, ArrayList<Date> applyLeaveDates)
	{
		int status=0;
		try
		{
			PreparedStatement ps, ps1, ps2;
			int noOfDays=applyLeaveDates.size();
			for(int i=0; i<applyLeaveDates.size();i++)
			{
				ps1=connection.prepareStatement("insert into leave_display_admin(employee_id, leave_type, date, status) values(?,?,?,?)");
				ps1.setInt(1, employeeId);
				ps1.setString(2, leaveType );
				ps1.setDate(3, new java.sql.Date(applyLeaveDates.get(i).getTime()));
				ps1.setString(4, "pending");
				int status1=ps1.executeUpdate();
				
				ps2=connection.prepareStatement("insert into leave_display_employee(employee_id, leave_type, date, status) values(?,?,?,?)");
				ps2.setInt(1, employeeId);
				ps2.setString(2, leaveType );
				ps2.setDate(3, new java.sql.Date(applyLeaveDates.get(i).getTime()));
				ps2.setString(4, "pending");
				int status2=ps2.executeUpdate();
				
				
				
				if(status1!=0 && status2!=0)
				{
					status=1;
				}
			}
			
			ps=connection.prepareStatement("select * from employee_leavebalance_detail where employee_id=? && leave_type=?");
			ps.setInt(1, employeeId);
			ps.setString(2, leaveType);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int finalBalance= rs.getInt("finalbalance");
				System.out.println("final balance--------->"+finalBalance);
				int availed= rs.getInt("availed");
				System.out.println(availed);
				int credit= rs.getInt("credit");
				
					int new_availed=availed+noOfDays;
					System.out.println("new availed----->>"+new_availed);
					
					int new_finalBalance=finalBalance-noOfDays;
					System.out.println("new finalBalance---->>"+new_finalBalance);
					ps=connection.prepareStatement("update employee_leavebalance_detail set availed=?, finalbalance=? where employee_id=? && leave_type=?");
					ps.setInt(1, new_availed);
					ps.setInt(2, new_finalBalance);
					ps.setInt(3, employeeId);
					ps.setString(4, leaveType);
					ps.executeUpdate();
				}
			
	
			
			
		}
		catch(Exception e)
		{
			System.out.println("unable to apply leaves"+ e.getMessage());
			
		}
		return status;
	}
	
	@Override
	public List<LeaveDisplayAdminPOJO>  getAppliedLeave(int start, int limit)
	{
		
		List<LeaveDisplayAdminPOJO> appliedLeave= new ArrayList<>();
		try
		{
			PreparedStatement ps= connection.prepareStatement("select * from leave_display_admin limit ?,?");
			ps.setInt(1, start);
			ps.setInt(2, limit);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				LeaveDisplayAdminPOJO leaveDisplay= new LeaveDisplayAdminPOJO();
				leaveDisplay.setEmployeeId(rs.getInt("employee_id"));
				leaveDisplay.setLeaveType(rs.getString("leave_type"));
				leaveDisplay.setDate(rs.getDate("date"));
				leaveDisplay.setStatus(rs.getString("status"));
				appliedLeave.add(leaveDisplay);
			}
			
		}
		catch(Exception e)
		{
			
		}
		return appliedLeave;
	}
	
	
	@Override
	public int getTotalCount()
	{
		int count=0;
		try
		{
			PreparedStatement ps=connection.prepareStatement("select count(*) from leave_display_admin");
			 ResultSet rs=ps.executeQuery();
			 while(rs.next())
			 {
				 count=rs.getInt(1);
			 }
			 
		}
		catch(Exception e)
		{
			
		}
		return count;
	}
	
	
	@Override
	public List<LeaveDisplayEmployeePOJO> getAppliedLeave(int start, int limit, int employeeId)
	{   
		
		
		List<LeaveDisplayEmployeePOJO> appliedLeave= new ArrayList<>();
		
		try
		{
			
			
			PreparedStatement ps;
			if(employeeId != 0)
			{  
				
				
				ps= connection.prepareStatement("select * from leave_display_employee where employee_id=? limit ?,?");
				ps.setInt(1, employeeId);
				ps.setInt(2, start);
				ps.setInt(3, limit);
				ResultSet rs=ps.executeQuery();
				while(rs.next())
				{
					LeaveDisplayEmployeePOJO leaveDisplay= new LeaveDisplayEmployeePOJO();
					leaveDisplay.setEmployeeId(rs.getInt("employee_id"));
					leaveDisplay.setLeaveType(rs.getString("leave_type"));
					leaveDisplay.setDate(rs.getDate("date"));
					
					leaveDisplay.setStatus(rs.getString("status"));
					appliedLeave.add(leaveDisplay);
				}
				
			}
			else
			{  
				
				ps=connection.prepareStatement("select * from leave_display_employee limit ?,?");
				ps.setInt(1, start);
				ps.setInt(2, limit);
				
				ResultSet rs=ps.executeQuery();
				
				while(rs.next())
				{
					LeaveDisplayEmployeePOJO leaveDisplay= new LeaveDisplayEmployeePOJO();
					leaveDisplay.setEmployeeId(rs.getInt("employee_id"));
					leaveDisplay.setLeaveType(rs.getString("leave_type"));
					leaveDisplay.setDate(rs.getDate("date"));
					
					leaveDisplay.setStatus(rs.getString("status"));
					appliedLeave.add(leaveDisplay);
				}
				
			}
		}
	
		catch(Exception e)
		{
			System.out.println("cannot get employee applied leave details"+e.getMessage());
		}
		
		return appliedLeave;
	}



	@Override
	public int getTotalCount(int employeeId)
	{
		int count=0;
		
		try
		{   
			PreparedStatement ps;
			if(employeeId!=0)
			{
				
				ps= connection.prepareStatement("select count(*) from leave_display_employee where employee_Id=?");
				ps.setInt(1, employeeId);
				 ResultSet rs=ps.executeQuery();
				 while(rs.next())
				 {
					 count=rs.getInt(1);
				 }
				
				
			}
			else
			{
			
			ps= connection.prepareStatement("select count(*) from leave_display_employee");	
			
			 ResultSet rs=ps.executeQuery();
			 while(rs.next())
			 {
				 count=rs.getInt(1);
				
			 }
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
			
		}
		return count;
	}
	
	
	@Override
	public int acceptLeave(int employeeId, String leaveType, Date date)
	{
		int status=0;
		try
		{
			PreparedStatement ps;
			ps=connection.prepareStatement("update leave_display_employee set status=? where employee_id=? && leave_type=? && date=?");
			ps.setString(1, "accepted");
			ps.setInt(2, employeeId);
			ps.setString(3, leaveType);
			ps.setDate(4, new java.sql.Date(date.getTime()));
			int status1=ps.executeUpdate();
			
			ps=connection.prepareStatement("delete from leave_display_admin where employee_id=? && leave_type=? && date=?");
			ps.setInt(1, employeeId);
			ps.setString(2, leaveType);
			ps.setDate(3, new java.sql.Date(date.getTime()));
			int status2= ps.executeUpdate();
			if(status1!=0 && status2!=0)
			{
				status=1;
			}
			
		}
		catch(Exception e)
		{
			
		}
		return status;
	}
	
	
	@Override
	public int rejectLeave(int employeeId, String leaveType, Date date)
	{
		int status=0;
		int status1; 
		int status2; 
		int status3;
		try
		{
			PreparedStatement ps;
			ps=connection.prepareStatement("update leave_display_employee set status=? where employee_id=? && leave_type=? && date=?");
			ps.setString(1, "rejected");
			ps.setInt(2, employeeId);
			ps.setString(3, leaveType);
			ps.setDate(4, new java.sql.Date(date.getTime()));
			 status1=ps.executeUpdate();
			
			ps=connection.prepareStatement("delete from leave_display_admin where employee_id=? && leave_type=? && date=?");
			ps.setInt(1, employeeId);
			ps.setString(2, leaveType);
			ps.setDate(3, new java.sql.Date(date.getTime()));
			 status2= ps.executeUpdate();
			
			
			ps=connection.prepareStatement("select * from employee_leavebalance_detail where employee_id=? && leave_type=?");
			ps.setInt(1, employeeId);
			ps.setString(2, leaveType);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				int finalBalance= rs.getInt("finalbalance");
				System.out.println("final balance--------->"+finalBalance);
				int availed= rs.getInt("availed");
				System.out.println(availed);
				int credit= rs.getInt("credit");
				
					int new_availed=availed-1;
					System.out.println("new availed----->>"+new_availed);
					
					int new_finalBalance=finalBalance+1;
					System.out.println("new finalBalance---->>"+new_finalBalance);
					ps=connection.prepareStatement("update employee_leavebalance_detail set availed=?, finalbalance=? where employee_id=? && leave_type=?");
					ps.setInt(1, new_availed);
					ps.setInt(2, new_finalBalance);
					ps.setInt(3, employeeId);
					ps.setString(4, leaveType);
				    status3=ps.executeUpdate();
				}
			
			if(status1!=0 && status2!=0)
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
	
	
	@Override
	public int addLeaveBalanceDetail(int employeeId)
	{
		int status=0;
		PreparedStatement ps;
		
		 try
		 {
			 ps=connection.prepareStatement("insert into employee_leavebalance_detail(employee_id, leave_type, credit, availed, finalbalance) values(?, 'CASUAL\\SICK LEAVE', 5, 0, 5)");
			 
			 ps.setInt(1,employeeId );
		     ps.executeUpdate();
		     ps=connection.prepareStatement("insert into employee_leavebalance_detail(employee_id, leave_type, credit, availed, finalbalance) values(?, 'EARNED LEAVE', 0, 0, 0)");
			 ps.setInt(1, employeeId);
			 ps.executeUpdate();
			 ps=connection.prepareStatement("insert into employee_leavebalance_detail(employee_id, leave_type, credit, availed, finalbalance) values(?, 'MATERNITY LEAVE', 84, 0, 84)");
			 ps.setInt(1, employeeId);
			 ps.executeUpdate();
			 ps=connection.prepareStatement("insert into employee_leavebalance_detail(employee_id, leave_type, credit, availed, finalbalance) values(?, 'VOLUNTARY DAY', 1, 0, 1)");
			 ps.setInt(1, employeeId);
			 status=ps.executeUpdate();
		 }
		 catch(Exception e)
		 {
			 
		 }
		
		     return status;
		
	}

	@Override
	public int deleteEmployeeLeaveDetail(int employeeId) {
		
		int status=0;
		
		PreparedStatement ps;
		try
		{
			ps=connection.prepareStatement("delete from employee_leavebalance_detail where employee_id=?");
    		ps.setInt(1, employeeId);
    		int employeeLeaveBalanceStatus=ps.executeUpdate();
    		ps=connection.prepareStatement("delete from leave_display_admin where employee_id=?");
    		ps.setInt(1, employeeId);
    		int leaveDisplayAdminStatus=ps.executeUpdate();
    		ps=connection.prepareStatement("delete from leave_display_employee where employee_id=?");
    		ps.setInt(1, employeeId);
    		int leaveDisplayEmployeeStatus=ps.executeUpdate();
    		
    		if(employeeLeaveBalanceStatus !=0)
    		{
    			status=1;
    		}
    		
		}
		catch(Exception e)
		{
			
		}
		return status;
	}
}
