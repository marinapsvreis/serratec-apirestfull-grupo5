package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ItemPedidoDTO;
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
	
	private ItemPedido toEntity(ItemPedidoDTO itemPedidoDTO) {
		ItemPedido itemPedido = new ItemPedido();
		
		itemPedido.setPercentualDescontoItemPedido(itemPedidoDTO.getPercentualDescontoItemPedido());
		itemPedido.setPrecoVendaItemPedido(itemPedidoDTO.getPrecoVendaItemPedido());
		itemPedido.setQuantidadeItemPedido(itemPedidoDTO.getQuantidadeItemPedido());
		itemPedido.setValorBrutoItemPedido(itemPedidoDTO.getValorBrutoItemPedido());
		itemPedido.setValorLiquidoItemPedido(itemPedidoDTO.getValorLiquidoItemPedido());
		
		return itemPedido;
	}
	
	private ItemPedidoDTO toDTO(ItemPedido itemPedido) {
		ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
		
		itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
		itemPedidoDTO.setPercentualDescontoItemPedido(itemPedido.getPercentualDescontoItemPedido());
		itemPedidoDTO.setPrecoVendaItemPedido(itemPedido.getPrecoVendaItemPedido());
		itemPedidoDTO.setQuantidadeItemPedido(itemPedido.getQuantidadeItemPedido());
		itemPedidoDTO.setValorBrutoItemPedido(itemPedido.getValorBrutoItemPedido());
		itemPedidoDTO.setValorLiquidoItemPedido(itemPedido.getValorLiquidoItemPedido());
		
		return itemPedidoDTO;
	}
}
