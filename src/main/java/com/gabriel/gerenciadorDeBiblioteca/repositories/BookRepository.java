package com.gabriel.gerenciadorDeBiblioteca.repositories;

import com.gabriel.gerenciadorDeBiblioteca.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
