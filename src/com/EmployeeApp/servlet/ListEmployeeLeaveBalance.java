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
import com.EmployeeApp.daoInterface.LeaveBalanceDAO;
import com.EmployeeApp.model.EmployeeLeaveBalancePOJO;
import com.EmployeeApp.result.ExtLeaveBalanceResult;
import com.google.gson.Gson;

@WebServlet("/ListEmployeeLeaveBalance")
public class ListEmployeeLeaveBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int employeeId;
    private String callback;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("employeeId") != null && !request.getParameter("employeeId").isEmpty()) {
			employeeId= Integer.parseInt(request.getParameter("employeeId"));
			
		} else {

			employeeId=0;
		}
		
		//employeeId= Integer.parseInt(request.getParameter("employeeId"));
		
		LeaveBalanceDAO dao= new LeaveBalanceDAOImpl();
		
		List<EmployeeLeaveBalancePOJO> employeeLeaveBalance= dao.listEmployeeLeaveBalance(employeeId);
		
		response.setContentType("application/json");

		callback = request.getParameter("callback");
		
		ExtLeaveBalanceResult elbr= new ExtLeaveBalanceResult(employeeLeaveBalance);
		
        PrintWriter pw=response.getWriter();
		
		Gson gson = new Gson();
		String jsonInString = callback + "(" + gson.toJson(elbr) + ")";
		System.out.println(jsonInString);
		pw.print(jsonInString);
		pw.flush();
		
		
		
		
	}

}
