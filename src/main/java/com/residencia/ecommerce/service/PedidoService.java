package com.residencia.ecommerce.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.exception.ClienteException;
import com.residencia.ecommerce.exception.EnderecoException;
import com.residencia.ecommerce.exception.PedidoFinalizadoException;
import com.residencia.ecommerce.repository.EnderecoRepository;
import com.residencia.ecommerce.repository.ItemPedidoRepository;
import com.residencia.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	MailService emailService;

	public List<PedidoDTO> findAllPedido() {
		List<Pedido> listPedidoEntity = pedidoRepository.findAll();
		List<PedidoDTO> listPedidoDTO = new ArrayList<>();
		
		for(Pedido pedido : listPedidoEntity) {
			listPedidoDTO.add(toDTO(pedido));
		}
		
		return listPedidoDTO;
	}

	public PedidoDTO findPedidoById(Integer idPedido) {
		return pedidoRepository.findById(idPedido).isPresent() ?
				toDTO(pedidoRepository.findById(idPedido).get()) 
				: null;
	}

	public PedidoDTO savePedido(PedidoDTO pedidoDTO) throws EnderecoException, ClienteException {
		pedidoDTO.setDataPedido(new Date());
		pedidoDTO.setValorTotalPedidoBruto(BigDecimal.valueOf(0));
		pedidoDTO.setValorTotalPedidoLiquido(BigDecimal.valueOf(0));
		pedidoDTO.setValorTotalDescontoPedido(BigDecimal.valueOf(0));
		pedidoDTO.setStatus(false);
		return toDTO(pedidoRepository.save(toEntity(pedidoDTO)));
	}

	public PedidoDTO updatePedido(Integer idPedido, PedidoDTO pedidoDTO) throws PedidoFinalizadoException, EnderecoException, ClienteException {
		if(pedidoDTO.getStatus() == true) {
			throw new PedidoFinalizadoException("Pedido já finalizado não pode ser alterado");
		}else {
			pedidoDTO.setIdPedido(idPedido);
			return toDTO(pedidoRepository.save(toEntity(pedidoDTO)));
		}		
	}

	public void deletePedidoById(Integer id) {
		pedidoRepository.deleteById(id);
	}
	
	public void finalizarPedido(Integer idPedido) throws PedidoFinalizadoException, EnderecoException, ClienteException {
		Pedido pedido = toEntity(findPedidoById(idPedido));
		if(pedido.getStatus() == true) {
			throw new PedidoFinalizadoException("Pedido já foi finalizado");
		}else {
			pedido.setStatus(true);
			pedido = pedidoRepository.save(pedido);
			
			String htmlEmail = gerarHTMLEmail(pedido);					
			
			try {
	            emailService.enviarEmailHTML("marinapsvreis@gmail.com", "Teste Email Pedido Finalizado", htmlEmail);
	        } catch (MessagingException e) {
	            System.out.println("Erro ao enviar e-mail HTML.");
	            e.printStackTrace();
	        }
		}		
	}

	public Pedido toEntity(PedidoDTO pedidoDTO) throws EnderecoException, ClienteException {
		Pedido pedido = new Pedido();
		
		pedido.setCliente(clienteService.toEntity(clienteService.findClienteById(pedidoDTO.getIdCliente())));
		pedido.setIdPedido(pedidoDTO.getIdPedido());
		pedido.setDataEnvio(pedidoDTO.getDataEnvio());
		pedido.setDataEntrega(pedidoDTO.getDataEnvio());
		pedido.setDataPedido(pedidoDTO.getDataPedido());
		pedido.setStatus(pedidoDTO.getStatus());
		pedido.setValorTotalPedidoBruto(pedidoDTO.getValorTotalPedidoBruto());
		pedido.setValorTotalDescontoPedido(pedidoDTO.getValorTotalDescontoPedido());
		pedido.setValorTotalPedidoLiquido(pedidoDTO.getValorTotalPedidoLiquido());
		
		return pedido;
	}
	
	public PedidoDTO toDTO(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();
		
		pedidoDTO.setIdPedido(pedido.getIdPedido());
		pedidoDTO.setDataEnvio(pedido.getDataEnvio());
		pedidoDTO.setDataEntrega(pedido.getDataEnvio());
		pedidoDTO.setDataPedido(pedido.getDataPedido());
		pedidoDTO.setStatus(pedido.getStatus());
		pedidoDTO.setIdCliente(pedido.getCliente().getIdCliente());
		pedidoDTO.setValorTotalPedidoBruto(pedido.getValorTotalPedidoBruto());
		pedidoDTO.setValorTotalDescontoPedido(pedido.getValorTotalDescontoPedido());
		pedidoDTO.setValorTotalPedidoLiquido(pedido.getValorTotalPedidoLiquido());
		
		return pedidoDTO;
	}
	
	public String gerarHTMLEmail(Pedido pedido) throws EnderecoException {
		List<ItemPedido> listProdutosPedido = new ArrayList<>();
		
		List<ItemPedido> todosItemPedido = itemPedidoRepository.findAll();
		
		for(ItemPedido item : todosItemPedido) {
			if(item.getPedido().getIdPedido() == pedido.getIdPedido()) {
				listProdutosPedido.add(item);
			}				
		}
		
		String htmlEmail = "<h1>Pedido ID:" + pedido.getIdPedido() + " Finalizado</h1>";
				htmlEmail += "<br>";
				htmlEmail += "<p>Data do Pedido: " + toDTO(pedido).getDataPedido() + "</p>";
				htmlEmail += "<p>Data do Envio: " + toDTO(pedido).getDataEnvio() +"</p>";
				htmlEmail += "<p>Data da Entrega(previsão): " + toDTO(pedido).getDataEntrega() +"</p>";
				htmlEmail += "<br>";
				htmlEmail += "<h2>Produtos:</h2>";
				htmlEmail += "<br>";
				
				for(ItemPedido itemPedido : listProdutosPedido) {
					htmlEmail += "<p>"+ itemPedido.toString() +"</p>";
				}
				
				
				htmlEmail += "<br>";
				htmlEmail += "<h2>Valores Totais:</h2>";
				htmlEmail += "<p>Valor Bruto do Pedido: " + pedido.getValorTotalPedidoBruto() + "</p>";
				htmlEmail += "<p>Valor de Desconto do Pedido: " + pedido.getValorTotalDescontoPedido() + "</p>";
				htmlEmail += "<p><strong>Valor Liquido do Pedido: " + pedido.getValorTotalPedidoLiquido() + "</strong></p>";
				htmlEmail += "<br>";
				htmlEmail += "<h2>Dados do cliente:</h2>";
				htmlEmail += "<br>";
				htmlEmail += "<p>Cliente: " + pedido.getCliente().getNomeCompleto() +"</p>";
				htmlEmail += "<p>CPF: " + pedido.getCliente().getCpf() +"</p>";
				htmlEmail += "<p>E-mail: " + pedido.getCliente().getEmail() +"</p>";
				htmlEmail += "<p>Telefone: " + pedido.getCliente().getTelefone() +"</p>";
				htmlEmail += "<br>";
				htmlEmail += "<h2>Endereço:</h2>";
				htmlEmail += "<p>CEP: " + enderecoService.findByIdEndereco(clienteService.findClienteById(toDTO(pedido).getIdCliente()).getIdEndereco()).getCep() + "</p>";
				htmlEmail += "<p>Rua: " + enderecoService.findByIdEndereco(clienteService.findClienteById(toDTO(pedido).getIdCliente()).getIdEndereco()).getRua() + "</p>";
				htmlEmail += "<p>Numero: " + enderecoService.findByIdEndereco(clienteService.findClienteById(toDTO(pedido).getIdCliente()).getIdEndereco()).getNumero().toString() + "</p>";
				htmlEmail += "<p>Bairro: " + enderecoService.findByIdEndereco(clienteService.findClienteById(toDTO(pedido).getIdCliente()).getIdEndereco()).getBairro() + "</p>";
				htmlEmail += "<p>Cidade: " + enderecoService.findByIdEndereco(clienteService.findClienteById(toDTO(pedido).getIdCliente()).getIdEndereco()).getCidade() + "</p>";
				htmlEmail += "<p>UF: " + enderecoService.findByIdEndereco(clienteService.findClienteById(toDTO(pedido).getIdCliente()).getIdEndereco()).getUf() + "</p>";
				htmlEmail += "<p>Complemento: " + enderecoService.findByIdEndereco(clienteService.findClienteById(toDTO(pedido).getIdCliente()).getIdEndereco()).getComplemento() + "</p>";
				
				return htmlEmail;
	}
}
