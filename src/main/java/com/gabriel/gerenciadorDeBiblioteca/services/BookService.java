package com.gabriel.gerenciadorDeBiblioteca.services;

import com.gabriel.gerenciadorDeBiblioteca.entities.Book;
import com.gabriel.gerenciadorDeBiblioteca.repositories.BookRepository;
import com.gabriel.gerenciadorDeBiblioteca.services.excpetions.BadRequestException;
import com.gabriel.gerenciadorDeBiblioteca.services.excpetions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    public Book findById(Long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado, id: " + id));
    }

    public Book create(Book bookToSave) {
        if (bookToSave.getTitle().isBlank()) {
            throw new BadRequestException("Título está em branco");
        }

        return this.bookRepository.save(bookToSave);
    }

    public void deleteById(Long id) {
        findById(id);
        this.bookRepository.deleteById(id);
    }

}
