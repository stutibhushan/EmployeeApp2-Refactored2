package com.EmployeeApp.model;

public class Notification {
	Boolean success;
    String message;
    
    
    public Notification()
    {
    	super();
    }
	
    public Notification(Boolean success, String message) {
		
		this.success = success;
		this.message=message;
	}
	
	public Boolean getSuccess() {
		return success;
	}




	public void setStatus(Boolean success) {
		this.success = success;
	}




	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}

	

}
