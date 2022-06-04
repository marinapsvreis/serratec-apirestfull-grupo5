package com.residencia.ecommerce.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.exception.DescricaoProdutoException;
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
	
	public List<ProdutoDTO> findAllProduto(){
		List<Produto> produtoListEntity = produtoRepository.findAll();
		List<ProdutoDTO> produtoListDTO = new ArrayList<>();
		
		for(Produto produto : produtoListEntity) {
			produtoListDTO.add(toDTO(produto));
		}
		
		return produtoListDTO;
	}
	
	public ProdutoDTO findByIdProduto(Integer idProduto) {
		ProdutoDTO produtoDTO = produtoRepository.findById(idProduto).isPresent() ?
				toDTO(produtoRepository.findById(idProduto).get()) 
				: null;
		if(produtoDTO == null) {
			throw new NoSuchElementException("Não existe produto com o id " + idProduto);
		}else {
			return produtoDTO;
		}
	}
	
	public Produto saveProdutoDTO(ProdutoDTO produtoDTO) throws Exception {
		produtoDTO.setDataCadastroProduto(new Date());
		if(!produtoRepository.findByDescricaoProduto(produtoDTO.getDescricaoProduto()).isEmpty()) {
			throw new DescricaoProdutoException("Essa descrição ja foi utilizada em outro produto");
		}
			return produtoRepository.save(toEntity(produtoDTO));		
	}
	
	public ProdutoDTO updateProdutoDTO(Integer idProduto, ProdutoDTO produtoDTO) throws Exception {
		findByIdProduto(idProduto);
		if(!produtoRepository.findByDescricaoProduto(produtoDTO.getDescricaoProduto()).isEmpty()) {
			throw new DescricaoProdutoException("Essa descrição ja foi utilizada em outro produto");
		}
		produtoDTO.setIdProduto(idProduto);
		return toDTO(produtoRepository.save(toEntity(produtoDTO)));
	}
	
	public void deleteByIdProduto(Integer idProduto) {
		findByIdProduto(idProduto);
		produtoRepository.deleteById(idProduto);
	
	}

	public Produto toEntity(ProdutoDTO produtoDTO) throws Exception {
		Produto produto = new Produto();
		
		
		produto.setCategoria(categoriaService.toEntity(categoriaService.findCategoriaByIdDTO(produtoDTO.getIdCategoria())));
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

	public Produto saveProdutoComFoto(String produtoString, MultipartFile file) throws Exception {
		ProdutoDTO novoProduto = new ProdutoDTO();
        
        try {
            ObjectMapper objMapper = new ObjectMapper();
            novoProduto = objMapper.readValue(produtoString, ProdutoDTO.class);
        }catch(IOException e) {
            System.out.println("Ocorreu um erro na conversão");
        }
        
        Produto produtoSalvo = produtoRepository.save(toEntity(novoProduto));
        
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
