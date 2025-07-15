package com.gabriel.gerenciadorDeBiblioteca.dto.location;

public record LocationCreateRequestDTO(
        String shelf,
        int column
) {
}
