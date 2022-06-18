package com.residencia.ecommerce.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.residencia.ecommerce.exception.ClienteException;
import com.residencia.ecommerce.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.exception.DescricaoProdutoException;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.exception.ProdutoException;
import com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private Arquivo2Service arquivo2Service;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Value("${pasta.arquivos.imagem}")
	private Path path;

	public List<ProdutoDTO> findAllProduto() {
		List<Produto> produtoListEntity = produtoRepository.findAll();
		List<ProdutoDTO> produtoListDTO = new ArrayList<>();

		for (Produto produto : produtoListEntity) {
			produtoListDTO.add(toDTO(produto));
		}

		return produtoListDTO;
	}

	public ProdutoDTO findByIdProduto(Integer idProduto) {
		ProdutoDTO produtoDTO = produtoRepository.findById(idProduto).isPresent()
				? toDTO(produtoRepository.findById(idProduto).get())
				: null;
		if (produtoDTO == null) {
			throw new NoSuchElementFoundException("Não existe pedido como id " + idProduto);
		} else {
			return produtoDTO;
		}

	}

	public Produto saveProdutoDTO(ProdutoDTO produtoDTO) throws Exception {
		produtoDTO.setDataCadastroProduto(new Date());

		List<Produto> listaProdutos = produtoRepository.findByDescricaoProduto(produtoDTO.getDescricaoProduto());

		if (!listaProdutos.isEmpty()) {
			throw new DescricaoProdutoException("Essa descrição ja foi utilizada em outro produto");
		}
		return produtoRepository.save(toEntity(produtoDTO));
	}

	public ProdutoDTO updateProdutoDTO(Integer idProduto, ProdutoDTO produtoDTO) throws Exception {
		produtoDTO.setIdProduto(idProduto);
		produtoDTO.setDataCadastroProduto(new Date());
		findByIdProduto(idProduto);
		List<Produto> listaProdutos = produtoRepository.findByDescricaoProduto(produtoDTO.getDescricaoProduto());

		List<Integer> listaIdProdutosCadastrados = new ArrayList<>();

		for (Produto produto : listaProdutos) {
			if (produto.getIdProduto() != produtoDTO.getIdProduto()) {
				listaIdProdutosCadastrados.add(idProduto);
			}
		}

		if (!listaIdProdutosCadastrados.isEmpty()) {
			throw new DescricaoProdutoException(
					"Essa descrição ja foi utilizada em outro produto de id:: " + listaIdProdutosCadastrados.get(0));
		}

		produtoDTO.setIdProduto(idProduto);
		return toDTO(produtoRepository.save(toEntity(produtoDTO)));
	}

	public void deleteByIdProduto(Integer idProduto) throws Exception {
		if (!itemPedidoRepository.findByProduto(toEntity(findByIdProduto(idProduto))).isEmpty()) {
			throw new ProdutoException("Existem items_pedidos cadastrados para esse Produto, portanto ele não pode ser deletado");
		}
		findByIdProduto(idProduto);
		produtoRepository.deleteById(idProduto);
	}

	public Produto toEntity(ProdutoDTO produtoDTO) throws Exception {
		Produto produto = new Produto();

		produto.setCategoria(
				categoriaService.toEntity(categoriaService.findCategoriaByIdDTO(produtoDTO.getIdCategoria())));
		produto.setIdProduto(produtoDTO.getIdProduto());
		produto.setDataCadastroProduto(produtoDTO.getDataCadastroProduto());
		produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
		produto.setNomeImagemProduto(produtoDTO.getNomeImagemProduto());
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setQtdEstoqueProduto(produtoDTO.getQtdEstoqueProduto());
		produto.setValorUnitario(produtoDTO.getValorUnitario());

		return produto;
	}

	public ProdutoDTO toDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();

		produtoDTO.setIdCategoria(produto.getCategoria().getIdCategoria());
		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setDataCadastroProduto(produto.getDataCadastroProduto());
		produtoDTO.setDescricaoProduto(produto.getDescricaoProduto());
		produtoDTO.setNomeImagemProduto(produto.getNomeImagemProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setQtdEstoqueProduto(produto.getQtdEstoqueProduto());
		produtoDTO.setValorUnitario(produto.getValorUnitario());

		return produtoDTO;
	}

	public Produto saveProdutoComFoto(String produtoString, String file) throws Exception {
		ProdutoDTO novoProduto = new ProdutoDTO();

		try {
			ObjectMapper objMapper = new ObjectMapper();
			novoProduto = objMapper.readValue(produtoString, ProdutoDTO.class);
		} catch (IOException e) {
			System.out.println("Ocorreu um erro na conversão");
		}

		List<Produto> listaProdutos = produtoRepository.findByDescricaoProduto(novoProduto.getDescricaoProduto());

		if (!(listaProdutos.isEmpty())) {
			throw new DescricaoProdutoException("Essa descrição ja foi utilizada em outro produto");
		}
		
		novoProduto.setNomeImagemProduto(file);

		novoProduto.setDataCadastroProduto(new Date());

		return produtoRepository.save(toEntity(novoProduto));
	}

}
