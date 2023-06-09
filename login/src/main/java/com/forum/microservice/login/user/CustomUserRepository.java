package com.forum.microservice.login.user;


import com.forum.microservice.login.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {

    public Optional<CustomUser> findById(Long id);
    public Optional<CustomUser> findByEmail(String email);

}
