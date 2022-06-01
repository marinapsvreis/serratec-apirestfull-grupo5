package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.repository.ClienteRepository;
import com.residencia.ecommerce.repository.EnderecoRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;

	public List<Cliente> findAllCliente() {
		return clienteRepository.findAll();
	}

	public Cliente findByIdCliente(Integer idCliente) {
		return clienteRepository.findById(idCliente).isPresent() ? clienteRepository.findById(idCliente).get() : null;
	}

	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente updateCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void deleteByIdCliente(Integer idCliente) {
		clienteRepository.deleteById(idCliente);
	}

	private Cliente toEntity(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();

		cliente.setCpf(clienteDTO.getCpf());
		cliente.setDataNascimento(clienteDTO.getDataNascimento());
		cliente.setEmail(clienteDTO.getEmail());

		//cliente.setEndereco(clienteDTO.ge());
		cliente.setNomeCompleto(clienteDTO.getNomeCompleto());
		cliente.setTelefone(clienteDTO.getTelefone());
		
		return cliente;
	}

	private ClienteDTO toDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();

		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setCpf(cliente.getCpf());
		clienteDTO.setDataNascimento(cliente.getDataNascimento());
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setIdEndereco(cliente.getEndereco().getIdEndereco());
		clienteDTO.setNomeCompleto(cliente.getNomeCompleto());
		clienteDTO.setTelefone(cliente.getTelefone());
		
		return clienteDTO;
	}
	
	//endereço
	public void atualizarEnderecoCliente(Integer idCliente, Integer idEndereco)  {
		if(clienteRepository.findById(idCliente).isPresent()) {
			Cliente cliente = clienteRepository.findById(idCliente).get();
			Endereco endereco = enderecoRepository.findById(idEndereco).get();
			cliente.setEndereco(endereco);
			clienteRepository.save(cliente);
			System.out.println("Sucesso!");
		}else {
			System.out.println("Não foi possível encontrar o cliente com o id " + idCliente);
		}
	}
}
