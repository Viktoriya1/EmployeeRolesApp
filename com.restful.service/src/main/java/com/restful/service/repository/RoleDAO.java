package com.restful.service.repository;

import com.restful.service.model.Role;
import com.restful.service.model.Roles;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDAO {

    private static Roles list = new Roles();

    static {
        list.getRoleList().add(new Role("admin"));
        list.getRoleList().add(new Role("user"));
        list.getRoleList().add(new Role("editor"));
    }

    public Roles getAllRoles() {
        return list;
    }

    public void addRole(Role role) {
        list.getRoleList().add(role);
    }
}
