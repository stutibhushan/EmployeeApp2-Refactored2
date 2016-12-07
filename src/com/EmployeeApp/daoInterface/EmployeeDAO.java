package com.EmployeeApp.daoInterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.EmployeeApp.model.Employee;
import com.EmployeeApp.model.EmployeeType;
import com.EmployeeApp.model.EmployeeVO;

public interface EmployeeDAO {
	
	public int addEmployee(Employee employee);
	
	public int getCurrentEmployeeId(String name);
	
	public List<EmployeeVO> getEmployeeByType(int employeeTypeId,int start,int limit);
	
	public int getEmployeeCount(int employeeTypeId);
	
	public List<EmployeeType> getListTypes();
	
    public int updateEmployee(Employee employee, int id);
	
	public int deleteEmployee(int employeeId);
	
	public int bulkDeleteEmployee(ArrayList<Integer> employeeIds);

}
