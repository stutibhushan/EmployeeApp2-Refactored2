package com.EmployeeApp.result;

import java.util.List;

//import com.EmployeeApp.model.AdminLeaveDisplayPOJO;
import com.EmployeeApp.model.EmployeeVO;
import com.EmployeeApp.model.LeaveDisplayAdminPOJO;

public class ExtAppliedLeaveDisplayResult {
	
	private int totalCount;
	private List<LeaveDisplayAdminPOJO> appliedLeaveDisplay;
	
	public ExtAppliedLeaveDisplayResult(int totalCount,List<LeaveDisplayAdminPOJO> appliedLeaveDisplay)
	{
		this.totalCount=totalCount;
		this.appliedLeaveDisplay= appliedLeaveDisplay;
	}


}
