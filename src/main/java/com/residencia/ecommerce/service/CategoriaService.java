package com.residencia.ecommerce.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.ecommerce.exception.CategoriaException;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.entity.Categoria;
import com.residencia.ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository categoriaRepository;

	public List<CategoriaDTO> findAllCategoria() {		
		List<Categoria> categoriaEntityList = categoriaRepository.findAll();
		List<CategoriaDTO> categoriaDTOList = new ArrayList<>();
		
		for(Categoria categoria : categoriaEntityList) {
			categoriaDTOList.add(toDTO(categoria));
		}	
		
		return categoriaDTOList;
	}
	public CategoriaDTO findCategoriaByIdDTO(Integer idCategoria) throws Exception {
		CategoriaDTO categoriaDTO = categoriaRepository.findById(idCategoria).isPresent() ?
				toDTO(categoriaRepository.findById(idCategoria).get())
				: null;
		if (categoriaDTO == null) {
			throw new NoSuchElementFoundException("Não existe categoria com o id " + idCategoria);
		}

		return categoriaDTO;
	}

	public Categoria saveCategoriaDTO(CategoriaDTO categoriaDTO) throws Exception {
		return categoriaRepository.save(toEntity(categoriaDTO));
	}

	public Categoria saveCategoriaComFoto(String categoriaString, String file) throws Exception {
		CategoriaDTO novaCategoria = new CategoriaDTO();

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			novaCategoria = objectMapper.readValue(categoriaString, CategoriaDTO.class);
		} catch (IOException e) {
			System.out.println("Ocorreu um erro na conversão");
		}

		novaCategoria.setImagemCategoria(file);
		return categoriaRepository.save(toEntity(novaCategoria));
	}

	public CategoriaDTO updateCategoria(Integer idCategoria, CategoriaDTO categoriaDTO) throws Exception {
		findCategoriaByIdDTO(idCategoria);
		categoriaDTO.setIdCategoria(idCategoria);
		return toDTO(categoriaRepository.save(toEntity(categoriaDTO)));

	}

	public void deleteCategoriaById(Integer idCategoria) throws CategoriaException {
		CategoriaDTO categoriaDTO = categoriaRepository.findById(idCategoria).isPresent() ?
				toDTO(categoriaRepository.findById(idCategoria).get())
				: null;
		if (categoriaDTO == null) {
			throw new NoSuchElementFoundException("Não existe categoria com o id " + idCategoria);
		} else {
			categoriaRepository.deleteById(idCategoria);
		}

	}
	
	public Categoria toEntity(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		
		categoria.setIdCategoria(categoriaDTO.getIdCategoria());
		categoria.setDescricaoCategoria(categoriaDTO.getDescricaoCategoria());
		categoria.setNomeCategoria(categoriaDTO.getNomeCategoria());
		categoria.setImagemCategoria(categoriaDTO.getImagemCategoria());
		
		return categoria;
	}
	
	public CategoriaDTO toDTO(Categoria categoria) {
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		
		categoriaDTO.setIdCategoria(categoria.getIdCategoria());
		categoriaDTO.setDescricaoCategoria(categoria.getDescricaoCategoria());
		categoriaDTO.setNomeCategoria(categoria.getNomeCategoria());
		categoriaDTO.setImagemCategoria(categoria.getImagemCategoria());

		return categoriaDTO;
	}
} 
