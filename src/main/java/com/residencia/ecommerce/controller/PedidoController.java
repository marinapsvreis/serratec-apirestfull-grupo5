package com.residencia.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> findAllPedido() {
		List<Pedido> pedidoList = pedidoService.findAllPedido();
		return new ResponseEntity<>(pedidoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findPedidoById(@PathVariable Integer id) {
		Pedido pedido = pedidoService.findPedidoById(id);
		return new ResponseEntity<>(pedido, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Pedido> savePedido(@RequestBody Pedido pedido) {
		return new ResponseEntity<>(pedidoService.savePedido(pedido), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido) {
		Pedido pedidoAtualizado = pedidoService.updatePedido(pedido);
		return new ResponseEntity<>(pedidoAtualizado, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePedidoById(@PathVariable Integer id) {
		pedidoService.deletePedidoById(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
