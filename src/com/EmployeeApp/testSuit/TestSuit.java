package com.EmployeeApp.testSuit;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.EmployeeApp.service.DepartmentServiceImplTest;
import com.EmployeeApp.service.EmployeeServiceImplTest;
import com.EmployeeApp.service.LeaveBalanceServiceImplTest;

@RunWith(Suite.class)
@SuiteClasses({
                DepartmentServiceImplTest.class,
                EmployeeServiceImplTest.class,
                LeaveBalanceServiceImplTest.class
                
})

public class TestSuit {

}