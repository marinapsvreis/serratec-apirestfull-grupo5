package com.residencia.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import com.residencia.ecommerce.entity.Categoria;
import com.residencia.ecommerce.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@RequestMapping("/categoria")
@Tag(name = "Categoria", description = "endpoints")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	@Operation(summary = "Listar todas as categorias")
	public ResponseEntity<List<CategoriaDTO>> findAllCategoria() {	
		return new ResponseEntity<>(categoriaService.findAllCategoria(), HttpStatus.OK);
	}

	@GetMapping("/{idCategoria}")
	@Operation(summary = "Listar categoria via ID Path")
	public ResponseEntity<CategoriaDTO> findCategoriaById(@Valid @PathVariable Integer idCategoria) throws Exception {
		return new ResponseEntity<>(categoriaService.findCategoriaByIdDTO(idCategoria), HttpStatus.OK);
	}

	@PostMapping
	@Operation(summary = "Cadastrar categoria")
	public ResponseEntity<CategoriaDTO> saveCategoriaDTO(@Valid @RequestBody CategoriaDTO categoriaDTO) throws Exception {
		categoriaService.saveCategoriaDTO(categoriaDTO);
		return new ResponseEntity<>(categoriaDTO, HttpStatus.CREATED);
	}

	@PostMapping(value = "/com-foto", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	@Operation(summary = "Cadastrar Categoria com foto")
	public ResponseEntity<Categoria> saveCategoriaComFoto(@Valid @RequestPart("categoria") String categoriaDTO, @RequestPart("file") String file) throws Exception {
		return new ResponseEntity<>(categoriaService.saveCategoriaComFoto(categoriaDTO , file), HttpStatus.CREATED);
	}


	@PutMapping
	@Operation(summary = "Atualizar categoria passando todos os dados")
	public ResponseEntity<CategoriaDTO> updateCategoriaDTO(@RequestParam Integer idCategoria, @RequestBody CategoriaDTO categoriaDTO) throws Exception {
		return new ResponseEntity<>(categoriaService.updateCategoria(idCategoria, categoriaDTO), HttpStatus.OK);
	}


	@DeleteMapping
	@Operation(summary = "Deletar categoria via ID")
	public ResponseEntity<String> deleteCategoriaById(@RequestParam Integer idCategoria) throws Exception {
		categoriaService.deleteCategoriaById(idCategoria);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
