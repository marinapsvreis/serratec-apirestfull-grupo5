package com.residencia.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
