package com.EmployeeApp.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.EmployeeApp.daoInterface.DepartmentDAO;


public class DepartmentServiceImplTest {

	@Mock
	DepartmentDAO departmentDAO;
	
	private DepartmentServiceImpl departmentService;
	
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		
		departmentService= new DepartmentServiceImpl();
		
		departmentService.setDepartmentDAO(departmentDAO);
		
	 }
	
	//Test for positive scenario when employee detail is being added
	@Test
	public void addDepartmentShouldAddADepartment() throws SQLException {
		
		ArrayList<Integer> departmentIdList= new ArrayList<>();
		
		departmentIdList.add(101);
		
		departmentIdList.add(102);
		
		int employeeId=1;
		
		when(departmentDAO.addEmployeeDepartmentDetail(departmentIdList, employeeId)).thenReturn(new Integer(1));
		
		
		boolean result= departmentService.addEmployeeDepartmentDetail(departmentIdList, employeeId);
		
		assertTrue("department detail should be added", result);
		
		verify(departmentDAO).addEmployeeDepartmentDetail(departmentIdList, employeeId);
		
		
		
	}
	
	
	
	
	
	//Test for negative scenario when department detail is not being added
	@Test
	public void addDepartmentShouldNotAddADepartment() throws SQLException
	{
       ArrayList<Integer> departmentIdList= new ArrayList<>();
		
        departmentIdList.add(101);
		
		departmentIdList.add(102); 
		
		int employeeId=1;
		
		when(departmentDAO.addEmployeeDepartmentDetail(departmentIdList, employeeId)).thenReturn(new Integer(0));
		
		
		boolean result= departmentService.addEmployeeDepartmentDetail(departmentIdList, employeeId);
		
		assertFalse("department detail should not be added", result);
		
		verify(departmentDAO).addEmployeeDepartmentDetail(departmentIdList, employeeId);
		
	}
	
   
	//Test for exception when employeeId is negative
	
	@Test(expected=RuntimeException.class)
	public void addDepartmentShouldThrowExceptionWhenEmployeeIdIsNegative()
	{
		
        ArrayList<Integer> departmentIdList= new ArrayList<>();
        
        departmentIdList.add(101);
		
		departmentIdList.add(102); 
		
		int employeeId=-1;
		
		when(departmentDAO.addEmployeeDepartmentDetail(departmentIdList, employeeId)).thenThrow(RuntimeException.class);
		
		
		boolean result= departmentService.addEmployeeDepartmentDetail(departmentIdList, employeeId);
		
		
	}
	
	
	//Test For exception when departmentIdList is empty
	
	@Test(expected=RuntimeException.class)
	public void addDepartmentShouldThrowExceptionWhenDepartmentListIsEmpty()
	{
		
        ArrayList<Integer> departmentIdList= new ArrayList<>();
        
      
		
		int employeeId=1;
		
		when(departmentDAO.addEmployeeDepartmentDetail(departmentIdList, employeeId)).thenThrow(RuntimeException.class);
		
		
		boolean result= departmentService.addEmployeeDepartmentDetail(departmentIdList, employeeId);
		
	}
	
	
	@After
	public void cleanUp()
	{
		departmentService=null;
	}

}
