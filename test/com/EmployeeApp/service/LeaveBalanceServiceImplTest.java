package com.EmployeeApp.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import com.EmployeeApp.daoInterface.LeaveBalanceDAO;

public class LeaveBalanceServiceImplTest {
   
	@Mock
	LeaveBalanceDAO leaveBalanceDAO;
	
	LeaveBalanceServiceImpl leaveBalanceService;
	
	
	@Before
	public void setup()
	{
        MockitoAnnotations.initMocks(this);
		
		leaveBalanceService= new LeaveBalanceServiceImpl();
		
		leaveBalanceService.setLeaveBalanceDAO(leaveBalanceDAO);
	}
	
	//Test for posotive scenario when employee leave balance is being added
	
	@Test
	public void addLeaveBalanceShouldAddLeave()
	{
		int employeeId=1;
		
		when(leaveBalanceDAO.addLeaveBalanceDetail(employeeId)).thenReturn(new Integer(1));
		
		boolean result= leaveBalanceService.addLeaveBalanceDetail(employeeId);
		
		assertTrue(result);
		
		verify(leaveBalanceDAO).addLeaveBalanceDetail(employeeId);
	}

}
