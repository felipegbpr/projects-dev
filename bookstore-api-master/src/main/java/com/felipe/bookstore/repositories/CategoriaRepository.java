package com.felipe.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipe.bookstore.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	

}
