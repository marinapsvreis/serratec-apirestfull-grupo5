package com.residencia.ecommerce.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdutoDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idProduto;
	private String nomeProduto;
	private String descricaoProduto;
	private Integer qtdEstoqueProduto;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)	
	private Date dataCadastroProduto;
	private BigDecimal valorUnitario;
	private String nomeImagemProduto;
	private Integer idCategoria;

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public Integer getQtdEstoqueProduto() {
		return qtdEstoqueProduto;
	}

	public void setQtdEstoqueProduto(Integer qtdEstoqueProduto) {
		this.qtdEstoqueProduto = qtdEstoqueProduto;
	}

	public Date getDataCadastroProduto() {
		return dataCadastroProduto;
	}

	public void setDataCadastroProduto(Date dataCadastroProduto) {
		this.dataCadastroProduto = dataCadastroProduto;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getNomeImagemProduto() {
		return nomeImagemProduto;
	}

	public void setNomeImagemProduto(String nomeImagemProduto) {
		this.nomeImagemProduto = nomeImagemProduto;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

}
