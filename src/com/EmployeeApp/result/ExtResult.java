package com.EmployeeApp.result;


import java.util.List;

import com.EmployeeApp.model.EmployeeVO;

public class ExtResult {
	
	private int totalCount;
	private List<EmployeeVO> employees;
	
	public ExtResult(int totalCount,List<EmployeeVO> employees)
	{
		this.totalCount=totalCount;
		this.employees=employees;
	}

}
