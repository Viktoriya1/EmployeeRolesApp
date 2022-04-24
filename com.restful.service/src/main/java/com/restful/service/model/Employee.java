package com.restful.service.model;

public class Employee {

    private String firstName;
    private String lastName;
    private int employeeId;
    private Role role;

    public Employee(String firstName, String lastName, int employeeId, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.role = role;
    }

    public Employee(String firstName, String lastName, int employeeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
    }

    public Employee() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Role getRoleName() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
