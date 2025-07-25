package com.gabriel.gerenciadorDeBiblioteca.repositories;

import com.gabriel.gerenciadorDeBiblioteca.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);
}
