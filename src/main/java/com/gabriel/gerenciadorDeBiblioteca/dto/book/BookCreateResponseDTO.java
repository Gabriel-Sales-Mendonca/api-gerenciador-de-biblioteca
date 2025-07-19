package com.gabriel.gerenciadorDeBiblioteca.dto.book;

import com.gabriel.gerenciadorDeBiblioteca.entities.Book;

public record BookCreateResponseDTO(
        Long id,
        String title
) {
    public BookCreateResponseDTO(Book book) {
        this(book.getId(), book.getTitle());
    }
}
