package com.perfil.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
        http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/v1/user/login", "/api/v1/user/registrar", "/api/v1/user/eliminar/**").permitAll()
            .anyRequest().authenticated()
        );
        return http.build();
    }
}
// Configuración de seguridad para la aplicación utilizando Spring Security.
// La anotación @Configuration indica que esta clase contiene configuraciones de Spring.
// El método filterChain define la cadena de filtros de seguridad para las solicitudes HTTP.
// Se desactiva la protección CSRF para simplificar las pruebas (no recomendado para producción).
// Se permite el acceso sin autenticación a las rutas de login y registro, mientras que todas las demás rutas requieren autenticación.