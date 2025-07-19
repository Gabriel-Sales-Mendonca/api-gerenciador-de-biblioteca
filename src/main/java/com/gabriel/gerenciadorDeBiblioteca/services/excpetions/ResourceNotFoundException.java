package com.gabriel.gerenciadorDeBiblioteca.services.excpetions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
