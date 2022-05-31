package com.residencia.ecommerce.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private Arquivo2Service arquivo2Service;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Value("${pasta.arquivos.imagem}")
    private Path path;
	
	public List<Produto> findAllProduto(){
		return produtoRepository.findAll();
	}
	
	public Produto findByIdProduto(Integer idProduto) {
		return produtoRepository.findById(idProduto).isPresent() ? produtoRepository.findById(idProduto).get() : null;
	}
	
	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto saveProdutoDTO(ProdutoDTO produtoDTO) {
		return produtoRepository.save(toEntity(produtoDTO));
	}
	
	public Produto updateProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void deleteByIdProduto(Integer idProduto) {
		produtoRepository.deleteById(idProduto);
	}

	private Produto toEntity(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		
		produto.setCategoria(categoriaService.findCategoriaById(produtoDTO.getIdCategoria()));
		produto.setDataCadastroProduto(produtoDTO.getDataCadastroProduto());
		produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
		produto.setNomeImagemProduto(produtoDTO.getNomeImagemProduto());
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setQtdEstoqueProduto(produtoDTO.getQtdEstoqueProduto());
		produto.setValorUnitario(produtoDTO.getValorUnitario());
		
		return produto;
	}
	
	private ProdutoDTO toDTO(Produto produto) {
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

	public Produto saveProdutoComFoto(String produtoString, MultipartFile file) {
		Produto novoProduto = new Produto();
        
        try {
            ObjectMapper objMapper = new ObjectMapper();
            novoProduto = objMapper.readValue(produtoString, Produto.class);
        }catch(IOException e) {
            System.out.println("Ocorreu um erro na conversão");
        }
        
        Produto produtoSalvo = produtoRepository.save(novoProduto);
        
        String fileName = "produto." + produtoSalvo.getIdProduto() + ".image.png";
        
        arquivo2Service.criarArquivo(fileName, file);        
        
        try {
            produtoSalvo.setNomeImagemProduto(path.resolve(fileName).toRealPath().toString());
        }catch(IOException e) {
            e.printStackTrace();
        }

        return produtoRepository.save(produtoSalvo);
	}

	
}
