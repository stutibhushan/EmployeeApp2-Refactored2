package com.EmployeeApp.daoInterface;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.EmployeeApp.model.Department;

public interface DepartmentDAO {
	
	public List<Department> getDepartmentList();
	
	public int updateEmployeeDepartmentDetail(ArrayList<Integer>departmentId,int employeeId);
	
	public int addEmployeeDepartmentDetail(ArrayList<Integer> departmentList, int employeeId);
	
	public int deleteEmployeeDepartmentDetail(int employeeId);

}
