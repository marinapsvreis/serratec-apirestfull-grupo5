package com.residencia.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@PutMapping
	@Operation(summary = "Atualizar categoria passando todos os dados")
	public ResponseEntity<CategoriaDTO> updateCategoriaDTO(@RequestParam Integer idCategoria, @RequestBody CategoriaDTO categoriaDTO) throws Exception {
		return new ResponseEntity<>(categoriaService.updateCategoria(idCategoria, categoriaDTO), HttpStatus.OK);
	}


	@DeleteMapping("/{idCategoria}")
	@Operation(summary = "Deletar categoria via ID")
	public ResponseEntity<String> deleteCategoriaById(@PathVariable Integer idCategoria) throws Exception {
		categoriaService.deleteCategoriaById(idCategoria);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
