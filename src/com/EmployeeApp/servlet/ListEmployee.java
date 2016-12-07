package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EmployeeApp.dao.EmployeeDAOImpl;
import com.EmployeeApp.daoInterface.EmployeeDAO;
import com.EmployeeApp.model.EmployeeVO;
import com.EmployeeApp.result.ExtResult;
import com.google.gson.Gson;

/**
 * Servlet implementation class ListEmployee
 */
@WebServlet("/ListEmployee")
public class ListEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private String callback;
	private int start;
	private int limit;
	
	private int employeeTypeId;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if(request.getParameter("id")==null || request.getParameter("id").isEmpty())
		   {
			
		     employeeTypeId=4;
		   }
		else
		   {
		     employeeTypeId=Integer.parseInt(request.getParameter("id"));
		   }
		
	    start=Integer.parseInt(request.getParameter("start"));
		limit=Integer.parseInt(request.getParameter("limit"));
		
		EmployeeDAO employeeDAO=new EmployeeDAOImpl();
		
		List<EmployeeVO> employees=employeeDAO.getEmployeeByType(employeeTypeId,start,limit);
		
		
		int totalCount=employeeDAO.getEmployeeCount(employeeTypeId);
		
		System.out.println(totalCount);
	
		ExtResult extResult=new ExtResult(totalCount,employees);
		
		callback = request.getParameter("callback");
		
		PrintWriter pw=response.getWriter();
		
		Gson gson = new Gson();
		String jsonInString = callback + "(" + gson.toJson(extResult) + ")";
		System.out.println(jsonInString);
		pw.print(jsonInString);
		pw.flush();
		
		
	}

}
