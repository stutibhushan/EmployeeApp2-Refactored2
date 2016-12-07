package com.EmployeeApp.serviceInterface;

import java.util.ArrayList;
import java.util.List;

import com.EmployeeApp.model.Department;

public interface DepartmentService {
	
    public List<Department> getDepartmentList();
	
	public int updateEmployeeDepartmentDetail(ArrayList<Integer>departmentId,int employeeId);
	
	public boolean addEmployeeDepartmentDetail(ArrayList<Integer> departmentIdList, int employeeId);
	
	public int deleteEmployeeDepartmentDetail(int employeeId);

}
