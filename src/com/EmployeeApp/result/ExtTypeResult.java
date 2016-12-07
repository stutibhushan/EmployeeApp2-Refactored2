package com.EmployeeApp.result;

import java.util.ArrayList;
import java.util.List;

import com.EmployeeApp.model.EmployeeType;

public class ExtTypeResult {
	
	private List<EmployeeType> employeeListTypes=new ArrayList<>();
	
	public ExtTypeResult(List<EmployeeType> employeeListTypes)
	{
		this.employeeListTypes=employeeListTypes;
	}

}
