package com.residencia.ecommerce.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "item_pedido")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idItemPedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Integer idItemPedido;
	@Column(name = "quantidade")
	private Integer quantidadeItemPedido;
	@Column(name = "preco_venda")
	private BigDecimal precoVendaItemPedido;
	@Column(name = "percentual_desconto")
	private BigDecimal percentualDescontoItemPedido;
	@Column(name = "valor_bruto")
	private BigDecimal valorBrutoItemPedido;
	@Column(name = "valor_liquido")
	private BigDecimal valorLiquidoItemPedido;

	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
	private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
	private Produto produto;

	public Integer getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Integer getQuantidadeItemPedido() {
		return quantidadeItemPedido;
	}

	public void setQuantidadeItemPedido(Integer quantidadeItemPedido) {
		this.quantidadeItemPedido = quantidadeItemPedido;
	}

	public BigDecimal getPrecoVendaItemPedido() {
		return precoVendaItemPedido;
	}

	public void setPrecoVendaItemPedido(BigDecimal precoVendaItemPedido) {
		this.precoVendaItemPedido = precoVendaItemPedido;
	}

	public BigDecimal getPercentualDescontoItemPedido() {
		return percentualDescontoItemPedido;
	}

	public void setPercentualDescontoItemPedido(BigDecimal percentualDescontoItemPedido) {
		this.percentualDescontoItemPedido = percentualDescontoItemPedido;
	}

	public BigDecimal getValorBrutoItemPedido() {
		return valorBrutoItemPedido;
	}

	public void setValorBrutoItemPedido(BigDecimal valorBrutoItemPedido) {
		this.valorBrutoItemPedido = valorBrutoItemPedido;
	}

	public BigDecimal getValorLiquidoItemPedido() {
		return valorLiquidoItemPedido;
	}

	public void setValorLiquidoItemPedido(BigDecimal valorLiquidoItemPedido) {
		this.valorLiquidoItemPedido = valorLiquidoItemPedido;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
