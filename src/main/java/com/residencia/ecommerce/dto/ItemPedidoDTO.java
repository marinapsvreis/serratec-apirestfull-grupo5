package com.residencia.ecommerce.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemPedidoDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idItemPedido;
	private Integer idPedido;
	private Integer idProduto;
	private Integer quantidadeItemPedido;
	private Integer precoVendaItemPedido;
	private BigDecimal percentualDescontoItemPedido;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private BigDecimal valorBrutoItemPedido;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private BigDecimal valorLiquidoItemPedido;

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

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

}
