package com.gabriel.gerenciadorDeBiblioteca.services;

import com.gabriel.gerenciadorDeBiblioteca.dto.book.BookCreateRequestDTO;
import com.gabriel.gerenciadorDeBiblioteca.dto.book.BookCreateResponseDTO;
import com.gabriel.gerenciadorDeBiblioteca.dto.book.BookResponseDTO;
import com.gabriel.gerenciadorDeBiblioteca.entities.Book;
import com.gabriel.gerenciadorDeBiblioteca.repositories.BookRepository;
import com.gabriel.gerenciadorDeBiblioteca.services.excpetions.BadRequestException;
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
        Book bookCreated = BookCreator.createValidBook();

        List<BookResponseDTO> books = this.bookService.findAll();

        Assertions.assertThat(books).isNotEmpty();
        Assertions.assertThat(books.getFirst().title()).isEqualTo(bookCreated.getTitle());
    }

    @Test
    @DisplayName("Cria book")
    void create_CreateBook_WhenSuccessful() {
        Book book = BookCreator.createValidBook();
        BookCreateRequestDTO bookToSave = new BookCreateRequestDTO(book.getTitle());

        BookCreateResponseDTO bookCreated = this.bookService.create(bookToSave);

        Assertions.assertThat(bookCreated).isNotNull();
        Assertions.assertThat(bookCreated.title()).isEqualTo(bookToSave.title());
    }

    @Test
    @DisplayName("Teste se lança exceção quando o título está em branco")
    void create_ThrowException_WhenEmptyTitle() {
        BookCreateRequestDTO bookCreateRequestDTO = BookCreator.createBookRequestDTOWithEmptyTitle();

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> this.bookService.create(bookCreateRequestDTO))
                .withMessage("Título está em branco");
    }

}