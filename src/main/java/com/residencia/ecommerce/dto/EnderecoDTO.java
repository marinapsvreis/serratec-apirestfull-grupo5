package com.residencia.ecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


public class EnderecoDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idEndereco;
	@NotNull(message = "O cep não pode ser nulo.")
	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private Integer numero;
	private String complemento;

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Override
	public String toString() {
		return "EnderecoDTO [idEndereco=" + idEndereco + ", cep=" + cep + ", rua=" + rua + ", bairro=" + bairro
				+ ", cidade=" + cidade + ", numero=" + numero + ", complemento=" + complemento + "]";
	}

}
