package com.EmployeeApp.service;

import java.sql.SQLException;

import com.EmployeeApp.daoInterface.EmployeeTypeDAO;
import com.EmployeeApp.serviceInterface.EmployeeTypeService;

public class EmployeeTypeServiceImpl implements EmployeeTypeService {
    
	private EmployeeTypeDAO employeeTypeDAO;
	
	@Override
	public boolean addEmployeeType(int employeeId, int typeId) {
		
		
		  int addTypeStatus=employeeTypeDAO.addEmployeeType(employeeId,typeId);
		  if(addTypeStatus==0)
		     {
		      return false;
		     }
		return true;
		
	}

	@Override
	public int updateEmployeeType(int employeeId, int typeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmployeeType(int employeeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public EmployeeTypeDAO getEmployeeTypeDAO() {
		return employeeTypeDAO;
	}

	public void setEmployeeTypeDAO(EmployeeTypeDAO employeeTypeDAO) {
		this.employeeTypeDAO = employeeTypeDAO;
	}
 
	
	
}
