package com.residencia.ecommerce.service;

import java.util.ArrayList;
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
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PedidoService pedidoService;
	
	public List<ItemPedidoDTO> findAllItemPedido(){
		List<ItemPedido> listItemPedidoEntity = itemPedidoRepository.findAll();
		List<ItemPedidoDTO> listItemPedidoDTO = new ArrayList<>();
		
		for(ItemPedido itemPedido : listItemPedidoEntity) {
			listItemPedidoDTO.add(toDTO(itemPedido));
		}
		
		return listItemPedidoDTO;
	}
	
	public ItemPedidoDTO findByIdItemPedido(Integer idItemPedido) {
		return itemPedidoRepository.findById(idItemPedido).isPresent() ?
				toDTO(itemPedidoRepository.findById(idItemPedido).get()) 
				: null;
	}
	
	public ItemPedidoDTO saveItemPedido(ItemPedidoDTO itemPedidoDTO) {
		return toDTO(itemPedidoRepository.save(toEntity(itemPedidoDTO)));
	}
	
	public ItemPedidoDTO updateItemPedido(Integer idItemPedido, ItemPedidoDTO itemPedidoDTO) {
		itemPedidoDTO.setIdItemPedido(idItemPedido);
		return toDTO(itemPedidoRepository.save(toEntity(itemPedidoDTO)));
	}
	
	public void deleteByIdItemPedido(Integer idItemPedido) {
		itemPedidoRepository.deleteById(idItemPedido);
	}
	
	private ItemPedido toEntity(ItemPedidoDTO itemPedidoDTO) {
		ItemPedido itemPedido = new ItemPedido();
		
		itemPedido.setIdItemPedido(itemPedidoDTO.getIdItemPedido());
		itemPedido.setProduto(produtoService.toEntity(produtoService.findByIdProduto(itemPedidoDTO.getIdProduto())));
		itemPedido.setPedido(pedidoService.toEntity(pedidoService.findPedidoById(itemPedidoDTO.getIdPedido())));
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
		itemPedidoDTO.setIdProduto(itemPedido.getProduto().getIdProduto());
		itemPedidoDTO.setIdPedido(itemPedido.getPedido().getIdPedido());
		itemPedidoDTO.setPercentualDescontoItemPedido(itemPedido.getPercentualDescontoItemPedido());
		itemPedidoDTO.setPrecoVendaItemPedido(itemPedido.getPrecoVendaItemPedido());
		itemPedidoDTO.setQuantidadeItemPedido(itemPedido.getQuantidadeItemPedido());
		itemPedidoDTO.setValorBrutoItemPedido(itemPedido.getValorBrutoItemPedido());
		itemPedidoDTO.setValorLiquidoItemPedido(itemPedido.getValorLiquidoItemPedido());
		
		return itemPedidoDTO;
	}
}
