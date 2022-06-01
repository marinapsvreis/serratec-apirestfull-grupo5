package com.residencia.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAllCategoria() {	
		return new ResponseEntity<>(categoriaService.findAllCategoria(), HttpStatus.OK);
	}

	@GetMapping("/{idCategoria}")
	public ResponseEntity<CategoriaDTO> findCategoriaById(@PathVariable Integer idCategoria) {
		return new ResponseEntity<>(categoriaService.findCategoriaByIdDTO(idCategoria), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CategoriaDTO> saveCategoriaDTO(@RequestBody CategoriaDTO categoriaDTO) {
		categoriaService.saveCategoriaDTO(categoriaDTO);
		return new ResponseEntity<>(categoriaDTO, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<CategoriaDTO> updateCategoriaDTO(@RequestBody CategoriaDTO categoriaDTO) {
		return new ResponseEntity<>(categoriaService.updateCategoria(categoriaDTO), HttpStatus.OK);
	}


	@DeleteMapping("/{idCategoria}")
	public ResponseEntity<String> deleteCategoriaById(@PathVariable Integer idCategoria) {
		categoriaService.deleteCategoriaById(idCategoria);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
