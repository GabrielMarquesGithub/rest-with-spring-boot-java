package br.com.nikisgabriel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nikisgabriel.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
