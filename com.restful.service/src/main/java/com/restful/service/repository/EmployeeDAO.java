package com.restful.service.repository;

import com.restful.service.model.Employee;
import com.restful.service.model.Employees;
import com.restful.service.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {
    private static Employees list = new Employees();

    static {
        list.getEmployeeList().add(new Employee("John", "Thomson", 1));
        list.getEmployeeList().add(new Employee("Soul", "Twilio", 2, new Role("superUser")));
        list.getEmployeeList().add(new Employee("Philip", "Cameron", 3));
        list.getEmployeeList().add(new Employee("Jonathan", "Bronson", 4, new Role("superAdmin")));
    }

    public Employees getAllEmployees() {
        return list;
    }

    public void addEmployee(Employee employee) {
        list.getEmployeeList().add(employee);
    }

    public void assignRoleToEmployee(Employee employee, String role) {
        if (getAllEmployees().getEmployeeList().contains(employee)) {
            employee.setRole(new Role(role));
        }
    }
}
