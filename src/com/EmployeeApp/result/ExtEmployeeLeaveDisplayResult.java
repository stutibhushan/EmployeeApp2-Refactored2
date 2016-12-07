package com.EmployeeApp.result;

import java.util.List;

//import com.EmployeeApp.model.AdminLeaveDisplayPOJO;
//import com.EmployeeApp.model.EmployeeLeaveDisplayPOJO;
import com.EmployeeApp.model.LeaveDisplayAdminPOJO;
import com.EmployeeApp.model.LeaveDisplayEmployeePOJO;

public class ExtEmployeeLeaveDisplayResult {
	
	private int totalCount;
	private List<LeaveDisplayEmployeePOJO> appliedLeaveDisplay;
	
	public ExtEmployeeLeaveDisplayResult(int totalCount,List<LeaveDisplayEmployeePOJO> appliedLeaveDisplay)
	{
		this.totalCount=totalCount;
		this.appliedLeaveDisplay= appliedLeaveDisplay;
	}

}
