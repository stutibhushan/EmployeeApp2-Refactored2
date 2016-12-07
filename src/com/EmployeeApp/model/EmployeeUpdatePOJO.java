package com.EmployeeApp.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeUpdatePOJO {
	private int id;
	private String name;
	private String startDate;
	private String endDate;
	private String description;
	private double salary;
	private String address;
	private String city;
	private String state;
	private String country;
	private ArrayList<Integer> departmentList;
	private int type;
	
	
	
	
	
	public EmployeeUpdatePOJO() {
		super();
	}





	public EmployeeUpdatePOJO(int id, String name, String startDate, String endDate, String description, double salary,
			String address, String city, String state, String country, ArrayList<Integer> departmentList, int type) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.salary = salary;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.departmentList = departmentList;
		this.type = type;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public String getStartDate() {
		return startDate;
	}





	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}





	public String getEndDate() {
		return endDate;
	}





	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}





	public String getDescription() {
		return description;
	}





	public void setDescription(String description) {
		this.description = description;
	}





	public double getSalary() {
		return salary;
	}





	public void setSalary(double salary) {
		this.salary = salary;
	}





	public String getAddress() {
		return address;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public String getCity() {
		return city;
	}





	public void setCity(String city) {
		this.city = city;
	}





	public String getState() {
		return state;
	}





	public void setState(String state) {
		this.state = state;
	}





	public String getCountry() {
		return country;
	}





	public void setCountry(String country) {
		this.country = country;
	}





	public ArrayList<Integer> getDepartmentList() {
		return departmentList;
	}





	public void setDepartmentList(ArrayList<Integer> departmentList) {
		this.departmentList = departmentList;
	}





	public int getType() {
		return type;
	}





	public void setType(int type) {
		this.type = type;
	}





	@Override
	public String toString() {
		return "EmployeeUpdatePOJO [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", description=" + description + ", salary=" + salary + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", departmentList=" + departmentList + ", type=" + type
				+ "]";
	}
	
	
	
}
