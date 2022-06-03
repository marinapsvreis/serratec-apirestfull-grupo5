package com.residencia.ecommerce.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.exception.ClienteException;
import com.residencia.ecommerce.exception.EnderecoException;
import com.residencia.ecommerce.exception.PedidoFinalizadoException;
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
	
	public ItemPedidoDTO saveItemPedido(ItemPedidoDTO itemPedidoDTO) throws PedidoFinalizadoException, EnderecoException, ClienteException {
		itemPedidoDTO.setValorBrutoItemPedido(itemPedidoDTO.getPrecoVendaItemPedido().multiply(BigDecimal.valueOf(itemPedidoDTO.getQuantidadeItemPedido())));
		itemPedidoDTO.setValorLiquidoItemPedido((itemPedidoDTO.getValorBrutoItemPedido()).multiply(BigDecimal.valueOf(1).subtract((itemPedidoDTO.getPercentualDescontoItemPedido()).divide(BigDecimal.valueOf(100)))));
		
		atualizarValoresTotaisPedido(itemPedidoDTO);
		
		return toDTO(itemPedidoRepository.save(toEntity(itemPedidoDTO)));
	}
	
	public ItemPedidoDTO updateItemPedido(Integer idItemPedido, ItemPedidoDTO itemPedidoDTO) throws EnderecoException, ClienteException {
		itemPedidoDTO.setIdItemPedido(idItemPedido);
		return toDTO(itemPedidoRepository.save(toEntity(itemPedidoDTO)));
	}
	
	public void deleteByIdItemPedido(Integer idItemPedido) {
		itemPedidoRepository.deleteById(idItemPedido);
	}
	
	public void atualizarValoresTotaisPedido(ItemPedidoDTO itemPedidoDTO) throws PedidoFinalizadoException, EnderecoException, ClienteException {
		Pedido pedido = pedidoService.toEntity(pedidoService.findPedidoById(itemPedidoDTO.getIdPedido()));
		BigDecimal valorTotalBrutoAtual = pedido.getValorTotalPedidoBruto();
		BigDecimal valorTotalDescontoAtual = pedido.getValorTotalDescontoPedido();
		BigDecimal valorTotalLiquidoAtual = pedido.getValorTotalPedidoLiquido();
		
		if(valorTotalBrutoAtual != null) {			
			pedido.setValorTotalPedidoBruto((valorTotalBrutoAtual).add(itemPedidoDTO.getValorBrutoItemPedido()));
			pedido.setValorTotalPedidoLiquido((valorTotalLiquidoAtual).add(itemPedidoDTO.getValorLiquidoItemPedido()));
			pedido.setValorTotalDescontoPedido((valorTotalDescontoAtual).add((pedido.getValorTotalPedidoBruto()).subtract(pedido.getValorTotalPedidoLiquido())));
		}else {
			pedido.setValorTotalPedidoBruto(itemPedidoDTO.getValorBrutoItemPedido());
			pedido.setValorTotalPedidoLiquido(itemPedidoDTO.getValorLiquidoItemPedido());
			pedido.setValorTotalDescontoPedido((pedido.getValorTotalPedidoBruto()).subtract(pedido.getValorTotalPedidoLiquido()));
		}		
		
		pedidoService.updatePedido(itemPedidoDTO.getIdPedido(), pedidoService.toDTO(pedido));
	}
	
	private ItemPedido toEntity(ItemPedidoDTO itemPedidoDTO) throws EnderecoException, ClienteException {
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
