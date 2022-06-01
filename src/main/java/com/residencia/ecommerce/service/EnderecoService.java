package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.residencia.ecommerce.dto.ConsultaCepDTO;
import com.residencia.ecommerce.dto.EnderecoDTO;
import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepository;

	public List<EnderecoDTO> findAllEndereco() {
		List<Endereco> listEnderecoEntity = enderecoRepository.findAll();
		List<EnderecoDTO> listEnderecoDTO = new ArrayList<>();
		
		for(Endereco endereco : listEnderecoEntity) {
			listEnderecoDTO.add(toDTO(endereco));
		}
		
		return listEnderecoDTO;
	}

	public EnderecoDTO findByIdEndereco(Integer idEndereco) {
		return enderecoRepository.findById(idEndereco).isPresent() ?
				toDTO(enderecoRepository.findById(idEndereco).get())
				: null;
	}

	public EnderecoDTO saveEndereco(EnderecoDTO enderecoDTO) {
		return toDTO(enderecoRepository.save(toEntity(enderecoDTO)));
	}

	public EnderecoDTO updateEnderecoDTO(EnderecoDTO enderecoDTO) {
		return toDTO(enderecoRepository.save(toEntity(enderecoDTO)));
	}

	public void deleteByIdEndereco(Integer idEndereco) {
		enderecoRepository.deleteById(idEndereco);
	}
	
	//api externa
	public ConsultaCepDTO consultarCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://viacep.com.br/ws/{cep}/json/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("cep", cep);

        ConsultaCepDTO cadastroCepDTO = restTemplate.getForObject(uri,ConsultaCepDTO.class, params);

        return cadastroCepDTO;
    }
	
	//DTO
	public EnderecoDTO saveEnderecoDTO(String cep, Integer numero) {
		String cepFormatado = cep.replaceAll("[.-]", "");
		ConsultaCepDTO cepDTO = consultarCep(cepFormatado);
		Endereco endereco = cepDTOtoEndereco(cepDTO);
		endereco.setNumero(numero);
		
		return toDTO(enderecoRepository.save(endereco));
	}
	
	public Endereco toEntity(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();

		endereco.setIdEndereco(enderecoDTO.getIdEndereco());
		endereco.setBairro(enderecoDTO.getBairro());
		endereco.setCep(enderecoDTO.getCep());
		endereco.setCidade(enderecoDTO.getCidade());
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setNumero(enderecoDTO.getNumero());
		endereco.setRua(enderecoDTO.getRua());

		return endereco;
	}

	public EnderecoDTO toDTO(Endereco endereco) {
		EnderecoDTO enderecoDTO = new EnderecoDTO();

		enderecoDTO.setIdEndereco(endereco.getIdEndereco());
		enderecoDTO.setBairro(endereco.getBairro());
		enderecoDTO.setCep(endereco.getCep());
		enderecoDTO.setCidade(endereco.getCidade());
		enderecoDTO.setComplemento(endereco.getComplemento());
		enderecoDTO.setNumero(endereco.getNumero());
		enderecoDTO.setRua(endereco.getRua());

		return enderecoDTO;
	}
	
	public Endereco cepDTOtoEndereco(ConsultaCepDTO cepDTO) {
		Endereco endereco = new Endereco();
		endereco.setBairro(cepDTO.getBairro());
		endereco.setCep(cepDTO.getCep());
		endereco.setCidade(cepDTO.getLocalidade());
		endereco.setComplemento(cepDTO.getComplemento());
		endereco.setRua(cepDTO.getLogradouro());
		endereco.setUf(cepDTO.getUf());
		
		return endereco;
	}

}
