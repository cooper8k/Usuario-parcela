package com.perfil.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReponseDto {
    @NotNull(message = "Id es requerido")
    private Long id;
    @NotNull(message = "Email es requerido")
    private String email;
    @NotNull(message = "Mensaje es requerido")
    private String mensaje;
}
