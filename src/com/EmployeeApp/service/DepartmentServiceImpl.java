package com.EmployeeApp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.EmployeeApp.dao.DepartmentDAOImpl;
import com.EmployeeApp.daoInterface.DepartmentDAO;
import com.EmployeeApp.model.Department;
import com.EmployeeApp.serviceInterface.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {
	
	private DepartmentDAO departmentDAO;

	@Override
	public List<Department> getDepartmentList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateEmployeeDepartmentDetail(ArrayList<Integer> departmentId, int employeeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean addEmployeeDepartmentDetail(ArrayList<Integer> departmentIdList, int employeeId) {
		
		if(employeeId<0 || departmentIdList.isEmpty())
		{
			throw new RuntimeException();
		}
		
	    int addDepartmentListStatus=departmentDAO.addEmployeeDepartmentDetail(departmentIdList,employeeId);
		if(addDepartmentListStatus ==0)
		{
			return false;
		}
		
		return true;
		
	}

	@Override
	public int deleteEmployeeDepartmentDetail(int employeeId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public DepartmentDAO getDepartmentDAO() {
		return departmentDAO;
	}

	public void setDepartmentDAO(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

}
