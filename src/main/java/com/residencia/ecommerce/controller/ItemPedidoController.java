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

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.service.ItemPedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/itemPedido")
@Tag(name = "Item Pedido", description = "endpoints")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@GetMapping
	@Operation(summary = "Listar todos os itens pedidos")
	public ResponseEntity <List<ItemPedidoDTO>> findAllItemPedido(){
		return new ResponseEntity<>(itemPedidoService.findAllItemPedido(), HttpStatus.OK);
	}
	
	@GetMapping("/{idItemPedido}")
	@Operation(summary = "Listar item pedido via ID Path")
	public ResponseEntity<ItemPedidoDTO> findItemPedidoById(@PathVariable Integer idItemPedido){
		return new ResponseEntity<>(itemPedidoService.findByIdItemPedido(idItemPedido), HttpStatus.OK);
	}
	
	@PostMapping
	@Operation(summary = "Cadastrar item pedido")
	public ResponseEntity<ItemPedidoDTO> saveItemPedido(@Valid @RequestBody ItemPedidoDTO itemPedidoDTO) throws Exception {
		return new ResponseEntity<>(itemPedidoService.saveItemPedido(itemPedidoDTO), HttpStatus.OK);
	}
	
	@PutMapping
	@Operation(summary = "Atualizar item pedido passando todos os dados")
	public ResponseEntity<ItemPedidoDTO> updateItemPedido(@RequestParam Integer idItemPedido, @Valid  @RequestBody ItemPedidoDTO itemPedidoDTO) throws Exception {
		return new ResponseEntity<>(itemPedidoService.updateItemPedido(idItemPedido, itemPedidoDTO), HttpStatus.OK);
	}
	
	@DeleteMapping
	@Operation(summary = "Deletar item pedido via ID")
	public ResponseEntity<String> deleteItemPedido(Integer idItemPedido){
		itemPedidoService.deleteByIdItemPedido(idItemPedido);
		return new ResponseEntity<>("", HttpStatus.OK);
	}	
	
}
