package com.EmployeeApp.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.EmployeeApp.daoInterface.EmployeeDAO;
import com.EmployeeApp.model.Employee;


public class EmployeeServiceImplTest {
    
	@Mock
	EmployeeDAO employeeDAO;
	
	private EmployeeServiceImpl employeeService;
	
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		
		employeeService= new EmployeeServiceImpl();
		
		employeeService.setEmployeeDAO(employeeDAO);
		
	 }
	
	//Test for positive scenario when employee detail is being added
	@Test
	public void addEmployeeShouldAddAnEmployee() throws SQLException {
		
		Employee employee= new Employee();
		
		when(employeeDAO.addEmployee(employee)).thenReturn(new Integer(1));
		
		int result= employeeService.addEmployee(employee);
		
		assertEquals("employee not added",result, 1);
		
		verify(employeeDAO).addEmployee(employee);
		
	}
	
	//Test for negative scenario when employee detail is not being added
	
	
	@Test
	public void addEmployeeShouldNotAddAnEmployee() throws SQLException
	{
		Employee employee= new Employee();
		
		when(employeeDAO.addEmployee(employee)).thenReturn(new Integer(0));
		
		int result= employeeService.addEmployee(employee);
		
		assertEquals("employee should not be added", result, 0);
		
		verify(employeeDAO).addEmployee(employee);
		
	}
	

  //Test for exception scenario when employee object is null
	
	@Test(expected=RuntimeException.class)
	public void addEmployeeShouldThrowException()
	{
		Employee employee=null;
		
		when(employeeDAO.addEmployee(employee)).thenThrow(RuntimeException.class);
		
		int result= employeeService.addEmployee(employee);
		
	}
	

}
