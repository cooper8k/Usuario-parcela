package com.perfil.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfil.user.model.User;
import com.perfil.user.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario (@RequestBody User user){
        try{
            User guardarUser = userService.registerUser(user);
            return ResponseEntity.status(201).body(guardarUser);
        }catch(Exception e){

            return ResponseEntity.badRequest().body("Error al registrar");
        }
    }

}
