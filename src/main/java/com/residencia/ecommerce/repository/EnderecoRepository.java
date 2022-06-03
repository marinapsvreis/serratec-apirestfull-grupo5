package com.residencia.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
	List<Endereco> findByCep(String cep);
}
