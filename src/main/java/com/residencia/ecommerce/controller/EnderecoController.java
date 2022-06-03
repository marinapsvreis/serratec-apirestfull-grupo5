package com.residencia.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.residencia.ecommerce.dto.EnderecoDTO;
import com.residencia.ecommerce.exception.EnderecoException;
import com.residencia.ecommerce.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<EnderecoDTO>> findAllEndereco() {
		return new ResponseEntity<>(enderecoService.findAllEndereco(), HttpStatus.OK);
	}

	@GetMapping("/{idEndereco}")
	public ResponseEntity<EnderecoDTO> findEnderecoById(@PathVariable Integer idEndereco) throws Exception {

		return new ResponseEntity<>(enderecoService.findByIdEndereco(idEndereco), HttpStatus.OK);
	}

	@PostMapping("/salvar")
	public ResponseEntity<EnderecoDTO> salvarEnderecoViaCep(@RequestParam Integer idCliente, @RequestParam String cep,
			@RequestParam Integer numero) throws Exception {

		return new ResponseEntity<>(enderecoService.saveEnderecoDTO(cep, numero, idCliente), HttpStatus.CREATED);
	}

	
	@PutMapping
	public ResponseEntity<EnderecoDTO> updateEndereco(@RequestParam Integer idEndereco,
			@Valid @RequestBody EnderecoDTO enderecoDTO) throws Exception {
		return new ResponseEntity<>(enderecoService.updateEnderecoDTO(idEndereco, enderecoDTO), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteCliente(@RequestParam Integer idEndereco) throws Exception {

		enderecoService.deleteByIdEndereco(idEndereco);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
