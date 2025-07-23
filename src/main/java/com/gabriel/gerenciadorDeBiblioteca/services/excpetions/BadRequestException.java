package com.gabriel.gerenciadorDeBiblioteca.services.excpetions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
