package com.gabriel.gerenciadorDeBiblioteca.services;

import com.gabriel.gerenciadorDeBiblioteca.dto.book.BookCreateRequestDTO;
import com.gabriel.gerenciadorDeBiblioteca.dto.book.BookCreateResponseDTO;
import com.gabriel.gerenciadorDeBiblioteca.dto.book.BookResponseDTO;
import com.gabriel.gerenciadorDeBiblioteca.entities.Book;
import com.gabriel.gerenciadorDeBiblioteca.repositories.BookRepository;
import com.gabriel.gerenciadorDeBiblioteca.services.excpetions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponseDTO> findAll() {
        List<Book> books = this.bookRepository.findAll();
        List<BookResponseDTO> bookResponseDTOList = new ArrayList<>();

        for (Book book : books) {
            bookResponseDTOList.add(new BookResponseDTO(book));
        }

        return bookResponseDTOList;
    }

    public BookResponseDTO findById(Long id) {
        Book book = this.bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado, id: " + id));

        return new BookResponseDTO(book);
    }

    public BookCreateResponseDTO create(BookCreateRequestDTO bookCreateRequestDTO) {
        Book bookToSave = new Book(bookCreateRequestDTO);

        Book bookCreated = this.bookRepository.save(bookToSave);

        return new BookCreateResponseDTO(
                bookCreated.getId(),
                bookCreated.getTitle()
        );
    }

}
