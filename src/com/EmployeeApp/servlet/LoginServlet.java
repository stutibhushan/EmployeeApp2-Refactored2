package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.EmployeeApp.handler.MyCallbackHandler;




//Unnecessary Comments -bad comment approach
/**
 * Servlet implementation class LoginServlet
 */


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	
	
	
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(LoginServlet.class.getName());
	
	
	public LoginServlet() 
	{
        super();
        // TODO Intialize resources here if any in future --TODO comments are good for future implementations
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		log.info(new Date()+":: in login servlet info :: " +LoginServlet.class);
		boolean status=false;
		String username;
	    String password;
		int authenticationType=0;
		 
		System.setProperty("java.security.auth.login.config", "C:\\NextGenPrototype\\EmployeeApp2-Refactored2\\WebContent\\WEB-INF\\jass.config");
		username=request.getParameter("username");
		password=request.getParameter("password");
		if(request.getParameter("authenticationType")!=null || request.getParameter("authenticationType")!="")
		{
			authenticationType=Integer.parseInt(request.getParameter("authenticationType"));
		}
		
		CallbackHandler handler = new MyCallbackHandler(username, password);
		PrintWriter pw=response.getWriter();
		LoginContext lc = null;
		
	    try
	    {
	    	  
	    	if(authenticationType==1)
	    	{
	    	  			lc = new LoginContext("TestLogin",handler);
	    	  			lc.login();
	    	}
	    	if(authenticationType==2)
	    	{
	    	  			lc=new LoginContext("TestLoginDB",handler);
	    	  			lc.login();
	    	}
	    	if(authenticationType==3)
	    	{
	    	  			lc=new LoginContext("TestLoginWebService",handler);
	    	  			lc.login();
	    	}
	    	
	    	
	          
	       pw.println("{\"success\":true,");
	       pw.println("\"message\":\"Employee Logged in successfully\"}");
	         
	      	} catch (LoginException le)
	      	  { 
	          System.err.println("Cannot create LoginContext. "+ le.getMessage());
	          log.error("Cannot create LoginContext. "+ le.getMessage());
	          
	          pw.println("{\"success\":false,");
		      pw.println("\"message\":\"Incorrect username/password\"}");
		      
	      	  }catch (SecurityException se)
	      	  {
	          System.err.println("Cannot create LoginContext. "+ se.getMessage());
	          log.error("Cannot create LoginContext. " + se.getMessage());
	          
	          pw.println("{\"success\":false,");
		      pw.println("\"message\":\"Incorrect username/password\"}");
	      }
	}

}
