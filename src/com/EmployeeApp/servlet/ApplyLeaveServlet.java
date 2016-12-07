package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EmployeeApp.dao.LeaveBalanceDAOImpl;


@WebServlet("/ApplyLeaveServlet")
public class ApplyLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private  String leaveType;
    private int duration;
    private int status=0;
    private int employeeId;
    private String applyLeaveDatesList;
    private SimpleDateFormat formatter1=new SimpleDateFormat("E MMM dd yyyy HH:mm:ss 'GMT'z", Locale.ENGLISH);
    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private SimpleDateFormat formatter3=new SimpleDateFormat("yyyy-MM-dd");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
	    PrintWriter out= response.getWriter();
		leaveType= request.getParameter("leaveType");
		duration=Integer.parseInt(request.getParameter("duration"));
		employeeId=Integer.parseInt(request.getParameter("employeeId"));
		applyLeaveDatesList=request.getParameter("applyLeaveDatesList");
		ArrayList<String> applyLeaveDatesListString= new ArrayList(Arrays.asList(applyLeaveDatesList.split(",")));
		
		//System.out.println("applyLeaveDatesListString "+applyLeaveDatesListString);
		ArrayList<Date> applyLeaveDates= new ArrayList<>();
		
		for(int i=0;i<applyLeaveDatesListString.size();i++)
        {
       	 
		 Date a=formatter1.parse(applyLeaveDatesListString.get(i));
		 //System.out.println("Date a"+a);
		 String b=formatter2.format(a);
		 //System.out.println("String b"+b);
		 Date c= formatter3.parse(b);
		 //System.out.println("Date c"+c);
       	 applyLeaveDates.add(a);
       	 
        }
		
		//System.out.println("apply leave dates------->>>>>>------->>>>"+applyLeaveDates);
		
		LeaveBalanceDAOImpl dao= new LeaveBalanceDAOImpl();
		status=dao.applyLeave(leaveType,  employeeId, applyLeaveDates);
		
		
		if(status!=0)
	      {
	        out.println("{");
	        out.println("\"success\": \"true\",");
	      
	        out.println("\"message\": \"Leave Applied Successfully\"");
	        out.println("}");
	      }
	      else
	      {
	    	  out.println("{");
		        out.println("\"success\": \"false\",");
		        out.println("\"message\": \"All leave Applied\"");
		        out.println("}");
	      
	    	  
	       }
	       
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

}
