package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

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
		List<CategoriaDTO> categoriaDTOList = new ArrayList();
		
		for(Categoria categoria : categoriaEntityList) {
			categoriaDTOList.add(toDTO(categoria));
		}	
		
		return categoriaDTOList;
	}

	public CategoriaDTO findCategoriaById(Integer idCategoria) {
		return categoriaRepository.findById(idCategoria).isPresent() ? 
				toDTO(categoriaRepository.findById(idCategoria).get()) 
				: null;
	}

	public Categoria saveCategoriaDTO(CategoriaDTO categoriaDTO) {
		return categoriaRepository.save(toEntity(categoriaDTO));
	}

	public CategoriaDTO updateCategoria(Categoria categoria) {
		return toDTO(categoriaRepository.save(categoria));
	}

	public void deleteCategoriaById(Integer id) {
		categoriaRepository.deleteById(id);
	}
	
	private Categoria toEntity(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		
		categoria.setDescricaoCategoria(categoriaDTO.getDescricaoCategoria());
		categoria.setNomeCategoria(categoriaDTO.getNomeCategoria());
		
		return categoria;
	}
	
	private CategoriaDTO toDTO(Categoria categoria) {
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		
		categoriaDTO.setIdCategoria(categoria.getIdCategoria());
		categoriaDTO.setDescricaoCategoria(categoria.getDescricaoCategoria());
		categoriaDTO.setNomeCategoria(categoria.getNomeCategoria());
		
		return categoriaDTO;
	}
} 
