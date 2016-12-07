package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(LogoutServlet.class.getName());

       
 
    public LogoutServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter pw=response.getWriter();
		log.info(new Date()+" in logout servlet"+ LogoutServlet.class.getName());
		try
		{
		request.getSession().invalidate();
		pw.println("{\"success\":true,");
        pw.println("\"message\":\"Employee Logged out successfully\"}");
		}
		catch(Exception e)
		{
		System.out.println("unable to logout..");
		log.error("unable to logout.."+e.getMessage());
		pw.println("{\"success\":false,");
	    pw.println("\"message\":\"Unable to logged out\"}");
		}
	}

	

}
