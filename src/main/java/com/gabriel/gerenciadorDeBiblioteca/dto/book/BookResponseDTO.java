package com.gabriel.gerenciadorDeBiblioteca.dto.book;

import com.gabriel.gerenciadorDeBiblioteca.entities.Book;

public record BookResponseDTO(
        Long id,
        String title
) {
}
