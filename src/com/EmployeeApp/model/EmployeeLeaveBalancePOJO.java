package com.EmployeeApp.model;

public class EmployeeLeaveBalancePOJO {
	
	int employeeId;
	String leaveType;
	int credit;
	int availed;
	int finalBalance;
	
	
	public EmployeeLeaveBalancePOJO() {
		super();
	}


	public EmployeeLeaveBalancePOJO(int employeeId, String leaveType, int credit, int availed, int finalBalance) {
		super();
		this.employeeId = employeeId;
		this.leaveType = leaveType;
		this.credit = credit;
		this.availed = availed;
		this.finalBalance = finalBalance;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public String getLeaveType() {
		return leaveType;
	}


	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}


	public int getCredit() {
		return credit;
	}


	public void setCredit(int credit) {
		this.credit = credit;
	}


	public int getAvailed() {
		return availed;
	}


	public void setAvailed(int availed) {
		this.availed = availed;
	}


	public int getFinalBalance() {
		return finalBalance;
	}


	public void setFinalBalance(int finalBalance) {
		this.finalBalance = finalBalance;
	}


	@Override
	public String toString() {
		return "EmployeeLeaveBalancePOJO [employeeId=" + employeeId + ", leaveType=" + leaveType + ", credit=" + credit
				+ ", availed=" + availed + ", finalBalance=" + finalBalance + "]";
	}
	
	
	
	

}
