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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAllCliente() {
		return new ResponseEntity<>(clienteService.findAllCliente(), HttpStatus.OK);
	}

	@GetMapping("/{idCliente}")
	public ResponseEntity<ClienteDTO> findClienteById(@PathVariable Integer idCliente) {
		return new ResponseEntity<>(clienteService.findClienteById(idCliente), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> saveCliente(@RequestBody ClienteDTO clienteDTO) throws Exception {
		return new ResponseEntity<>(clienteService.saveCliente(clienteDTO), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ClienteDTO> updateCliente(@RequestParam Integer idCliente, @RequestBody ClienteDTO clienteDTO) {
		return new ResponseEntity<>(clienteService.updateCliente(idCliente, clienteDTO), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteCliente(@RequestParam Integer idCliente) {
		clienteService.deleteClienteById(idCliente);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
