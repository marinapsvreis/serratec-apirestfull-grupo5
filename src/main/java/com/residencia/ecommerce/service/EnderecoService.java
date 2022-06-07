package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.residencia.ecommerce.dto.ConsultaCepDTO;
import com.residencia.ecommerce.dto.EnderecoDTO;
import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.exception.EnderecoException;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.repository.ClienteRepository;
import com.residencia.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	public List<EnderecoDTO> findAllEndereco() {
		List<Endereco> listEnderecoEntity = enderecoRepository.findAll();
		List<EnderecoDTO> listEnderecoDTO = new ArrayList<>();

		for (Endereco endereco : listEnderecoEntity) {
			listEnderecoDTO.add(toDTO(endereco));
		}

		return listEnderecoDTO;
	}

	public EnderecoDTO findByIdEndereco(Integer idEndereco) throws Exception {
		if (!enderecoRepository.findById(idEndereco).isPresent()) {
			throw new NoSuchElementFoundException("Não existe endereço com o id " + idEndereco);
		} else {
			return enderecoRepository.findById(idEndereco).isPresent()
					? toDTO(enderecoRepository.findById(idEndereco).get())
					: null;
		}

	}

	public EnderecoDTO saveEnderecoDTO(String cep, Integer numero, Integer idCliente) throws Exception {
		String cepFormatado = "";
		cepFormatado = cep.replaceAll("[.-]", "");

		if (!cepFormatado.matches("[0-9]+")) {
			throw new EnderecoException("O cep deve conter apenas números e 1 único hífen.");
		}
		if (cepFormatado.length() != 8) {
			throw new EnderecoException(
					"Cep inválido. Digite o cep com hífen (9 caracteres) ou somente números (8 caracteres): Ex 25660-004/25660004");
		}

		if (!clienteRepository.findById(idCliente).isPresent()) {
			throw new NoSuchElementFoundException("Não foi possível encontrar o cliente com o id " + idCliente);
		}
		if (!numero.toString().matches("[0-9]+")) {
			throw new EnderecoException(
					"No campo número deverá conter apenas números, qualquer informação adicional colocar no complemento.");
		}

		try{
			ConsultaCepDTO cepDTO = consultarCep(cepFormatado);
			Endereco endereco = cepDTOtoEndereco(cepDTO);
			endereco.setNumero(numero);		

			Endereco endereco2 = endereco;
			endereco2.setCep(cepFormatado);
			clienteRepository.findById(idCliente).get().setEndereco(endereco2);

			return toDTO(enderecoRepository.save(endereco2));
		} catch (DataIntegrityViolationException e) {
			throw new EnderecoException("CEP invalido, não foram encontrados dados nesse cep");
		}
	}

	public EnderecoDTO updateEnderecoDTO(Integer idEndereco, EnderecoDTO enderecoDTO) throws Exception {
		if (!enderecoRepository.findById(idEndereco).isPresent()) {
			throw new NoSuchElementFoundException("Não existe endereço com o id " + idEndereco);
		}

		enderecoDTO.setIdEndereco(idEndereco);
		String cepFormatado = "";

		if (enderecoDTO.getCep() != null) {
			cepFormatado = enderecoDTO.getCep().replaceAll("[.-]", "");
		}
		if (!cepFormatado.matches("[0-9]+")) {
			throw new EnderecoException("O cep deve conter apenas números e 1 único hífen.");
		}
		if (cepFormatado.length() != 8) {
			throw new EnderecoException(
					"Cep inválido. Digite o cep com hífen (9 caracteres) ou somente números (8 caracteres): Ex 25660-004/25660004");
		}

		if (!enderecoDTO.getNumero().toString().matches("[0-9]+")) {
			throw new EnderecoException(
					"No campo número deverá conter apenas números, qualquer informação adicional colocar no complemento.");
		}

		return toDTO(enderecoRepository.save(toEntity(enderecoDTO)));
	}

	public void deleteByIdEndereco(Integer idEndereco) throws Exception {
		List<Integer> listaIdEnderecosCadastrados = new ArrayList<>();
		for (Cliente cliente : clienteRepository.findAll()) {
			if (cliente.getEndereco() != null && cliente.getEndereco().getIdEndereco() == idEndereco) {
				listaIdEnderecosCadastrados.add(cliente.getEndereco().getIdEndereco());
			}
		}
		if (!enderecoRepository.findById(idEndereco).isPresent()) {
			throw new NoSuchElementFoundException("Não existe endereço com o id " + idEndereco);
		} else if (!listaIdEnderecosCadastrados.isEmpty()) {
			throw new EnderecoException("Existem clientes ainda cadastrados com esse endereço!");
		}else {
			enderecoRepository.deleteById(idEndereco);
		}
	}

	public ConsultaCepDTO consultarCep(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://viacep.com.br/ws/{cep}/json/";

		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);

		ConsultaCepDTO cadastroCepDTO = restTemplate.getForObject(uri, ConsultaCepDTO.class, params);

		return cadastroCepDTO;
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
		endereco.setUf(enderecoDTO.getUf());

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
		enderecoDTO.setUf(endereco.getUf());

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
