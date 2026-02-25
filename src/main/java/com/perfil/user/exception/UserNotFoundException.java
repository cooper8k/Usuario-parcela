package com.perfil.user.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String mensaje){
        super(mensaje);
    }
    
}
//  Excepción personalizada para indicar que un usuario no fue encontrado.
//  Esta clase extiende RuntimeException, lo que permite lanzar esta excepción sin necesidad de declararla en la firma de los métodos.
// El constructor acepta un mensaje personalizado que describe el error específico.
// Esta excepción puede ser utilizada en el servicio de usuario para manejar casos donde un usuario no existe en la base de datos.
 
