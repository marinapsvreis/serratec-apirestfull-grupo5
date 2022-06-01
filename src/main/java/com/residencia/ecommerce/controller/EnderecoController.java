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

import com.residencia.ecommerce.dto.EnderecoDTO;
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
	public ResponseEntity<List<EnderecoDTO>> findAllEndereco() {
		return new ResponseEntity<>(enderecoService.findAllEndereco(), HttpStatus.OK);
	}

	@GetMapping("/{idEndereco}")
	public ResponseEntity<EnderecoDTO> findEnderecoById(@PathVariable Integer idEndereco) {
		return new ResponseEntity<>(enderecoService.findByIdEndereco(idEndereco), HttpStatus.OK);
	}

	@PostMapping("/salvar")
	public ResponseEntity<EnderecoDTO> salvarEnderecoViaCep(@RequestParam Integer idCliente, @RequestParam String cep, @RequestParam Integer numero){
		EnderecoDTO enderecoDTO = enderecoService.saveEnderecoDTO(cep, numero);
		Boolean status = clienteService.atualizarEnderecoCliente(idCliente, enderecoDTO.getIdEndereco());
		if(!status) {
			throw new NoSuchElementFoundException("Não foi possível encontrar o cliente com o id " + idCliente);
		}else {
			return new ResponseEntity<>(enderecoDTO, HttpStatus.CREATED);
		}
	}

	@PutMapping
	public ResponseEntity<EnderecoDTO> updateEndereco(@RequestBody EnderecoDTO enderecoDTO) {
		System.out.println(enderecoDTO);
		return new ResponseEntity<>(enderecoService.updateEnderecoDTO(enderecoDTO), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteCliente(@RequestParam Integer idEndereco) {
		enderecoService.deleteByIdEndereco(idEndereco);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
