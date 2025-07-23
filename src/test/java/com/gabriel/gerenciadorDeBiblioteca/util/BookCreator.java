package com.gabriel.gerenciadorDeBiblioteca.util;

import com.gabriel.gerenciadorDeBiblioteca.dto.book.BookCreateRequestDTO;
import com.gabriel.gerenciadorDeBiblioteca.entities.Book;

public class BookCreator {

    public static Book createBook() {
        return new Book("Titulo Exemplo");
    }

    public static Book createValidBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Titulo Exemplo");

        return book;
    }

    public static BookCreateRequestDTO createValidBookRequestDTO() {
        return new BookCreateRequestDTO("Titulo Exemplo");
    }

    public static BookCreateRequestDTO createBookRequestDTOWithEmptyTitle() {
        return new BookCreateRequestDTO("      ");
    }

}
