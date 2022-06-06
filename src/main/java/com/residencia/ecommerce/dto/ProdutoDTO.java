package com.residencia.ecommerce.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProdutoDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idProduto;
	@NotBlank(message = "Nome do produoto não pode ser null")
	@Size(max = 30, message = "Nome do produto não pode passar de 30 caracteres")
	private String nomeProduto;
	@Size(max = 100, message = "Descrição do produto não pode ter mais que 100 caracteres")
	private String descricaoProduto;
	@Min(value = 0, message="Estoque do produto não pode ser menor que 0")
	@NotNull(message = "Estoque do produto não pode ser nulo")
	private Integer qtdEstoqueProduto;

	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "America/Sao_Paulo" )
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Date dataCadastroProduto;
	@Min(value = 0, message="Valor unitario nao pode ser menor que 0")
	@NotNull(message = "Valor unitario do produto não pode ser nulo")
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
