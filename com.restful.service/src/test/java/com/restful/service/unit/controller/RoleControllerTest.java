package com.restful.service.unit.controller;

import com.restful.service.Categories;
import com.restful.service.controller.RoleController;
import com.restful.service.model.Role;
import com.restful.service.model.Roles;
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

import java.util.HashSet;
import java.util.Set;

import static com.restful.service.unit.controller.EmployeeControllerTest.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RoleController.class)
@Category(Categories.UnitTests.class)
public class RoleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoleDAO roleDAO;

    @Test
    public void getAllRolesSuccessTest() throws Exception {
        Role role = new Role("super_admin");
        Set<Role> rolesList = new HashSet<>();
        rolesList.add(role);
        Roles roles = new Roles();
        roles.setRoleList(rolesList);
        Mockito.when(roleDAO.getAllRoles()).thenReturn(roles);

        mvc.perform(get("/roles/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roleList", hasSize(1)));
    }

    @Test
    public void getAllRolesInvalidEndpointTest() throws Exception {
        Role role = new Role("super_admin");
        Set<Role> rolesList = new HashSet<>();
        rolesList.add(role);
        Roles roles = new Roles();
        roles.setRoleList(rolesList);
        Mockito.when(roleDAO.getAllRoles()).thenReturn(roles);

        mvc.perform(get("/rolees").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void postNewEmployeeTest() throws Exception {
        Role role = new Role("super_admin");
        RoleDAO roleDAO = mock(RoleDAO.class);
        doCallRealMethod().when(roleDAO).addRole(role);

        mvc.perform(post("/roles/").contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(asJsonString(role)))
                .andExpect(status().isCreated());
    }

}
