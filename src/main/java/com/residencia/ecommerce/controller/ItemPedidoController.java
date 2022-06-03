package com.residencia.ecommerce.controller;

import java.util.List;

import com.residencia.ecommerce.exception.CategoriaException;
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

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.exception.ClienteException;
import com.residencia.ecommerce.exception.EnderecoException;
import com.residencia.ecommerce.exception.PedidoFinalizadoException;
import com.residencia.ecommerce.service.ItemPedidoService;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@GetMapping
	public ResponseEntity <List<ItemPedidoDTO>> findAllItemPedido(){
		return new ResponseEntity<>(itemPedidoService.findAllItemPedido(), HttpStatus.OK);
	}
	
	@GetMapping("/{idItemPedido}")
	public ResponseEntity<ItemPedidoDTO> findItemPedidoById(@PathVariable Integer idItemPedido){
		return new ResponseEntity<>(itemPedidoService.findByIdItemPedido(idItemPedido), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ItemPedidoDTO> saveItemPedido(@RequestBody ItemPedidoDTO itemPedidoDTO) throws PedidoFinalizadoException, EnderecoException, ClienteException, CategoriaException {
		return new ResponseEntity<>(itemPedidoService.saveItemPedido(itemPedidoDTO), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<ItemPedidoDTO> updateItemPedido(@RequestParam Integer idItemPedido, @RequestBody ItemPedidoDTO itemPedidoDTO) throws EnderecoException, ClienteException, CategoriaException {
		return new ResponseEntity<>(itemPedidoService.updateItemPedido(idItemPedido, itemPedidoDTO), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteItemPedido(Integer idItemPedido){
		itemPedidoService.deleteByIdItemPedido(idItemPedido);
		return new ResponseEntity<>("", HttpStatus.OK);
	}	
	
}
