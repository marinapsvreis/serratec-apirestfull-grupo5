package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ClienteService clienteService;

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

	public PedidoDTO savePedido(PedidoDTO pedidoDTO) {
		pedidoDTO.setDataPedido(new Date());
		return toDTO(pedidoRepository.save(toEntity(pedidoDTO)));
	}

	public PedidoDTO updatePedido(Integer idPedido, PedidoDTO pedidoDTO) {
		pedidoDTO.setIdPedido(idPedido);
		return toDTO(pedidoRepository.save(toEntity(pedidoDTO)));
	}

	public void deletePedidoById(Integer id) {
		pedidoRepository.deleteById(id);
	}

	public Pedido toEntity(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		
		pedido.setCliente(clienteService.toEntity(clienteService.findClienteById(pedidoDTO.getIdCliente())));
		pedido.setIdPedido(pedidoDTO.getIdPedido());
		pedido.setDataEnvio(pedidoDTO.getDataEnvio());
		pedido.setDataEntrega(pedidoDTO.getDataEnvio());
		pedido.setDataPedido(pedidoDTO.getDataPedido());
		pedido.setStatus(pedidoDTO.getStatus());	
		
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
		
		return pedidoDTO;
	}
}
