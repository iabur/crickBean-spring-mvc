package com.crickbean.application.service;

import java.util.List;

import com.crickbean.application.model.Role;
import com.crickbean.application.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    //firstly check by role
    //if not exist the roleName create a new role in database
    //return the same role
    public Role create(Role role) {
        if (exists(role.getAuthority())) {
            return null;
        } else {
            authorityRepository.save(role);
            return role;
        }
    }

    //find role by roleName
    public Role findByRoleName(String roleName) {
        return authorityRepository.findByRoleName(roleName);
    }

    //check the role exist or not by roleName
    public boolean exists(String roleName) {
        if (findByRoleName(roleName) != null) {
            return true;
        } else {
            return false;
        }
    }

    //find all role from database
    public List<Role> listAllAuthorities() {
        return authorityRepository.findAll();
    }
}