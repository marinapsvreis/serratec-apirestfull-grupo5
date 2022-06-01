package com.residencia.ecommerce.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.ClienteService;
import com.residencia.ecommerce.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private ClienteService clienteService;

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
	public ResponseEntity<Endereco> saveEndereco(@RequestParam Endereco endereco) {
		enderecoService.updateEndereco(endereco);
		return new ResponseEntity<>(enderecoService.saveEndereco(endereco), HttpStatus.OK);
	}

	@PostMapping("/salvar")
	public ResponseEntity<Endereco> salvarEnderecoViaCep(@RequestParam Integer idCliente, @RequestParam String cep, @RequestParam Integer numero){
		Endereco endereco = enderecoService.saveEnderecoDTO(cep, numero);
		System.out.println("Entrando no método");
		Boolean status = clienteService.atualizarEnderecoCliente(idCliente, endereco.getIdEndereco());
		if(!status) {
			throw new NoSuchElementFoundException("Não foi possível encontrar o cliente com o id " + idCliente);
		}else {
			return new ResponseEntity<>(endereco, HttpStatus.CREATED);
		}
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
