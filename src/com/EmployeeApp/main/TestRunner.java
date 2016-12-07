package com.EmployeeApp.main;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.EmployeeApp.service.DepartmentServiceImplTest;
import com.EmployeeApp.service.EmployeeServiceImplTest;



public class TestRunner {
  public static void main(String[] args) {
    Result resultCalculationImplTest = JUnitCore.runClasses(DepartmentServiceImplTest.class);
    for (Failure failure : resultCalculationImplTest.getFailures()) {
      System.out.println(failure.toString());
    }
      
      Result resultGreetingImplTest = JUnitCore.runClasses(EmployeeServiceImplTest.class);
      for (Failure failure : resultGreetingImplTest.getFailures()) {
        System.out.println(failure.toString());
      }
  }
}
