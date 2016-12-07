package com.EmployeeApp.result;

import java.util.ArrayList;
import java.util.List;

import com.EmployeeApp.model.EmployeeLeaveBalancePOJO;
import com.EmployeeApp.model.EmployeeType;

public class ExtLeaveBalanceResult {

private List<EmployeeLeaveBalancePOJO> employeeLeaveBalance=new ArrayList<>();
	
	public ExtLeaveBalanceResult(List<EmployeeLeaveBalancePOJO> employeeLeaveBalance)
	{
		this.employeeLeaveBalance=employeeLeaveBalance;
	}
}
