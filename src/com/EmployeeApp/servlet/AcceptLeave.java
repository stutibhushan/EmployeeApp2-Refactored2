package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EmployeeApp.dao.LeaveBalanceDAOImpl;

/**
 * Servlet implementation class AcceptLeave
 */
@WebServlet("/AcceptLeave")
public class AcceptLeave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   private int employeeId;
   private String leaveType;
   private Date date;
   int status;
   private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
	
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   
	   try
	   {
		   
		   PrintWriter out= response.getWriter();
	   employeeId=Integer.parseInt(request.getParameter("employeeId"));
		leaveType=request.getParameter("leaveType");
		date=formatter.parse(request.getParameter("date"));
		LeaveBalanceDAOImpl dao= new LeaveBalanceDAOImpl();
		
		status=dao.acceptLeave(employeeId, leaveType, date);
		
		 if(status!=0)
	      {
	        out.println("{");
	        out.println("\"success\": \"true\",");
	        
	        out.println("\"message\": \"Leave Accepted\"");
	        out.println("}");
	      }
	      else
	      {
	    	  out.println("{");
		        out.println("\"success\": \"false\",");
		        out.println("\"message\": \"Unable to accept\"");
		        out.println("}");
	      
	    	  
	       }
		
		
	   }
	   catch(Exception e)
	   {
		   System.out.println(e.getMessage());
	   }
	}

}
