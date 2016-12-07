package com.EmployeeApp.model;

import java.util.Date;

public class LeaveDisplayEmployeePOJO {
	
		
		private int employeeId;
		private String leaveType;
		private Date date;
		private String status;
		
		
		
		
		public LeaveDisplayEmployeePOJO() {
			super();
		}




		public LeaveDisplayEmployeePOJO(int employeeId, String leaveType, Date date, String status) {
			super();
			this.employeeId = employeeId;
			this.leaveType = leaveType;
			this.date = date;
			this.status = status;
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




		public Date getDate() {
			return date;
		}




		public void setDate(Date date) {
			this.date = date;
		}




		public String getStatus() {
			return status;
		}




		public void setStatus(String status) {
			this.status = status;
		}




		@Override
		public String toString() {
			return "LeaveDisplayPOJO [employeeId=" + employeeId + ", leaveType=" + leaveType + ", date=" + date
					+ ", status=" + status + "]";
		}
		
		
		
		

	}



