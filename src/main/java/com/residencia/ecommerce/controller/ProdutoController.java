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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.exception.DescricaoProdutoException;
import com.residencia.ecommerce.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "endpoints")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	@Operation(summary = "Listar todos os produtos")
	public ResponseEntity <List<ProdutoDTO>> findAllProduto(){
		return new ResponseEntity<>(produtoService.findAllProduto(), HttpStatus.OK);
	}
	
	@GetMapping("/{idProduto}")
	@Operation(summary = "Listar produto via ID Path")
	public ResponseEntity<ProdutoDTO> findProdutoById(@PathVariable Integer idProduto){
		return new ResponseEntity<>(produtoService.findByIdProduto(idProduto), HttpStatus.OK);
	}
	
	@PostMapping
	@Operation(summary = "Cadastrar produto")
	public ResponseEntity<ProdutoDTO> saveProdutoDTO(@RequestBody ProdutoDTO produtoDTO) throws DescricaoProdutoException{
		produtoService.saveProdutoDTO(produtoDTO);
		return new ResponseEntity<>(produtoDTO, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/com-foto", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	@Operation(summary = "Cadastrar produto com foto")
	public ResponseEntity<Produto> saveProdutoComFoto(@RequestPart("produto") String produtoDTO, @RequestPart("file") MultipartFile file){
		return new ResponseEntity<>(produtoService.saveProdutoComFoto(produtoDTO , file), HttpStatus.CREATED);
	}
	
	@PutMapping
	@Operation(summary = "Atualizar produto passando todos os dados")
	public ResponseEntity<ProdutoDTO> updateProduto(@RequestParam Integer idProduto, @RequestBody ProdutoDTO produtoDTO){
		return new ResponseEntity<>(produtoService.updateProdutoDTO(idProduto, produtoDTO), HttpStatus.OK);
	}
	
	@DeleteMapping
	@Operation(summary = "Deletar produto via ID")
	public ResponseEntity<String> deleteProduto(Integer idProduto){
		produtoService.deleteByIdProduto(idProduto);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	
	
}
