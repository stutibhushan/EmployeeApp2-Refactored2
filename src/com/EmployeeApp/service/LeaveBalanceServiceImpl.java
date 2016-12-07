package com.EmployeeApp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.EmployeeApp.daoInterface.LeaveBalanceDAO;
import com.EmployeeApp.model.EmployeeLeaveBalancePOJO;
import com.EmployeeApp.model.LeaveDisplayAdminPOJO;
import com.EmployeeApp.model.LeaveDisplayEmployeePOJO;
import com.EmployeeApp.serviceInterface.LeaveBalanceService;

public class LeaveBalanceServiceImpl implements LeaveBalanceService {
    
	private LeaveBalanceDAO leaveBalanceDAO;
	
	@Override
	public boolean addLeaveBalanceDetail(int employeeId) {
		 
		  if(employeeId<0)
		  {
			  throw new RuntimeException();
		  }
           		
		   int addLeaveBalanceStatus= leaveBalanceDAO.addLeaveBalanceDetail(employeeId);
		   if(addLeaveBalanceStatus==0)
		   {
			   return false;
		   }
		
		return true; 
	}

	@Override
	public List<EmployeeLeaveBalancePOJO> listEmployeeLeaveBalance(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int applyLeave(String leaveType, int employeeId, ArrayList<Date> applyLeaveDates) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LeaveDisplayAdminPOJO> getAppliedLeave(int start, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<LeaveDisplayEmployeePOJO> getAppliedLeave(int start, int limit, int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalCount(int employeeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int acceptLeave(int employeeId, String leaveType, Date date) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rejectLeave(int employeeId, String leaveType, Date date) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployeeLeaveDetail(int employeeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public LeaveBalanceDAO getLeaveBalanceDAO() {
		return leaveBalanceDAO;
	}

	public void setLeaveBalanceDAO(LeaveBalanceDAO leaveBalanceDAO) {
		this.leaveBalanceDAO = leaveBalanceDAO;
	}
	
  

}
