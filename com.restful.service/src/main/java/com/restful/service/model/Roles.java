package com.restful.service.model;

import java.util.HashSet;
import java.util.Set;

public class Roles {

    private Set<Role> roleList;

    public Set<Role> getRoleList() {
        if (roleList == null) {
            roleList = new HashSet<>();
        }
        return roleList;
    }

    public void setRoleList(Set<Role> rolesList) {
        this.roleList = rolesList;
    }

    public void addRole(String newRoleName) {
        if (!roleList.contains(newRoleName)) {
            roleList.add(new Role(newRoleName));
        }
    }
}
