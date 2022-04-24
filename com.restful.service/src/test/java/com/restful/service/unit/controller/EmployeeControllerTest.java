package com.restful.service.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restful.service.Categories;
import com.restful.service.SpringBootTaskApplication;
import com.restful.service.controller.EmployeeController;
import com.restful.service.model.Employee;
import com.restful.service.model.Employees;
import com.restful.service.repository.EmployeeDAO;
import com.restful.service.repository.RoleDAO;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@Category(Categories.UnitTests.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeDAO employeeDAO;

    @Test
    public void getAllEmployeeSuccessTest() throws Exception {
        Employee employee = new Employee("John", "Karter", 100);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        Employees employees = new Employees();
        employees.setEmployeeList(employeeList);
        when(employeeDAO.getAllEmployees()).thenReturn(employees);

        mvc.perform(get("/employees/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeList", hasSize(1)))
                .andExpect(jsonPath("$.employeeList.[0].firstName", is(employees.getEmployeeList().get(0).getFirstName())))
                .andExpect(jsonPath("$.employeeList.[0].lastName", is(employees.getEmployeeList().get(0).getLastName())))
                .andExpect(jsonPath("$.employeeList.[0].employeeId", is(employees.getEmployeeList().get(0).getEmployeeId())));
    }

    @Test
    public void getAllEmployeeInvalidEndpointTest() throws Exception {
        Employee employee = new Employee("Smith", "Teddy", 500);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        Employees employees = new Employees();
        employees.setEmployeeList(employeeList);
        when(employeeDAO.getAllEmployees()).thenReturn(employees);

        mvc.perform(get("/employeees").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postNewEmployeeTest() throws Exception {
        Employee employee = new Employee("Won", "Reeth", 800);
        EmployeeDAO employeeDAO = mock(EmployeeDAO.class);
        doCallRealMethod().when(employeeDAO).addEmployee(employee);

        mvc.perform(post("/employees/").contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(asJsonString(employee)))
                .andExpect(status().isCreated());
    }

    @Test
    public void applicationContextLoaded() {
    }

    @Test
    public void applicationContextTest() {
        SpringBootTaskApplication.main(new String[] {});
    }

    @Test
    public void testRoleDAOClass() {
        RoleDAO roleDAO = Mockito.mock(RoleDAO.class);
        when(roleDAO.getAllRoles()).thenThrow(new NullPointerException());
    }

    @Test
    public void testEmployeeDAOClass() {
        EmployeeDAO employeeDAO = Mockito.mock(EmployeeDAO.class);
        when(employeeDAO.getAllEmployees()).thenThrow(new NullPointerException());
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
