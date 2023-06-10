package com.forum.microservice.login.user;

import com.forum.microservice.login.CustomUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDataService {


    final CustomUserRepository customUserRepository;

    public CustomUserDataService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    public CustomUser getUserById(Long id){
        Optional<CustomUser> customUser = customUserRepository.findById(id);
        return customUser.orElse(null);
    }

    public CustomUser getUserByEmail(String email){
        Optional<CustomUser> customUser = customUserRepository.findByEmail(email);
        return customUser.orElse(null);
    }

    public void createOrUpdateUser(CustomUser customUser){
        customUserRepository.save(customUser);
    }

}

