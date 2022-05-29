package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public List<Endereco> findAllEndereco(){
		return enderecoRepository.findAll();	
	}
	
	public Endereco findByIdEndereco(Integer idEndereco){
		return enderecoRepository.findById(idEndereco).isPresent() ?
				enderecoRepository.findById(idEndereco).get() : null;
	}
	
	public Endereco saveEndereco(Endereco endereco){
		return enderecoRepository.save(endereco);
	}
	
	public Endereco updateEndereco(Endereco endereco){
		return enderecoRepository.save(endereco);
	}
	
	public void deleteByIdEndereco(Integer idEndereco){
		enderecoRepository.deleteById(idEndereco);
	}

}
