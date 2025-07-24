package com.gabriel.gerenciadorDeBiblioteca.mapper;

import com.gabriel.gerenciadorDeBiblioteca.dto.book.BookCreateRequestDTO;
import com.gabriel.gerenciadorDeBiblioteca.dto.book.BookResponseDTO;
import com.gabriel.gerenciadorDeBiblioteca.entities.Book;

public class BookMapper {

    public static Book toEntity(BookCreateRequestDTO dto) {
        return new Book(
                dto.title()
        );
    }

    public static BookResponseDTO toDTO(Book entity) {
        return new BookResponseDTO(
                entity.getId(),
                entity.getTitle()
        );
    }

}
