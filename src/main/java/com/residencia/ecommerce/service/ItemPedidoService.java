package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public List<ItemPedido> findAllItemPedido(){
		return itemPedidoRepository.findAll();
	}
	
	public ItemPedido findByIdItemPedido(Integer idItemPedido) {
		return itemPedidoRepository.findById(idItemPedido).isPresent() ? itemPedidoRepository.findById(idItemPedido).get() : null;
	}
	
	public ItemPedido saveItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	public ItemPedido updateItemPedido(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	public void deleteByIdItemPedido(Integer idItemPedido) {
		itemPedidoRepository.deleteById(idItemPedido);
	}
}
