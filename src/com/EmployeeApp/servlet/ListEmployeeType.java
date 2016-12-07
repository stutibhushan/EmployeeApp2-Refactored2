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
import com.EmployeeApp.model.EmployeeType;
import com.EmployeeApp.result.ExtResult;
import com.EmployeeApp.result.ExtTypeResult;
import com.google.gson.Gson;

/**
 * Servlet implementation class ListEmployeeType
 */
@WebServlet("/ListEmployeeType")
public class ListEmployeeType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private String callback;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListEmployeeType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		


		EmployeeDAO employeeDAO=new EmployeeDAOImpl();
		List<EmployeeType> employeeListTypes=employeeDAO.getListTypes();
		
		response.setContentType("application/json");

		callback = request.getParameter("callback");
		
		ExtTypeResult extTypeResult=new ExtTypeResult(employeeListTypes);
		
		PrintWriter pw=response.getWriter();
		
		Gson gson = new Gson();
		String jsonInString = callback + "(" + gson.toJson(extTypeResult) + ")";
		System.out.println(jsonInString);
		pw.print(jsonInString);
		pw.flush();
		
	
		
		
		
		
	}

	

}
