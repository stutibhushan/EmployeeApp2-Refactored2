package com.EmployeeApp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

/**
 * Servlet implementation class DeleteEmployee
 */
@WebServlet("/DeleteEmployee")
public class DeleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
        private int employeeId;
        private int status=0;
        private EmployeeDAO  employeeDAO;
        private DepartmentDAO departmentDAO;
        private EmployeeTypeDAO employeeTypeDAO;
        private LeaveBalanceDAO leaveBalanceDAO;
        private int employeeDeleteStatus=0;
        private int employeeDepartmentDeleteStatus=0;
        private int employeeTypeDeleteStatus=0;
        private int employeeLeaveBalanceDeleteStatus=0;
        
        final static Logger logger=Logger.getLogger(DeleteEmployee.class);
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out= response.getWriter())
		{
			
			employeeId= Integer.parseInt(request.getParameter("employeeId"));
			
			employeeDAO=EmployeeDAOImpl.getEmployeeDAO();
			departmentDAO= DepartmentDAOImpl.getDepartmentDAO();
			employeeTypeDAO= EmployeeTypeDAOImpl.getEmployeeTypeDAO();
			leaveBalanceDAO= LeaveBalanceDAOImpl.getLeaveBalanceDAO();
			
			//status= dao.deleteEmployee(employeeId);
			
			employeeTypeDeleteStatus= employeeTypeDAO.deleteEmployeeType(employeeId);
			
			employeeDepartmentDeleteStatus= departmentDAO.deleteEmployeeDepartmentDetail(employeeId);
			
			employeeLeaveBalanceDeleteStatus= leaveBalanceDAO.deleteEmployeeLeaveDetail(employeeId);
			
			
			if(employeeTypeDeleteStatus!=0 && employeeDepartmentDeleteStatus!=0 && employeeLeaveBalanceDeleteStatus!=0)
			{
				employeeDeleteStatus= employeeDAO.deleteEmployee(employeeId);
				
			}
			
			
			 if(employeeDeleteStatus!=0)
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
			
			 logger.error("Employee cannot be added"+e.getMessage());
		}
	}

}
