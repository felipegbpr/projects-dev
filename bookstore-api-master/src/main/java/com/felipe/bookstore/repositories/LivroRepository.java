package com.felipe.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.bookstore.domain.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

}
