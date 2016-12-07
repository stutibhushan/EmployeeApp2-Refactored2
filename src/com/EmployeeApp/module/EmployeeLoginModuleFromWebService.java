package com.EmployeeApp.module;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;

//import com.EmployeeApp.util.NotificationObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.xml.internal.ws.api.policy.PolicyResolver.ClientContext;


public class EmployeeLoginModuleFromWebService implements LoginModule {
	
	
	static Logger log = Logger.getLogger(EmployeeLoginModuleFromWebService.class.getName());
	private CallbackHandler callbackHandler=null;
	private Subject subject;
	private Map<String, ?> sharedState;
	private Map<String, ?> options;
	private boolean succeeded;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		System.out.println("Login Module - initialize called");
		this.subject = subject;
		this.callbackHandler =  callbackHandler;
		this.sharedState = sharedState;
		this.options = options;
		succeeded = false;
		
	}

	@Override
	public boolean login() throws LoginException {
		log.info("Login Module - login called");
		if (callbackHandler == null){
	        throw new LoginException("No callback handler supplied.");
	    }
	   Callback[] callbacks = new Callback[2];
	   callbacks[0] = new NameCallback("username");
	   callbacks[1] = new PasswordCallback("password", false);
	   try {
	        callbackHandler.handle( callbacks);
	        //--> authenticate if username is the same as password (yes, this is a somewhat simplistic approach :-)
	    } catch (IOException ioe)  {
	    	log.error("IOException occured: "+ioe.getMessage());
	        ioe.printStackTrace();
	        throw new LoginException("IOException occured: "+ioe.getMessage());
	    } catch (UnsupportedCallbackException ucbe) {
	    	log.error("UnsupportedCallbackException encountered: "+ucbe.getMessage());
	        ucbe.printStackTrace();
	        throw new LoginException("UnsupportedCallbackException encountered: "+ucbe.getMessage());
	    }
	    String username = ((NameCallback) callbacks[0]).getName();
        String password = new String(((PasswordCallback) callbacks[1]).getPassword());
        
        try
        {
         Client client=ClientBuilder.newClient();
         WebTarget target=client.target("http://localhost:8080/UserWebServiceApi/rest");
         WebTarget resourceWebTarget=target.path("AuthenticateUser");
         WebTarget resourceWebTargetWithQueryParam=resourceWebTarget.queryParam("username",username).queryParam("password", password);
         Invocation.Builder invocationBuilder=resourceWebTargetWithQueryParam.request();
         Response response=invocationBuilder.get();
         if(response.getStatus()==200)
         {
        	 succeeded=true;
         }
         if(response.getStatus()==401)succeeded=false;
        }catch(Exception e)
        {
        	log.error("unable to use web service"+e.getMessage());
        	succeeded=false;
        	e.printStackTrace();
        }
        return succeeded;
	}

	@Override
	public boolean commit() throws LoginException {
		log.info("Login Module - logout called");
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

}
