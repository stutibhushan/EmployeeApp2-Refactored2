package com.EmployeeApp.daoInterface;

import java.sql.SQLException;

public interface EmployeeTypeDAO {
      
	public int addEmployeeType(int employeeId,int typeId);
	
	public int updateEmployeeType(int employeeId, int typeId);
	
	public int deleteEmployeeType(int employeeId);
	
}
