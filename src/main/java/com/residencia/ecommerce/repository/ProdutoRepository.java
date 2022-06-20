package com.residencia.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.entity.Categoria;
import com.residencia.ecommerce.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	List<Produto> findByDescricaoProduto(String descricaoProduto);
	List<Produto> findByCategoria(Categoria categoria);
}
