package com.residencia.ecommerce.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "pedido")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idPedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Integer idPedido;

	@Column(name = "data_pedido")
	private Date dataPedido;

	@Column(name = "data_entrega")
	private Date dataEntrega;

	@Column(name = "data_envio")
	private Date dataEnvio;

	@Column(name = "status")
	private Boolean status;

	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido")
	@JsonIgnore
	private List<ItemPedido> listItemPedido;

	@Column(name = "valor_bruto_total")
	private BigDecimal valorTotalPedidoBruto;
	@Column(name = "valor_desconto_total")
	private BigDecimal valorTotalDescontoPedido;
	@Column(name = "valor_liquido_total")
	private BigDecimal valorTotalPedidoLiquido;

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValorTotalPedidoBruto() {
		return valorTotalPedidoBruto;
	}

	public void setValorTotalPedidoBruto(BigDecimal valorTotalPedidoBruto) {
		this.valorTotalPedidoBruto = valorTotalPedidoBruto;
	}

	public BigDecimal getValorTotalDescontoPedido() {
		return valorTotalDescontoPedido;
	}

	public void setValorTotalDescontoPedido(BigDecimal valorTotalDescontoPedido) {
		this.valorTotalDescontoPedido = valorTotalDescontoPedido;
	}

	public BigDecimal getValorTotalPedidoLiquido() {
		return valorTotalPedidoLiquido;
	}

	public void setValorTotalPedidoLiquido(BigDecimal valorTotalPedidoLiquido) {
		this.valorTotalPedidoLiquido = valorTotalPedidoLiquido;
	}

}
