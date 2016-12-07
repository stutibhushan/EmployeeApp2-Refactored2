package com.EmployeeApp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.EmployeeApp.dao.EmployeeDAOImpl;
import com.EmployeeApp.daoInterface.EmployeeDAO;
import com.EmployeeApp.model.Employee;
import com.EmployeeApp.model.EmployeeType;
import com.EmployeeApp.model.EmployeeVO;
import com.EmployeeApp.serviceInterface.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
    
	private  EmployeeDAO employeeDAO;
	
	
	
	@Override
	public int addEmployee(Employee employee) {
		
		int employeeId=0;
		if(employee==null)
		{
			throw new RuntimeException();
		}
			
		
		employeeId = employeeDAO.addEmployee(employee);
			
		return employeeId;
		
	}

	@Override
	public int getCurrentEmployeeId(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EmployeeVO> getEmployeeByType(int employeeTypeId, int start, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getEmployeeCount(int employeeTypeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EmployeeType> getListTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateEmployee(Employee employee, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int bulkDeleteEmployee(ArrayList<Integer> employeeIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	

}
