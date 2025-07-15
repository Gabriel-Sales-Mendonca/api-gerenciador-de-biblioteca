package com.gabriel.gerenciadorDeBiblioteca.repositories;

import com.gabriel.gerenciadorDeBiblioteca.entities.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Testes para Book Repository")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Cria Book")
    void create_PersistBook_WhenSuccessfull() {
        Book bookToSave = this.createBook();
        Book bookCreated = this.bookRepository.save(bookToSave);

        Assertions.assertThat(bookCreated).isNotNull();
        Assertions.assertThat(bookCreated.getTitle()).isEqualTo(bookToSave.getTitle());
    }

    @Test
    @DisplayName("Atualiza Book")
    void update_UpdateBook_WhenSuccessfull() {
        Book bookToCreate = this.createBook();
        Book bookCreated = this.bookRepository.save(bookToCreate);

        bookCreated.setTitle("Outro titulo");
        Book bookUpdated = this.bookRepository.save(bookCreated);

        Assertions.assertThat(bookUpdated.getTitle()).isEqualTo("Outro titulo");
    }

    @Test
    @DisplayName("Busca por ID")
    void find_FindById_WhenSuccessfull() {
        Book bookToCreate = this.createBook();
        Book bookCreated = this.bookRepository.save(bookToCreate);

        Optional<Book> bookOptional = this.bookRepository.findById(bookCreated.getId());

        Assertions.assertThat(bookOptional).isNotNull();
        Assertions.assertThat(bookOptional.get().getId()).isEqualTo(bookCreated.getId());
    }

    @Test
    @DisplayName("Busca por Title")
    void find_FindByTitle_WhenSuccessfull() {
        Book bookToCreate = this.createBook();
        Book bookCreated = this.bookRepository.save(bookToCreate);

        List<Book> books = this.bookRepository.findByTitle(bookCreated.getTitle());

        Assertions.assertThat(books)
                .isNotEmpty()
                .contains(bookCreated);
    }

    @Test
    @DisplayName("Deleta Book por ID")
    void delete_DeleteBookById_WhenSuccessfull() {
        Book bookToCreate = this.createBook();
        Book bookCreated = this.bookRepository.save(bookToCreate);

        this.bookRepository.deleteById(bookCreated.getId());

        Optional<Book> bookOptional = this.bookRepository.findById(bookCreated.getId());

        Assertions.assertThat(bookOptional).isEmpty();
    }

    private Book createBook() {
        return new Book("Titulo Exemplo");
    }

}