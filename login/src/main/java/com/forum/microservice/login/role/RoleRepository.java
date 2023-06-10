package com.forum.microservice.login.role;

import com.forum.microservice.login.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Optional<Role> findById(Long id);
    public Optional<Role> findByName(String name);

}
