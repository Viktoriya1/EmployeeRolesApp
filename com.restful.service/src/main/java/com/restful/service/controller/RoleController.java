package com.restful.service.controller;

import com.restful.service.model.Role;
import com.restful.service.model.Roles;
import com.restful.service.repository.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(path = "/roles")
public class RoleController {

    @Autowired
    private RoleDAO roleDAO;

    @GetMapping(path = "/", produces = "application/json")
    public Roles getRoles() {
        return roleDAO.getAllRoles();
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addRole(@RequestBody Role role) {
        roleDAO.addRole(role);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{name}")
                .buildAndExpand(role.getRoleName())
                .toUri();
        return ResponseEntity.created(location).build();
    }


}
