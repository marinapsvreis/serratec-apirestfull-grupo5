package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.residencia.ecommerce.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoService enderecoService;

	public List<ClienteDTO> findAllCliente() {
		List<Cliente> listClienteEntity = clienteRepository.findAll();
		List<ClienteDTO> listClienteDTO = new ArrayList<>();

		for (Cliente cliente : listClienteEntity) {
			listClienteDTO.add(toDTO(cliente));
		}

		return listClienteDTO;
	}

	public ClienteDTO findClienteById(Integer idCliente) {
		ClienteDTO clienteDTO =  clienteRepository.findById(idCliente).isPresent() ? toDTO(clienteRepository.findById(idCliente).get())
				: null;
		if (clienteDTO == null) {
			throw new NoSuchElementFoundException("Não existe cliente como id " + idCliente);
		} else {
			return clienteDTO;
		}

	}

	public ClienteDTO saveCliente(ClienteDTO clienteDTO) throws CpfClienteException, EmailClienteException, ClienteException, EnderecoException  {
		clienteDTO.setCpf(clienteDTO.getCpf().replaceAll("[.-]", ""));
		clienteDTO.setTelefone(clienteDTO.getTelefone().replaceAll("[()-]", ""));

		List<Cliente> clienteCpf = clienteRepository.findByCpf(clienteDTO.getCpf());
		List<Cliente> clienteEmail = clienteRepository.findByEmail(clienteDTO.getEmail());

		if (!clienteCpf.isEmpty()) {
			throw new ClienteException("CPF ja foi registrado");
		} else if (!clienteEmail.isEmpty()) {
			throw new ClienteException("Email ja foi registrado");
		} else if (!validate(clienteDTO.getEmail())) {
			throw new ClienteException("Email inválido");
		} else if (!clienteDTO.getNomeCompleto().matches("[a-zA-Z][a-zA-Z ]*")) {
			throw new ClienteException("Nome deve conter somente letras");
		} else if (!clienteDTO.getTelefone().matches("[0-9]+")) {
			throw new ClienteException("Numero de telefone deve corresponder ao formato: (11)11111-1111 ou somente numeros");
		} else {
			Cliente cliente = toEntity(clienteDTO);
			cliente = clienteRepository.save(cliente);
			if(cliente.getEndereco() != null) {
				atualizarEnderecoCliente(cliente.getIdCliente(), clienteDTO.getIdEndereco());
			}			
			return toDTO(cliente);
		}
	}

	public ClienteDTO updateCliente(Integer idCliente, ClienteDTO clienteDTO)
			throws EnderecoException, ClienteException {
		clienteDTO.setIdCliente(idCliente);
		clienteDTO.setCpf(clienteDTO.getCpf().replaceAll("[.-]", ""));
		clienteDTO.setTelefone(clienteDTO.getTelefone().replaceAll("[()-]", ""));

		Optional<Cliente> clienteAntigo = clienteRepository.findById(idCliente);

		if (!(clienteAntigo.get().getEmail() == clienteDTO.getEmail() || clienteAntigo.get().getCpf() == clienteDTO.getCpf())) {
			throw new ClienteException("Dados inseridos referentes a outro ususario");
		} else if (!validate(clienteDTO.getEmail())) {
			throw new ClienteException("Email inválido");
		} else if (!clienteDTO.getNomeCompleto().matches("[a-zA-Z][a-zA-Z ]*")) {
			throw new ClienteException("Nome deve conter somente letras");
		} else if (!clienteDTO.getTelefone().matches("[0-9]+")) {
			throw new ClienteException("Numero de telefone deve corresponder ao formato: (11)11111-1111 ou somente numeros");
		} else {
			Cliente cliente = toEntity(clienteDTO);
			if(cliente.getEndereco() != null) {
				atualizarEnderecoCliente(cliente.getIdCliente(), clienteDTO.getIdEndereco());
			}			
			return toDTO(clienteRepository.save(cliente));
		}
	}

	public void deleteClienteById(Integer idCliente) {
		ClienteDTO clienteDTO =  clienteRepository.findById(idCliente).isPresent() ? toDTO(clienteRepository.findById(idCliente).get())
				: null;
		if (clienteDTO == null) {
			throw new NoSuchElementFoundException("Não existe cliente como id " + idCliente);
		} else {
			clienteRepository.deleteById(idCliente);
		}

	}

	public Boolean atualizarEnderecoCliente(Integer idCliente, Integer idEndereco) throws EnderecoException {

		if (clienteRepository.findById(idCliente).isPresent() == true) {
			Cliente cliente = clienteRepository.findById(idCliente).get();
			Endereco endereco = enderecoService.toEntity(enderecoService.findByIdEndereco(idEndereco));
			cliente.setEndereco(endereco);
			clienteRepository.save(cliente);
			return true;
		} else {
			enderecoService.deleteByIdEndereco(idEndereco);
			return false;
		}
	}

	public Cliente toEntity(ClienteDTO clienteDTO) throws EnderecoException, ClienteException {
		Cliente cliente = new Cliente();
		
		if(clienteDTO.getIdCliente() != null) {
			cliente.setIdCliente(clienteDTO.getIdCliente());
		}		
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setDataNascimento(clienteDTO.getDataNascimento());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setNomeCompleto(clienteDTO.getNomeCompleto());
		cliente.setTelefone(clienteDTO.getTelefone());
		if (clienteDTO.getIdEndereco() != null) {
			cliente.setEndereco(enderecoService.toEntity(enderecoService.findByIdEndereco(clienteDTO.getIdEndereco())));
		}

		return cliente;
	}

	public ClienteDTO toDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();

		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setCpf(putMaskCPF(cliente.getCpf()));
		clienteDTO.setDataNascimento(cliente.getDataNascimento());
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setNomeCompleto(cliente.getNomeCompleto());
		clienteDTO.setTelefone(putMaskOnPhone(cliente.getTelefone()));
		if (cliente.getEndereco() != null) {
			clienteDTO.setIdEndereco(cliente.getEndereco().getIdEndereco());
		}

		return clienteDTO;
	}
	
	public String putMaskCPF(String cpf) {
		String cpfWithMask = "";
		
		cpfWithMask += cpf.charAt(0);
		cpfWithMask += cpf.charAt(1);
		cpfWithMask += cpf.charAt(2);
		cpfWithMask += ".";
		cpfWithMask += cpf.charAt(3);
		cpfWithMask += cpf.charAt(4);
		cpfWithMask += cpf.charAt(5);
		cpfWithMask += ".";
		cpfWithMask += cpf.charAt(6);
		cpfWithMask += cpf.charAt(7);
		cpfWithMask += cpf.charAt(8);
		cpfWithMask += "-";
		cpfWithMask += cpf.charAt(9);
		cpfWithMask += cpf.charAt(10);
		
		return cpfWithMask;
	}
	
	public String putMaskOnPhone(String telefone) {
		String phoneWithMask = "";
		
		phoneWithMask += "(";
		phoneWithMask += telefone.charAt(0);
		phoneWithMask += telefone.charAt(1);
		phoneWithMask += ")";
		phoneWithMask += telefone.charAt(2);
		phoneWithMask += telefone.charAt(3);
		phoneWithMask += telefone.charAt(4);
		phoneWithMask += telefone.charAt(5);
		phoneWithMask += telefone.charAt(6);
		phoneWithMask += "-";
		phoneWithMask += telefone.charAt(7);
		phoneWithMask += telefone.charAt(8);
		phoneWithMask += telefone.charAt(9);
		phoneWithMask += telefone.charAt(10);
		
		return phoneWithMask;
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
}
