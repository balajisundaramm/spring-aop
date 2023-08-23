package org.example.aop.model;

/**
 * @author = mbalaji on 23-08-2023
 * @project = spring-aop
 */

public class Employee {
    private String empId;
    private String name;

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

}
