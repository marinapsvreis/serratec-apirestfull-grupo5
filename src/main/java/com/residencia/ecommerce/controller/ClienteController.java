package com.residencia.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.exception.EnderecoException;
import com.residencia.ecommerce.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente", description = "endpoints")
@CrossOrigin("*")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PasswordEncoder encoder;

	@GetMapping
	@Operation(summary = "Listar todos os clientes")
	public ResponseEntity<List<ClienteDTO>> findAllCliente() {
		return new ResponseEntity<>(clienteService.findAllCliente(), HttpStatus.OK);
	}

	@GetMapping("/{idCliente}")
	@Operation(summary = "Listar cliente via ID Path")
	public ResponseEntity<ClienteDTO> findClienteById(@PathVariable Integer idCliente) {
		return new ResponseEntity<>(clienteService.findClienteById(idCliente), HttpStatus.OK);
	}
	
	@GetMapping("/logar_cliente")
	public ResponseEntity<Boolean> logarCliente(@RequestParam String email, @RequestParam String password){

		Cliente cliente = clienteService.findByEmail(email);
		if(cliente == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		
		boolean valid = encoder.matches(password, cliente.getPassword());
		
		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		
		return ResponseEntity.status(status).body(valid);
	}

	@PostMapping
	@Operation(summary = "Cadastrar cliente")
	public ResponseEntity<ClienteDTO> saveCliente(@Valid @RequestBody ClienteDTO clienteDTO) throws Exception {
		clienteDTO.setPassword(encoder.encode(clienteDTO.getPassword()));
		return new ResponseEntity<>(clienteService.saveCliente(clienteDTO), HttpStatus.CREATED);
	}

	@PutMapping
	@Operation(summary = "Atualizar cliente passando todos os dados")
	public ResponseEntity<ClienteDTO> updateCliente(@RequestParam Integer idCliente,
			@Valid @RequestBody ClienteDTO clienteDTO) throws EnderecoException, Exception {
		return new ResponseEntity<>(clienteService.updateCliente(idCliente, clienteDTO), HttpStatus.OK);
	}

	@DeleteMapping
	@Operation(summary = "Deletar cliente via ID")
	public ResponseEntity<String> deleteCliente(@RequestParam Integer idCliente) throws Exception {
		clienteService.deleteClienteById(idCliente);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
