package com.EmployeeApp.model;

import java.util.Date;

public class Employee {
	
	int id;
	String name, description, address, city, state, country;
	double salary;
	Date startDate, endDate;
	
	
	
	public Employee() {
		super();
	}
	public Employee(String name, String description, String address, String city, String state, String country,
			double salary, Date startDate, Date endDate) {
		super();
		this.name = name;
		this.description = description;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.salary = salary;
		this.startDate = startDate;
		this.endDate = endDate;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", description=" + description + ", address=" + address
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", salary=" + salary + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
	

}
