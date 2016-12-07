package com.EmployeeApp.model;

public class EmployeeType {
     
	int id;
	String typeName;
	public EmployeeType() {
		super();
	}
	public EmployeeType(int id, String typeName) {
		super();
		this.id = id;
		this.typeName = typeName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "EmployeeType [id=" + id + ", typeName=" + typeName + "]";
	}
	
	
}
