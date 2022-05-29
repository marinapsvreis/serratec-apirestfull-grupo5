package com.residencia.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
