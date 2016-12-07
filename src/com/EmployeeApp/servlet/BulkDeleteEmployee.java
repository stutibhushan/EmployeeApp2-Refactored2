package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EmployeeApp.dao.EmployeeDAOImpl;


@WebServlet("/BulkDeleteEmployee")
public class BulkDeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    private String employeeIdString;
    private int status;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out= response.getWriter())
		{
			employeeIdString= request.getParameter("employeeIds");
			ArrayList<String> aList= new ArrayList(Arrays.asList(employeeIdString.split(",")));
	         
	         ArrayList<Integer> employeeIds=new ArrayList<Integer>();
	         
	         for(int i=0;i<aList.size();i++)
	         {
	        	
	        	 employeeIds.add(Integer.parseInt(aList.get(i)));
	        	 
	         }
			
			EmployeeDAOImpl dao= new EmployeeDAOImpl();
			
			status= dao.bulkDeleteEmployee(employeeIds);
			
			
			 if(status!=0)
		      {
		        out.println("{");
		        out.println("\"success\": \"true\",");
		        //out.println("\"employeeId\":"+id+",");
		        out.println("\"message\": \"Employee Deleted Successfully\"");
		        out.println("}");
		      }
		      else
		      {
		    	  out.println("{");
			        out.println("\"success\": \"false\",");
			        out.println("\"message\": \"Unable to delete..\"");
			        out.println("}");
		      
		    	  
		       }
		       
		       
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	
	}

}
