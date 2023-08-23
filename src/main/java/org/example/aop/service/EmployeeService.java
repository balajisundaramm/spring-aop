package org.example.aop.service;

import org.example.aop.model.Employee;
import org.springframework.stereotype.Service;

/**
 * @author = mbalaji on 23-08-2023
 * @project = spring-aop
 */
@Service
public class EmployeeService {

    public Employee createEmployee(String name, String empId) {

        Employee emp = new Employee();
        emp.setName(name);
        emp.setEmpId(empId);
        return emp;
    }

    public void deleteEmployee(String empId) {

    }
}
