package com.residencia.ecommerce.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.exception.ClienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.exception.PedidoException;
import com.residencia.ecommerce.exception.PedidoFinalizadoException;
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

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public List<PedidoDTO> findAllPedido() {
        List<Pedido> listPedidoEntity = pedidoRepository.findAll();
        List<PedidoDTO> listPedidoDTO = new ArrayList<>();

        for (Pedido pedido : listPedidoEntity) {
            listPedidoDTO.add(toDTO(pedido));
        }

        return listPedidoDTO;
    }

    public PedidoDTO findPedidoById(Integer idPedido) {
        PedidoDTO pedidoDTO = pedidoRepository.findById(idPedido).isPresent()
                ? toDTO(pedidoRepository.findById(idPedido).get())
                : null;
        if (pedidoDTO == null) {
            throw new NoSuchElementFoundException("Não existe pedido como id " + idPedido);
        } else {
            return pedidoDTO;
        }

    }

    public List<PedidoDTO> findPedidoByCliente(Integer idCliente) throws Exception {
        ClienteDTO clienteDTO = clienteService.findClienteById(idCliente);

        List<Pedido> pedidos = pedidoRepository.findByCliente(clienteService.toEntity(clienteDTO));
        List<PedidoDTO> pedidoDTO = new ArrayList<>();
        for(Pedido pedido: pedidos){
            pedidoDTO.add(toDTO(pedido));
        }
        return pedidoDTO;

    }

    public PedidoDTO savePedido(PedidoDTO pedidoDTO) throws Exception {
        pedidoDTO.setDataPedido(new Date());
        pedidoDTO.setValorTotalPedidoBruto(BigDecimal.valueOf(0));
        pedidoDTO.setValorTotalPedidoLiquido(BigDecimal.valueOf(0));
        pedidoDTO.setValorTotalDescontoPedido(BigDecimal.valueOf(0));
        pedidoDTO.setStatus(false);

        if (clienteService.findClienteById(pedidoDTO.getIdCliente()).getIdEndereco() == null) {
            throw new PedidoException("O cliente só pode criar um pedido depois de cadastrar seu endereço");
        }

        return toDTO(pedidoRepository.save(toEntity(pedidoDTO)));
    }

    public PedidoDTO updatePedido(Integer idPedido, PedidoDTO pedidoDTO) throws Exception {
        findPedidoById(idPedido);
        Pedido pedido = pedidoRepository.findById(idPedido).get();
        pedidoDTO.setStatus(pedido.getStatus());
        pedidoDTO.setDataPedido(pedido.getDataPedido());
        if (pedidoDTO.getStatus() == true) {
            throw new PedidoFinalizadoException("Pedido já finalizado não pode ser alterado");
        } else {
            pedidoDTO.setIdPedido(idPedido);
            return toDTO(pedidoRepository.save(toEntity(pedidoDTO)));
        }
    }

    public void deletePedidoById(Integer idPedido) throws Exception {
        PedidoDTO pedidoDTO = pedidoRepository.findById(idPedido).isPresent() ?
                toDTO(pedidoRepository.findById(idPedido).get())
                : null;
        if (pedidoDTO == null) {
            throw new NoSuchElementFoundException("Não existe pedido com o id " + idPedido);
        } else if (!itemPedidoRepository.findByPedido(toEntity(findPedidoById(idPedido))).isEmpty()) {
            throw new PedidoException("Existem items_pedidos cadastrados para esse pedido, portanto ele não pode ser deletado");
        } else {
            pedidoRepository.deleteById(idPedido);
        }

    }

    public void finalizarPedido(Integer idPedido) throws Exception {
        Pedido pedido = toEntity(findPedidoById(idPedido));
        if (pedido.getStatus() == true) {
            throw new PedidoFinalizadoException("Pedido já foi finalizado");
        } else if ((pedido.getValorTotalPedidoBruto()).doubleValue() == 0.0) {
            throw new PedidoFinalizadoException("Você não pode finalizar esse pedido porque nenhum produto foi adicionado!");
        } else {
            pedido.setStatus(true);

            //adicionando um dia a partir desse momento para data do envio
            Date today = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.DATE, 1);
            Date dataEnvio = c.getTime();

            //adicionando 7 dias a partir desse momento para data prevista de entrega
            c.setTime(today);
            c.add(Calendar.DATE, 7);
            Date dataEntrega = c.getTime();

            pedido.setDataEnvio(dataEnvio);
            pedido.setDataEntrega(dataEntrega);
            pedido = pedidoRepository.save(pedido);

            String htmlEmail = gerarHTMLEmail(pedido);

            try {
                emailService.enviarEmailHTML("marinapsvreis@gmail.com", "Teste Email Pedido Finalizado", htmlEmail);
            } catch (MessagingException e) {
                System.out.println("Erro ao enviar e-mail HTML. Porém o pedido já foi processado!");
                e.printStackTrace();
            }
        }
    }

    public Pedido toEntity(PedidoDTO pedidoDTO) throws Exception {
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

    public String gerarHTMLEmail(Pedido pedido) throws Exception {
        List<ItemPedido> listProdutosPedido = new ArrayList<>();

        List<ItemPedido> todosItemPedido = itemPedidoRepository.findAll();

        for (ItemPedido item : todosItemPedido) {
            if (item.getPedido().getIdPedido() == pedido.getIdPedido()) {
                listProdutosPedido.add(item);
            }
        }

        String htmlEmail = "<h2>Pedido ID: " + pedido.getIdPedido() + " Processado</h2>";
        htmlEmail += "<br>";
        htmlEmail += "<p>Data do Pedido: " + simpleDateFormat.format(pedido.getDataPedido()) + "</p>";
        htmlEmail += "<p>Data do Envio: " + simpleDateFormat.format(pedido.getDataEnvio()) + "</p>";
        htmlEmail += "<p>Data da Entrega(previsão): " + simpleDateFormat.format(pedido.getDataEntrega()) + "</p>";
        htmlEmail += "<br>";
        htmlEmail += "<h3>Produtos:</h3>";
        htmlEmail += "<br>";
        for (ItemPedido itemPedido : listProdutosPedido) {
            htmlEmail += "<p>" + itemPedido.toString() + "</p>";
        }
        htmlEmail += "<br>";
        htmlEmail += "<h3>Valores Totais:</h3>";
        htmlEmail += "<p>Valor Bruto do Pedido: R$" + String.format("%.2f", pedido.getValorTotalPedidoBruto()) + "</p>";
        htmlEmail += "<p>Valor de Desconto do Pedido: R$" + String.format("%.2f", pedido.getValorTotalDescontoPedido()) + "</p>";
        htmlEmail += "<p><strong>Valor Liquido do Pedido: R$" + String.format("%.2f", pedido.getValorTotalPedidoLiquido()) + "</strong></p>";
        htmlEmail += "<br>";
        htmlEmail += "<h3>Dados do cliente:</h3>";
        htmlEmail += "<br>";
        htmlEmail += "<h3>Cliente: </h3>";
        htmlEmail += "<p>" + (clienteService.toDTO(pedido.getCliente())).toString() + "</p>";
        htmlEmail += "<br>";
        htmlEmail += "<h3>Endereço:</h3>";
        htmlEmail += "<p>" + (enderecoService.findByIdEndereco(clienteService.findClienteById(toDTO(pedido).getIdCliente()).getIdEndereco())).toString() + "</p>";

        return htmlEmail;
    }
}
