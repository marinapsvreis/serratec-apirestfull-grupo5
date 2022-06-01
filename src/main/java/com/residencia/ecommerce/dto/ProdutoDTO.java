package com.residencia.ecommerce.dto;

import java.util.Date;

public class ProdutoDTO {

	private Integer idProduto;
	private String nomeProduto;
	private String descricaoProduto;
	private Integer qtdEstoqueProduto;
	private Date dataCadastroProduto;
	private Double valorUnitario;
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

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
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
