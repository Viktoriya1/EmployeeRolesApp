package com.restful.service.unit.model;

import com.restful.service.Categories;
import com.restful.service.model.Employee;
import com.restful.service.model.Role;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.assertEquals;

@Category(Categories.UnitTests.class)
public class ModelTest {

    private Employee employee;
    private Employee employeeWithRole;
    private Role role;

    public ModelTest() {
        employee = new Employee("Tom", "Jerry", 1000, new Role("user"));
        employeeWithRole = new Employee("Tom", "Jerry", 1000, new Role("user"));
        role = new Role("admin");
    }

    @Test
    public void testEmployeeModelClass() {
        assertEquals("Employee IDs are different", employee.getEmployeeId(), 1000);
        assertEquals("Employee firstNames are different", employee.getFirstName(), "Tom");
        assertEquals("Employee lastNames are different", employee.getLastName(), "Jerry");
        assertEquals("Employee roleNames are different", employeeWithRole.getRoleName().toString(),
                new Role("user").toString());
    }

    @Test
    public void testRoleModelClass() {
        assertEquals("Role names are different", role.getRoleName(), "admin");
    }
}
