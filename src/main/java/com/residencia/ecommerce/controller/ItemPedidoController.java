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

import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.service.ItemPedidoService;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@GetMapping
	public ResponseEntity <List<ItemPedido>> findAllItemPedido(){
		List<ItemPedido> itemPedidoList = itemPedidoService.findAllItemPedido();
		return new ResponseEntity<>(itemPedidoList, HttpStatus.OK);
	}
	
	@GetMapping("/{idItemPedido}")
	public ResponseEntity<ItemPedido> findItemPedidoById(@PathVariable Integer idItemPedido){
		ItemPedido itemPedido = itemPedidoService.findByIdItemPedido(idItemPedido);
		return new ResponseEntity<>(itemPedido, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ItemPedido> saveItemPedido(@RequestBody ItemPedido itemPedido){
		itemPedidoService.updateItemPedido(itemPedido);
		return new ResponseEntity<>(itemPedidoService.saveItemPedido(itemPedido), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<ItemPedido> updateItemPedido(@PathVariable Integer idItemPedido, @RequestBody ItemPedido itemPedido){
		ItemPedido itemPedidoAtualizado = itemPedidoService.updateItemPedido(itemPedido);
		return new ResponseEntity<>(itemPedidoAtualizado, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteItemPedido(Integer idItemPedido){
		itemPedidoService.deleteByIdItemPedido(idItemPedido);
		return new ResponseEntity<>("", HttpStatus.OK);
	}	
	
}
