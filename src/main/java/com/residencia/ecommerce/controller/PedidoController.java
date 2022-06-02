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

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.exception.PedidoFinalizadoException;
import com.residencia.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAllPedido() { 
		return new ResponseEntity<>(pedidoService.findAllPedido(), HttpStatus.OK);
	}

	@GetMapping("/{idPedido}")
	public ResponseEntity<PedidoDTO> findPedidoById(@PathVariable Integer idPedido) {
		return new ResponseEntity<>(pedidoService.findPedidoById(idPedido), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PedidoDTO> savePedido(@RequestBody PedidoDTO pedidoDTO) {
		return new ResponseEntity<>(pedidoService.savePedido(pedidoDTO), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PedidoDTO> updatePedido(@RequestParam Integer idPedido, @RequestBody PedidoDTO pedidoDTO) throws PedidoFinalizadoException {
		return new ResponseEntity<>(pedidoService.updatePedido(idPedido, pedidoDTO), HttpStatus.OK);
	}
	
	@PutMapping("/finalizar")
	public ResponseEntity<String> finalizarPedido(@RequestParam Integer idPedido) throws PedidoFinalizadoException {
		pedidoService.finalizarPedido(idPedido);
		return new ResponseEntity<>("Pedido finalizado", HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deletePedidoById(@RequestParam Integer id) {
		pedidoService.deletePedidoById(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
