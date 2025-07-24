package com.gabriel.gerenciadorDeBiblioteca.controllers;

import com.gabriel.gerenciadorDeBiblioteca.dto.book.BookCreateRequestDTO;
import com.gabriel.gerenciadorDeBiblioteca.dto.book.BookResponseDTO;
import com.gabriel.gerenciadorDeBiblioteca.entities.Book;
import com.gabriel.gerenciadorDeBiblioteca.mapper.BookMapper;
import com.gabriel.gerenciadorDeBiblioteca.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<BookResponseDTO> create(@RequestBody BookCreateRequestDTO bookCreateRequestDTO) {
        Book bookToSave = BookMapper.toEntity(bookCreateRequestDTO);
        Book bookCreated = this.bookService.create(bookToSave);
        BookResponseDTO bookResponseDTO = BookMapper.toDTO(bookCreated);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(bookResponseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(bookResponseDTO);
    }

}
