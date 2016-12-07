package com.EmployeeApp.module;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.log4j.Logger;

import com.EmployeeApp.dao.UserDAO;
import com.EmployeeApp.daoInterface.UserDAOInterface;

public class EmployeeLoginModuleFromDB implements LoginModule {
	
	
	static Logger log = Logger.getLogger(EmployeeLoginModuleFromDB.class.getName());
	private CallbackHandler callbackHandler=null;
	private Subject subject;
	private Map<String, ?> sharedState;
	private Map<String, ?> options;
	private boolean succeeded;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		log.info("Login Module - initialize called");
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
        
        UserDAOInterface userDAOInterface=new UserDAO();
        int status= userDAOInterface.authenticate(username, password);
       
        if(status==1)
           {
    	   succeeded=true;
    	   return succeeded;
           }
        else {
			log.error("Failure! You don't get to log in");
			succeeded = false;
			throw new FailedLoginException("Sorry! No login for you.");
		}

		
	}

	@Override
	public boolean commit() throws LoginException {
		log.info("Login Module - commit called");
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		log.info("Login Module - logout called");
		return true;
	}

}
