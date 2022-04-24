package com.restful.service.controller;

import com.restful.service.model.Employee;
import com.restful.service.model.Roles;
import com.restful.service.repository.EmployeeDAO;
import com.restful.service.repository.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/assign")
public class AssignRoleController {

    @Autowired
    private EmployeeDAO employeeDao;

    @Autowired
    private RoleDAO roleDAO;

    @PostMapping(path = "/{id}/{roleName}", consumes = "application/json", produces = "application/json")
    public Employee assignRoleToEmployee(@PathVariable int id, @PathVariable String roleName) {

        Employee existingEmployee = employeeDao.getAllEmployees().getEmployeeList().stream()
                .filter(specificEmployee -> specificEmployee.getEmployeeId() == id)
                .findFirst().orElseThrow(() -> new RuntimeException("Can't find employeeId " + id));

        Roles allRoles = roleDAO.getAllRoles();
        allRoles.addRole(roleName);
        employeeDao.assignRoleToEmployee(existingEmployee, roleName);

        return existingEmployee;
    }
}
