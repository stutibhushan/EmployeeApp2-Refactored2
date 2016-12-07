package com.EmployeeApp.serviceInterface;

import java.sql.SQLException;

public interface EmployeeTypeService {
	
    public boolean addEmployeeType(int employeeId,int typeId);
	
	public int updateEmployeeType(int employeeId, int typeId);
	
	public int deleteEmployeeType(int employeeId);

}
