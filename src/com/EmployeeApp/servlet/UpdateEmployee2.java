package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.EmployeeApp.daoInterface.DepartmentDAO;
import com.EmployeeApp.daoInterface.EmployeeDAO;
import com.EmployeeApp.daoInterface.EmployeeTypeDAO;
import com.EmployeeApp.dao.DepartmentDAOImpl;
import com.EmployeeApp.dao.EmployeeDAOImpl;
import com.EmployeeApp.dao.EmployeeTypeDAOImpl;
import com.EmployeeApp.model.Employee;
import com.EmployeeApp.model.EmployeeUpdatePOJO;
import com.google.gson.Gson;




@WebServlet("/UpdateEmployee2")
public class UpdateEmployee2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");   
	private DepartmentDAO departmentDAO;
	private EmployeeDAO employeeDAO;
	private EmployeeTypeDAO employeeTypeDAO;
	private int employeeUpdateStatus=0;
	private int employeeTypeUpdateStatus=0;
	private int departmentUpdateStatus=0;
	
	final static Logger logger=Logger.getLogger(UpdateEmployee2.class);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("application/json");
		
		String employeeJsonInString= request.getHeader("employeeJson");
		Gson gson= new Gson();
		EmployeeUpdatePOJO  employeePOJO = gson.fromJson(employeeJsonInString, EmployeeUpdatePOJO.class);
		
		
		try{
			
		PrintWriter out= response.getWriter();
		
		Employee employee=new Employee(employeePOJO.getName(),employeePOJO.getDescription(),employeePOJO.getAddress(),employeePOJO.getCity(),employeePOJO.getState(),employeePOJO.getCountry(),employeePOJO.getSalary(), formatter.parse(employeePOJO.getStartDate()), formatter.parse(employeePOJO.getEndDate()));
		ArrayList<Integer> departmentList= employeePOJO.getDepartmentList();
		int typeId=employeePOJO.getType();
		int employeeId=employeePOJO.getId();
		
		employeeDAO=EmployeeDAOImpl.getEmployeeDAO();
		
		employeeTypeDAO=EmployeeTypeDAOImpl.getEmployeeTypeDAO();
		
	    departmentDAO = DepartmentDAOImpl.getDepartmentDAO();
		
		employeeUpdateStatus=employeeDAO.updateEmployee(employee, employeeId);
		
		employeeTypeUpdateStatus=employeeTypeDAO.updateEmployeeType(employeeId, typeId);
	    
		departmentUpdateStatus=departmentDAO.updateEmployeeDepartmentDetail(departmentList,employeeId);
	      
	 
	           
	      if(employeeUpdateStatus !=0 && employeeTypeUpdateStatus!=0 && departmentUpdateStatus != 0)
	      {
	        out.println("{");
	        out.println("\"success\": \"true\",");
	       // out.println("\"employeeId\":"+id+",");
	        out.println("\"message\": \"Employee Updated Succesfully\"");
	        out.println("}");
	      }
	      else
	      {
	    	  out.println("{");
		        out.println("\"success\": \"false\",");
		        out.println("\"message\": \"Unable to update..\"");
		        out.println("}");
	      
	    	  
	       }
	       
		 
		}
		
		catch(Exception e)
		{
			logger.error("Employee details cannot be updated"+e.getMessage());
		}
	}

}
