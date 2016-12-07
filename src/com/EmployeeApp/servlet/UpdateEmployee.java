/*package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.EmployeeApp.daoInterface.DepartmentDAO;
import com.EmployeeApp.dao.DepartmentDAOImpl;
import com.EmployeeApp.dao.EmployeeDAOImpl;
import com.EmployeeApp.model.Employee;

*//**
 * Servlet implementation class UpdateEmployee
 *//*
@WebServlet("/UpdateEmployee")
public class UpdateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   private int id;
   private String name;
   private Date startDate;
   private Date endDate;
   private String description;
   private double salary;
   private String address;
   private String city;
   private String state;
   private String country;
   //use intention- revealing names
   //private String d;
   private String departmentListAsString;
   private int type=-1;
   private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
   private int status=0;
   final static Logger logger=Logger.getLogger(UpdateEmployee.class);
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out= response.getWriter())
		{
			id= Integer.parseInt(request.getParameter("id"));
			name= request.getParameter("name");
			startDate=formatter.parse(request.getParameter("startDate"));
			endDate= formatter.parse(request.getParameter("endDate"));
			description=request.getParameter("description");
	        salary=Double.parseDouble(request.getParameter("salary"));
	        address=request.getParameter("address");
	        city=request.getParameter("city");
	        state=request.getParameter("state");
	        country=request.getParameter("country");
	        departmentListAsString= request.getParameter("departmentList");
	       
	        ArrayList<String> departmentList= new ArrayList(Arrays.asList(departmentListAsString.split(",")));
	        
	        //Avoid disinformation
	        //ArrayList<Integer> departmentId=new ArrayList<Integer>();
	        
	        ArrayList<Integer> departmentIdList=new ArrayList<Integer>();
	         
	         for(int i=0;i<departmentList.size();i++)
	           {    
	        	 //Avoid disinformation
	        	 //int a=Integer.parseInt(departmentList.get(i));
	        	 int id=Integer.parseInt(departmentList.get(i));
	        	 departmentIdList.add(id);
	        	 
	            }
	        
	         
	 		 
	         if(request.getParameter("type")!="" || request.getParameter("type")!=null)
		        {
	        	 
		        	type=Integer.parseInt(request.getParameter("type"));
		        }
	        
	         
	         //Use getters and setters to set values instead of contructor
	        // Employee employee=new Employee(name,description,address,city,state,country,salary, startDate, endDate);
	        Employee employee=new Employee();
	        employee.setName(name);
	        employee.setDescription(description);
	        employee.setAddress(address);
	        employee.setCity(city);
	        employee.setState(state);
	        employee.setCountry(country);
	        employee.setSalary(salary);
	        employee.setStartDate(startDate);
	        employee.setEndDate(endDate);
	         
	        
	        EmployeeDAOImpl employeeDAO=new EmployeeDAOImpl();
	        
	        status=employeeDAO.updateEmployee(employee, id, type);
	       
	      // int id=employeeDAO.getCurrentEmployeeId(name);
	       
	       DepartmentDAO departmentDAO = new DepartmentDAOImpl();
	       
	       departmentDAO.updateEmployeeDepartmentDetail(departmentIdList,id);
	       
	       
	       
	       //response.setContentType("application/json");
	       
	       
	      if(status!=0)
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
			logger.error("Employee cannot be updated"+e.getMessage());
		}
		
	}

}
*/