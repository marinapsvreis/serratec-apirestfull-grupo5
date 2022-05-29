package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> findAllCliente(){
		return clienteRepository.findAll();	
	}
	
	public Cliente findByIdCliente(Integer idCliente){
		return clienteRepository.findById(idCliente).isPresent() ?
				clienteRepository.findById(idCliente).get() : null;
	}
	
	public Cliente saveCliente(Cliente cliente){
		return clienteRepository.save(cliente);
	}
	
	public Cliente updateCliente(Cliente cliente){
		return clienteRepository.save(cliente);
	}
	
	public void deleteByIdCliente(Integer idCliente){
		clienteRepository.deleteById(idCliente);
	}
}
