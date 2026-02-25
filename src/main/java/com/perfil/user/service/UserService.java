package com.perfil.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.perfil.user.dto.UserReponseDto;
import com.perfil.user.exception.UserNotFoundException;
import com.perfil.user.model.User;
import com.perfil.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // para guardar un usario en un bash

    public User registerUser (User user){
        // validar si el email ya existe
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("El correo ya esta en uso");
        }
        // validar contraseña 
        String password= user.getPassword();
        String passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$";
        if (!password.matches(passwordPattern)){
            throw new IllegalArgumentException("La contraseña no cumple con los requisitos de seguridad");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword())); // aca se manda encriptada la contrasena
        return userRepository.save(user);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public UserReponseDto eliminarUsuario(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));
    userRepository.delete(user);
    return new UserReponseDto(user.getId(), user.getEmail(), "Usuario eliminado correctamente");
}

}
