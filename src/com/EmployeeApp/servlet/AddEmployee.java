package com.EmployeeApp.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import com.EmployeeApp.dao.DepartmentDAOImpl;
import com.EmployeeApp.dao.EmployeeDAOImpl;
import com.EmployeeApp.dao.EmployeeTypeDAOImpl;
import com.EmployeeApp.dao.LeaveBalanceDAOImpl;
import com.EmployeeApp.daoInterface.DepartmentDAO;
import com.EmployeeApp.daoInterface.EmployeeDAO;
import com.EmployeeApp.daoInterface.EmployeeTypeDAO;
import com.EmployeeApp.daoInterface.LeaveBalanceDAO;
import com.EmployeeApp.model.Employee;
import com.EmployeeApp.service.DepartmentServiceImpl;
import com.EmployeeApp.service.EmployeeServiceImpl;
import com.EmployeeApp.service.EmployeeTypeServiceImpl;
import com.EmployeeApp.service.LeaveBalanceServiceImpl;
import com.EmployeeApp.serviceInterface.DepartmentService;
import com.EmployeeApp.serviceInterface.EmployeeService;
import com.EmployeeApp.serviceInterface.EmployeeTypeService;
import com.EmployeeApp.serviceInterface.LeaveBalanceService;

import java.text.SimpleDateFormat;

@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	
	//public static constants
	public static final long serialVersionUID = 1L;
	
	//private static variables
	final static Logger logger=Logger.getLogger(AddEmployee.class);
	
	//private instance variables
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
	
	private String departmentListAsString;
	
	//hungarian notation
	//avoid encodings, name not changed when type changed
	//private int type;
	private int typeId;
	private int employeeId;
	private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
	private boolean addTypeStatus;
	private boolean addDepartmentListStatus;
	private boolean addLeaveBalanceStatus;
	private EmployeeDAO employeeDAO;
	private DepartmentDAO departmentDAO;
	private LeaveBalanceDAO leaveBalanceDAO;
	private EmployeeTypeDAO employeeTypeDAO;
	private EmployeeServiceImpl employeeService;
	private DepartmentServiceImpl departmentService;
	private LeaveBalanceServiceImpl leaveBalanceService;
	private EmployeeTypeServiceImpl employeeTypeService;

	//final static Logger logger=Logger.getLogger(AddEmployee.class);
   
	//public functions
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{

	    try( PrintWriter out = response.getWriter()) {


	        name=request.getParameter("name");
	        startDate=formatter.parse(request.getParameter("startDate"));
	        endDate=formatter.parse(request.getParameter("endDate"));
	        description=request.getParameter("description");
	        
	        //dont pass null
	        if(request.getParameter("salary")=="" || request.getParameter("salary")==null)
	           {
	        	 salary=0.0d;
	           }
	        else
	           {
	             salary=Double.parseDouble(request.getParameter("salary"));
	           }
	        
	        address=request.getParameter("address");
	        city=request.getParameter("city");
	        state=request.getParameter("state");
	        country=request.getParameter("country");
	        departmentListAsString= request.getParameter("departmentList");
	        typeId=Integer.parseInt(request.getParameter("type"));
	         
	        
	         ArrayList<String> departmentList= new ArrayList(Arrays.asList(departmentListAsString.split(",")));
	         
	         ArrayList<Integer> departmentIdList=new ArrayList<Integer>();
	         
	         for(int i=0;i<departmentList.size();i++)
	            {
	        	 int id=Integer.parseInt(departmentList.get(i));
	        	 departmentIdList.add(id);
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

	          employeeDAO=EmployeeDAOImpl.getEmployeeDAO();
	          departmentDAO = DepartmentDAOImpl.getDepartmentDAO();
	          leaveBalanceDAO= LeaveBalanceDAOImpl.getLeaveBalanceDAO();
	          employeeTypeDAO= new EmployeeTypeDAOImpl();
	           
	          employeeService= new EmployeeServiceImpl();
	          employeeService.setEmployeeDAO(employeeDAO);
	          
	          departmentService= new DepartmentServiceImpl();
	          departmentService.setDepartmentDAO(departmentDAO);
	          
	          employeeTypeService= new EmployeeTypeServiceImpl();
	          employeeTypeService.setEmployeeTypeDAO(employeeTypeDAO);
	          
	          leaveBalanceService= new LeaveBalanceServiceImpl();
	          leaveBalanceService.setLeaveBalanceDAO(leaveBalanceDAO);
	          
	          employeeId=employeeService.addEmployee(employee);
	         
	        
	         if(employeeId!=0)
	         {
	          addTypeStatus=employeeTypeService.addEmployeeType(employeeId,typeId);
	          
	          addDepartmentListStatus=departmentService.addEmployeeDepartmentDetail(departmentIdList,employeeId);
	          
	          addLeaveBalanceStatus= leaveBalanceService.addLeaveBalanceDetail(employeeId);
	         }
	       
	        
	       //response.setContentType("application/json");
	        
	        //Meaningful names
	         
	        /* if(status!=0)
		      {
		        out.println("{");
		        out.println("\"success\": \"true\",");
		        out.println("\"employeeId\":"+id+",");
		        out.println("\"message\": \"Employee Added Successfully\"");
		        out.println("}");
		      }
		      else
		      {
		    	  out.println("{");
			        out.println("\"success\": \"false\",");
			        out.println("\"message\": \"Unable to add..\"");
			        out.println("}");
		      
		    	  
		       }*/
	       
	       
	         if(addTypeStatus==true && addDepartmentListStatus==true && addLeaveBalanceStatus==true)
	             {
	              out.println("{");
	              out.println("\"success\": \"true\",");
	              out.println("\"employeeId\":"+employeeId+",");
	              out.println("\"message\": \"Employee Added Successfully\"");
	              out.println("}");
	             }
	         else
	             {
	    	      out.println("{");
		          out.println("\"success\": \"false\",");
		          out.println("\"message\": \"Unable to add..\"");
		          out.println("}");
	             }
	        
	    }

	 catch (Exception e)
	    {
	      
		 logger.error("Employee cannot be added"+e.getMessage());
	    }
	
	}
}


