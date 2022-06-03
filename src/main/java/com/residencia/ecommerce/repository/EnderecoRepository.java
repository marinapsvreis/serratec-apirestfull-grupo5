package com.residencia.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.entity.Produto;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
	List<Endereco> findByCep(String cep);
}
