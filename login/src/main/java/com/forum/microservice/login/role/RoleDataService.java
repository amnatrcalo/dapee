package com.forum.microservice.login.role;

import com.forum.microservice.login.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleDataService {

    @Autowired
    RoleRepository roleRepository;

    public Role getRoleById(Long id){
        Optional<Role> role = roleRepository.findById(id);
        return role.orElse(null);
    }

    public Role getRoleByName(String name){
        Optional<Role> role = roleRepository.findByName(name);
        return role.orElse(null);
    }
}

