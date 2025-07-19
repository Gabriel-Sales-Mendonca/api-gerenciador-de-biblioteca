package com.gabriel.gerenciadorDeBiblioteca.services;

import com.gabriel.gerenciadorDeBiblioteca.dto.book.BookCreateRequestDTO;
import com.gabriel.gerenciadorDeBiblioteca.dto.book.BookCreateResponseDTO;
import com.gabriel.gerenciadorDeBiblioteca.entities.Book;
import com.gabriel.gerenciadorDeBiblioteca.repositories.BookRepository;
import com.gabriel.gerenciadorDeBiblioteca.util.BookCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Testes para Book Service")
class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        List<Book> books = List.of(BookCreator.createValidBook());
        BDDMockito.when(bookRepository.findAll()).thenReturn(books);
        BDDMockito.when(bookRepository.save(ArgumentMatchers.any())).thenReturn(BookCreator.createValidBook());
    }

    @Test
    @DisplayName("Retorna lista com books")
    void findAll_ReturnListOfBooks_WhenSuccessful() {
        List<Book> books = this.bookService.findAll();

        Assertions.assertThat(books).isNotEmpty();
        Assertions.assertThat(books.getFirst().getTitle()).isEqualTo("Titulo Exemplo");
    }

    @Test
    @DisplayName("Cria book")
    void create_CreateBook_WhenSuccessful() {
        BookCreateRequestDTO bookToSave = BookCreator.createBookRequestDTO();
        BookCreateResponseDTO bookCreated = this.bookService.create(bookToSave);

        Assertions.assertThat(bookCreated).isNotNull();
        Assertions.assertThat(bookCreated.title()).isEqualTo(bookToSave.title());
    }

}