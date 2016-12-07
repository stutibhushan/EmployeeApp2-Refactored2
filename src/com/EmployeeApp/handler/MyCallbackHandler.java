package com.EmployeeApp.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.Logger;

import com.EmployeeApp.daoInterface.EmployeeDAO;

public class MyCallbackHandler implements CallbackHandler {
	
	static Logger log = Logger.getLogger(MyCallbackHandler.class.getName());
	private String username;
	private String password;
	private int authenticationType;
	
	public MyCallbackHandler(String username,String password)
	{
		System.out.println("Callback Handler - constructor called");
		this.username=username;
		this.password=password;
	}
	
	public void setAuthenticationType(int authenticationType)
	{
		this.authenticationType=authenticationType;	
	}
	
	public int getAuthenticationType(){
		return authenticationType;
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {

		log.info(new Date()+"in handle method "+MyCallbackHandler.class);
		for (int i = 0; i < callbacks.length; i++)
		{
            if (callbacks[i] instanceof NameCallback)
            {
                NameCallback nc = (NameCallback)callbacks[i];
                nc.setName(username);
            } 
            else if (callbacks[i] instanceof PasswordCallback)
            {
                PasswordCallback pc = (PasswordCallback)callbacks[i];
                pc.setPassword(password.toCharArray());
            } else {
                throw new UnsupportedCallbackException
                        (callbacks[i], "Unrecognized Callback");
            }
        }
    }
		
}


