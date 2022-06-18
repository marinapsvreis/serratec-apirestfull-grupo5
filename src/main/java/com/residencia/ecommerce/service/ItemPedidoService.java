package com.residencia.ecommerce.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.exception.ItemPedidoException;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.exception.PedidoException;
import com.residencia.ecommerce.exception.PedidoFinalizadoException;
import com.residencia.ecommerce.exception.ProdutoException;
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
		ItemPedidoDTO itemPedidoDTO = itemPedidoRepository.findById(idItemPedido).isPresent()
				? toDTO(itemPedidoRepository.findById(idItemPedido).get())
				: null;
		if (itemPedidoDTO == null) {
			throw new NoSuchElementFoundException("Não existe item_pedido como id " + idItemPedido);
		} else {
			return itemPedidoDTO;
		}

	}

	public List<ItemPedidoDTO> findByItemPedidoByPedidoId(Integer idPedido) throws Exception {
		Pedido pedido = pedidoService.toEntity(pedidoService.findPedidoById(idPedido));

		List<ItemPedido> itemPedidoList = itemPedidoRepository.findByPedido(pedido);

		List<ItemPedidoDTO> itemPedidoDTOList = new ArrayList<>();
		for(ItemPedido itemPedido: itemPedidoList) {
			itemPedidoDTOList.add(toDTO(itemPedido));
		}
		return itemPedidoDTOList;
	}
	
	public ItemPedidoDTO saveItemPedido(ItemPedidoDTO itemPedidoDTO) throws Exception {
		if(pedidoService.findPedidoById(itemPedidoDTO.getIdPedido()).getStatus() == true) {
			throw new PedidoFinalizadoException("O pedido referente a este item já foi finalizado e não pode ser alterado.");
		}
		itemPedidoDTO.setValorBrutoItemPedido(itemPedidoDTO.getPrecoVendaItemPedido().multiply(BigDecimal.valueOf(itemPedidoDTO.getQuantidadeItemPedido())));
		itemPedidoDTO.setValorLiquidoItemPedido((itemPedidoDTO.getValorBrutoItemPedido()).multiply(BigDecimal.valueOf(1).subtract((itemPedidoDTO.getPercentualDescontoItemPedido()).divide(BigDecimal.valueOf(100)))));
		ProdutoDTO produtoDTO = produtoService.findByIdProduto(itemPedidoDTO.getIdProduto());
		
		if(produtoDTO.getQtdEstoqueProduto() < itemPedidoDTO.getQuantidadeItemPedido()) {
			throw new ProdutoException("A loja não tem item suficientes para essa requisição.");
		}else {
			produtoDTO.setQtdEstoqueProduto(produtoDTO.getQtdEstoqueProduto()-itemPedidoDTO.getQuantidadeItemPedido());
			
			atualizarValoresTotaisPedido(itemPedidoDTO);
			
			return toDTO(itemPedidoRepository.save(toEntity(itemPedidoDTO)));
		}
		
		
	}
	
	public ItemPedidoDTO updateItemPedido(Integer idItemPedido, ItemPedidoDTO itemPedidoDTO) throws Exception {
		findByIdItemPedido(idItemPedido);
		itemPedidoDTO.setIdItemPedido(idItemPedido); 
		if(pedidoService.findPedidoById(itemPedidoDTO.getIdPedido()).getStatus() == true) {
			throw new PedidoFinalizadoException("O pedido referente a este item já foi finalizado e não pode ser alterado.");
		}else {
			itemPedidoDTO.setValorBrutoItemPedido(itemPedidoDTO.getPrecoVendaItemPedido().multiply(BigDecimal.valueOf(itemPedidoDTO.getQuantidadeItemPedido())));
			itemPedidoDTO.setValorLiquidoItemPedido((itemPedidoDTO.getValorBrutoItemPedido()).multiply(BigDecimal.valueOf(1).subtract((itemPedidoDTO.getPercentualDescontoItemPedido()).divide(BigDecimal.valueOf(100)))));
			
			ItemPedidoDTO itemPedidoDTOAnterior = findByIdItemPedido(idItemPedido);
			
			if(itemPedidoDTOAnterior.getQuantidadeItemPedido() != itemPedidoDTO.getQuantidadeItemPedido()) {
				throw new ItemPedidoException("Você não pode alterar a quantidade de items do produto, delete e refaza a requisicao do item_pedido");
			}
			
			return toDTO(itemPedidoRepository.save(toEntity(itemPedidoDTO)));
		}
		
	}
	
	public void deleteByIdItemPedido(Integer idItemPedido) {
			ItemPedidoDTO itemPedidoDTO = itemPedidoRepository.findById(idItemPedido).isPresent() ?
			        toDTO(itemPedidoRepository.findById(idItemPedido).get())
			        : null;
        if (itemPedidoDTO == null) {
            throw new NoSuchElementFoundException("Não existe item_pedido com o id " + idItemPedido);
        } else {
        	ProdutoDTO produtoDTO = produtoService.findByIdProduto(itemPedidoDTO.getIdProduto());
        	produtoDTO.setQtdEstoqueProduto(produtoDTO.getQtdEstoqueProduto() + itemPedidoDTO.getQuantidadeItemPedido());
            itemPedidoRepository.deleteById(idItemPedido);
        }
		
	}
	
	public void atualizarValoresTotaisPedido(ItemPedidoDTO itemPedidoDTO) throws Exception {
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
	
	private ItemPedido toEntity(ItemPedidoDTO itemPedidoDTO) throws Exception {
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
