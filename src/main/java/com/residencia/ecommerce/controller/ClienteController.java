package com.residencia.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> findAllCliente() {
		List<Cliente> clienteList = clienteService.findAllCliente();
		return new ResponseEntity<>(clienteList, HttpStatus.OK);
	}

	@GetMapping("/{idCliente}")
	public ResponseEntity<Cliente> findClienteById(@PathVariable Integer idCliente) {
		Cliente cliente = clienteService.findByIdCliente(idCliente);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
		clienteService.updateCliente(cliente);
		return new ResponseEntity<>(clienteService.saveCliente(cliente), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Cliente> updateCliente(@PathVariable Cliente Cliente) {
		Cliente clienteAtualizado = clienteService.updateCliente(Cliente);
		return new ResponseEntity<>(clienteAtualizado, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCliente(Integer idCliente) {
		clienteService.deleteByIdCliente(idCliente);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
