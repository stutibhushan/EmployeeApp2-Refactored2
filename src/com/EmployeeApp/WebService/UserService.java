package com.EmployeeApp.WebService;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.EmployeeApp.dao.LoginDao;
import com.google.gson.Gson;


@Path("/UserService")
public class UserService {
	
	LoginDao dao= new LoginDao();
	
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessageQueryParam(@QueryParam("username") String username, @QueryParam("password") String password)
	   {
	     
		 int status= dao.verifyUser(username, password);
		
		if(status!=0)
		{
		   return Response
				   .status(200)
				   .entity("{\"success\":true, \"message\":\"LOGIN SUCCESSFUL\"}").build();
		}
		else
		{
			return Response.status(404).entity("{\"success\":false, \"message\":\"INVALID USERNAME OR PASSWORD\"}").build();
		}
	    
	   }

}
