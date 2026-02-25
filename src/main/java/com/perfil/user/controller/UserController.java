package com.perfil.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfil.user.config.JwtUtil;
import com.perfil.user.dto.JwtResponse;
import com.perfil.user.dto.LoginRequest;
import com.perfil.user.dto.UserReponseDto;
import com.perfil.user.exception.UserNotFoundException;
import com.perfil.user.model.User;
import com.perfil.user.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario (@RequestBody User user){
        try{
            User guardarUser = userService.registerUser(user);
            return ResponseEntity.status(201).body(guardarUser);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error al registrar");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new JwtResponse(token));
    }

@DeleteMapping("/eliminar/{id}")
public ResponseEntity<?> eliminarUsuario (@PathVariable Long id){
    try {
        UserReponseDto respuesta = userService.eliminarUsuario(id);
        return ResponseEntity.ok(respuesta);
    }catch(UserNotFoundException e){
        return ResponseEntity.status(404).body(e.getMessage());
    } catch (Exception e){
        return ResponseEntity.status(500).body("Error al eliminar usuario");
    }
}
}
