package com.EmployeeApp.result;


import java.util.List;

import com.EmployeeApp.model.Department;
import com.EmployeeApp.model.EmployeeVO;

public class ExtDepartmentResult {
	
	//private int totalCount;
	private List<Department> departmentList;
	
	
	public ExtDepartmentResult(List<Department> departmentList) {
		//super();
		//this.totalCount = totalCount;
		this.departmentList = departmentList;
	}
	
	

}
