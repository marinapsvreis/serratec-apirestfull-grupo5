package com.residencia.ecommerce.controller;

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

import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.service.EnderecoService;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<Endereco>> findAllEndereco() {
		List<Endereco> enderecoList = enderecoService.findAllEndereco();
		return new ResponseEntity<>(enderecoList, HttpStatus.OK);
	}

	@GetMapping("/{idEndereco}")
	public ResponseEntity<Endereco> findEnderecoById(@PathVariable Integer idEndereco) {
		Endereco endereco = enderecoService.findByIdEndereco(idEndereco);
		return new ResponseEntity<>(endereco, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Endereco> saveEndereco(@RequestBody Endereco endereco) {
		enderecoService.updateEndereco(endereco);
		return new ResponseEntity<>(enderecoService.saveEndereco(endereco), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Endereco> updateEndereco(@PathVariable Endereco endereco) {
		Endereco enderecoAtualizado = enderecoService.updateEndereco(endereco);
		return new ResponseEntity<>(enderecoAtualizado, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteCliente(Integer idEndereco) {
		enderecoService.deleteByIdEndereco(idEndereco);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
