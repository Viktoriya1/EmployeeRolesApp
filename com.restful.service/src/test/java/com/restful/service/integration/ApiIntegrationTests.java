package com.restful.service.integration;

import com.restful.service.Categories;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@Category(Categories.IntegrationTests.class)
public class ApiIntegrationTests {

    private WebTestClient client;

    public ApiIntegrationTests() {
        client = WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080").build();
    }

    /* As an API User,
    Given the Employee Service
    When I call the third URL mapping, I get a list of Employees */
    @Test
    public void getAllEmployeesApiTest() {
        client
                .get().uri("/employees/")
                .exchange().expectStatus().isOk()
                .expectBody().jsonPath("$.employeeList").isArray();
    }

    /* As an API User,
    Given the User Service
    When I call the first URL mapping, and pass a new User JSON, it’s created on the Service*/
    @Test
    public void postNewEmployeeApiTest() {
        String jsonNewUser = "{\n" +
                "   \"firstName\":\"newUser12\",\n" +
                "   \"lastName\":\"newLastName12\",\n" +
                "   \"employeeId\":500\n" +
                "}";
        client
                .post().uri("/employees/").contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(jsonNewUser)
                .exchange().expectStatus().isCreated()
                .expectBody().isEmpty();
    }

    /* As an API User,
    Given the User Service
    When I call the third URL mapping, I get a list of Roles*/
    @Test
    public void getAllRolesApiTest() {
        client
                .get().uri("/roles/")
                .exchange().expectStatus().isOk()
                .expectBody().jsonPath("$.roleList").isArray();
    }

    /* As an API User,
    Given the User Service
    When I call the fourth URL mapping, and pass a new Role  JSON, it’s created on the Service*/
    @Test
    public void postNewRoleApiTest() {
        String jsonNewRole = "{\n" +
                "   \"roleName\":\"superuser1\"\n" +
                "}";
        client
                .post().uri("/roles/").contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(jsonNewRole)
                .exchange().expectStatus().isCreated()
                .expectBody().isEmpty();
    }

    /* As an API User,
    Given the User Service
    When I call the fifth URL mapping, and pass a User-Role association JSON, the concerned role is applied to the User*/
    @Test
    public void assignRoleToSpecificEmployeeApiTest() {
        client
                .post().uri("/assign/1/superadmin").contentType(MediaType.APPLICATION_JSON_UTF8)
                .exchange().expectStatus().isOk()
                .expectBody().jsonPath("$.firstName").isEqualTo("John")
                .jsonPath("$.employeeId").isEqualTo(1)
                .jsonPath("$.roleName.roleName").isEqualTo("superadmin");
    }
}
