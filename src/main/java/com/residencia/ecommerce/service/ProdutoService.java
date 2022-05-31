package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> findAllProduto(){
		return produtoRepository.findAll();
	}
	
	public Produto findByIdProduto(Integer idProduto) {
		return produtoRepository.findById(idProduto).isPresent() ? produtoRepository.findById(idProduto).get() : null;
	}
	
	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto updateProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void deleteByIdProduto(Integer idProduto) {
		produtoRepository.deleteById(idProduto);
	}

	private Produto toEntity(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		
		//produto.setCategoria(null);
		produto.setDataCadastroProduto(produtoDTO.getDataCadastroProduto());
		produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
		produto.setNomeImagemProduto(produtoDTO.getNomeImagemProduto());
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setQtdEstoqueProduto(produtoDTO.getQtdEstoqueProduto());
		produto.setValorUnitario(produtoDTO.getValorUnitario());
		
		return produto;
	}
	
	private ProdutoDTO toEntity(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		
		//produto.setCategoria(null);
		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setDataCadastroProduto(produto.getDataCadastroProduto());
		produtoDTO.setDescricaoProduto(produto.getDescricaoProduto());
		produtoDTO.setNomeImagemProduto(produto.getNomeImagemProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setQtdEstoqueProduto(produto.getQtdEstoqueProduto());
		produtoDTO.setValorUnitario(produto.getValorUnitario());
		
		return produtoDTO;
	}
}
