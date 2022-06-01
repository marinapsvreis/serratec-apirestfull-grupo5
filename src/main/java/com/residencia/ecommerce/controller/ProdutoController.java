package com.residencia.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.exception.DescricaoProdutoException;
import com.residencia.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity <List<ProdutoDTO>> findAllProduto(){
		return new ResponseEntity<>(produtoService.findAllProduto(), HttpStatus.OK);
	}
	
	@GetMapping("/{idProduto}")
	public ResponseEntity<ProdutoDTO> findProdutoById(@PathVariable Integer idProduto){
		return new ResponseEntity<>(produtoService.findByIdProduto(idProduto), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoDTO> saveProdutoDTO(@RequestBody ProdutoDTO produtoDTO) throws DescricaoProdutoException{
		produtoService.saveProdutoDTO(produtoDTO);
		return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/com-foto", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Produto> saveProdutoComFoto(@RequestPart("produto") String produtoDTO, @RequestPart("file") MultipartFile file){
		return new ResponseEntity<>(produtoService.saveProdutoComFoto(produtoDTO , file), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ProdutoDTO> updateProduto(@RequestBody ProdutoDTO produtoDTO){
		return new ResponseEntity<>(produtoService.updateProdutoDTO(produtoDTO), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteProduto(Integer idProduto){
		produtoService.deleteByIdProduto(idProduto);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	
}
