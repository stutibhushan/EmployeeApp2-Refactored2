package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EmployeeApp.daoInterface.DepartmentDAO;
import com.EmployeeApp.dao.DepartmentDAOImpl;
import com.EmployeeApp.model.Department;
import com.EmployeeApp.result.ExtDepartmentResult;
import com.EmployeeApp.result.ExtTypeResult;
import com.google.gson.Gson;

/**
 * Servlet implementation class DepartmentList
 */
@WebServlet("/departmentList")
public class DepartmentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String callback;
    
   
    public DepartmentList() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DepartmentDAO dao= new DepartmentDAOImpl();
		List<Department> departmentList= dao.getDepartmentList();
		
		callback = request.getParameter("callback");
		
        ExtDepartmentResult extDeptResult=new ExtDepartmentResult(departmentList);
		
		PrintWriter pw=response.getWriter();
		
		Gson gson = new Gson();
		String jsonInString = callback + "(" + gson.toJson(extDeptResult) + ")";
		System.out.println(jsonInString);
		pw.print(jsonInString);
		pw.flush();
		
		
		
	}

}
