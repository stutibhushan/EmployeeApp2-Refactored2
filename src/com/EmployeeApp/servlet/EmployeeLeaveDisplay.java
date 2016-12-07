package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EmployeeApp.dao.LeaveBalanceDAOImpl;
//import com.EmployeeApp.model.AdminLeaveDisplayPOJO;
//import com.EmployeeApp.model.EmployeeLeaveDisplayPOJO;
import com.EmployeeApp.model.LeaveDisplayAdminPOJO;
import com.EmployeeApp.model.LeaveDisplayEmployeePOJO;
import com.EmployeeApp.result.ExtAppliedLeaveDisplayResult;
import com.EmployeeApp.result.ExtEmployeeLeaveDisplayResult;
import com.google.gson.Gson;


@WebServlet("/EmployeeLeaveDisplay")
public class EmployeeLeaveDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	
	 private int start;
	 private int limit;
     private int employeeId;
	 private String callback;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("employeeId") != null && !request.getParameter("employeeId").isEmpty()) {
			employeeId= Integer.parseInt(request.getParameter("employeeId"));
			
			
		} else {

			employeeId=0;
			
		}
		
		
		LeaveBalanceDAOImpl dao= new LeaveBalanceDAOImpl();
		start= Integer.parseInt(request.getParameter("start"));
		limit= Integer.parseInt(request.getParameter("limit"));
		
		
        List<LeaveDisplayEmployeePOJO> appliedLeaveDisplay= dao.getAppliedLeave(start, limit, employeeId);
		
		int totalCount= dao.getTotalCount(employeeId);
		callback = request.getParameter("callback");
		
		ExtEmployeeLeaveDisplayResult ext= new ExtEmployeeLeaveDisplayResult(totalCount, appliedLeaveDisplay);
		
		
      PrintWriter pw=response.getWriter();
		
		Gson gson = new Gson();
		String jsonInString = callback + "(" + gson.toJson(ext) + ")";
		System.out.println(jsonInString);
		pw.print(jsonInString);
		pw.flush();
		
		
		
	}

}
