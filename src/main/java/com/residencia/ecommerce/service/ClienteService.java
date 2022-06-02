package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.exception.CpfClienteException;
import com.residencia.ecommerce.exception.EmailClienteException;
import com.residencia.ecommerce.repository.ClienteRepository;
import com.residencia.ecommerce.repository.EnderecoRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired 
	EnderecoService enderecoService;

	public List<ClienteDTO> findAllCliente() {
		List<Cliente> listClienteEntity = clienteRepository.findAll();
		List<ClienteDTO> listClienteDTO = new ArrayList<>();
		
		for(Cliente cliente : listClienteEntity) {
			listClienteDTO.add(toDTO(cliente));
		}
		
		return listClienteDTO;
	}

	public ClienteDTO findClienteById(Integer idCliente) {
		return clienteRepository.findById(idCliente).isPresent() ? 
				toDTO(clienteRepository.findById(idCliente).get()) : null;
	}

	public ClienteDTO saveCliente(ClienteDTO clienteDTO) throws Exception {
		List<Cliente> clienteCpf = clienteRepository.findByCpf(clienteDTO.getCpf());
		List<Cliente> clienteEmail = clienteRepository.findByEmail(clienteDTO.getEmail());

		if (!clienteCpf.isEmpty()) {
			throw new CpfClienteException("CPF ja foi registrado");
		} else if (!clienteEmail.isEmpty()) {
			throw new EmailClienteException("Email ja foi registrado");
		}
		else {
			Cliente cliente = toEntity(clienteDTO);
			cliente = clienteRepository.save(cliente);
			atualizarEnderecoCliente(cliente.getIdCliente(), clienteDTO.getIdEndereco());
			return toDTO(cliente);
		}
	}

	public ClienteDTO updateCliente(Integer idCliente, ClienteDTO clienteDTO) {
		clienteDTO.setIdEndereco(idCliente);
		Cliente cliente = toEntity(clienteDTO);
		atualizarEnderecoCliente(cliente.getIdCliente(), clienteDTO.getIdEndereco());
		return toDTO(clienteRepository.save(cliente));
	}

	public void deleteClienteById(Integer idCliente) {
		clienteRepository.deleteById(idCliente);
	}

	public Boolean atualizarEnderecoCliente(Integer idCliente, Integer idEndereco) {

		if (clienteRepository.findById(idCliente).isPresent() == true) {
			Cliente cliente = clienteRepository.findById(idCliente).get();
			Endereco endereco = enderecoRepository.findById(idEndereco).get();
			cliente.setEndereco(endereco);
			clienteRepository.save(cliente);
			return true;
		} else {
			enderecoRepository.deleteById(idEndereco);
			return false;
		}
	}
	
	public Cliente toEntity(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();

		cliente.setIdCliente(clienteDTO.getIdCliente());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setDataNascimento(clienteDTO.getDataNascimento());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setNomeCompleto(clienteDTO.getNomeCompleto());
		cliente.setTelefone(clienteDTO.getTelefone());
		if(clienteDTO.getIdEndereco() != null) {
			cliente.setEndereco(enderecoService.toEntity(enderecoService.findByIdEndereco(clienteDTO.getIdEndereco())));
		}

		return cliente;
	}

	public ClienteDTO toDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();

		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setCpf(cliente.getCpf());
		clienteDTO.setDataNascimento(cliente.getDataNascimento());
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setNomeCompleto(cliente.getNomeCompleto());
		clienteDTO.setTelefone(cliente.getTelefone());
		if(cliente.getEndereco() != null) {
			clienteDTO.setIdEndereco(cliente.getEndereco().getIdEndereco());
		}
		
		return clienteDTO;
	}
}
