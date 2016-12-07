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
import com.EmployeeApp.model.LeaveDisplayAdminPOJO;
import com.EmployeeApp.result.ExtAppliedLeaveDisplayResult;
import com.google.gson.Gson;

@WebServlet("/AdminLeaveDisplay")
public class AdminLeaveDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String callback;
	private int start;
	private int limit;
   
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LeaveBalanceDAOImpl dao= new LeaveBalanceDAOImpl();
		 start= Integer.parseInt(request.getParameter("start"));
		 limit= Integer.parseInt(request.getParameter("limit"));
		List<LeaveDisplayAdminPOJO> appliedLeaveDisplay= dao.getAppliedLeave(start, limit);
		
		int totalCount= dao.getTotalCount();
		callback = request.getParameter("callback");
		
		ExtAppliedLeaveDisplayResult ext= new ExtAppliedLeaveDisplayResult(totalCount, appliedLeaveDisplay);
		
		
        PrintWriter pw=response.getWriter();
		
		Gson gson = new Gson();
		String jsonInString = callback + "(" + gson.toJson(ext) + ")";
		System.out.println(jsonInString);
		pw.print(jsonInString);
		pw.flush();
		
		
		
	}

}
