package com.residencia.ecommerce.entity;

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
	private Integer precoVendaItemPedido;
	@Column(name = "percentual_desconto")
	private Double percentualDescontoItemPedido;
	@Column(name = "valor_bruto")
	private Double valorBrutoItemPedido;
	@Column(name = "valor_liquido")
	private Double valorLiquidoItemPedido;

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

	public Integer getPrecoVendaItemPedido() {
		return precoVendaItemPedido;
	}

	public void setPrecoVendaItemPedido(Integer precoVendaItemPedido) {
		this.precoVendaItemPedido = precoVendaItemPedido;
	}

	public Double getPercentualDescontoItemPedido() {
		return percentualDescontoItemPedido;
	}

	public void setPercentualDescontoItemPedido(Double percentualDescontoItemPedido) {
		this.percentualDescontoItemPedido = percentualDescontoItemPedido;
	}

	public Double getValorBrutoItemPedido() {
		return valorBrutoItemPedido;
	}

	public void setValorBrutoItemPedido(Double valorBrutoItemPedido) {
		this.valorBrutoItemPedido = valorBrutoItemPedido;
	}

	public Double getValorLiquidoItemPedido() {
		return valorLiquidoItemPedido;
	}

	public void setValorLiquidoItemPedido(Double valorLiquidoItemPedido) {
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
