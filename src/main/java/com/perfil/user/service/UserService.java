package com.perfil.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.perfil.user.model.User;
import com.perfil.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // para guardar un usario en un bash

    public User registerUser (User user){

        user.setPassword(passwordEncoder.encode(user.getPassword())); // aca se manda encriptado
        return userRepository.save(user);
    }
}
