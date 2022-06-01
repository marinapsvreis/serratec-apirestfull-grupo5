package com.residencia.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.entity.Cliente;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByCpf(String cpf);
    List<Cliente> findByEmail(String email);
}
