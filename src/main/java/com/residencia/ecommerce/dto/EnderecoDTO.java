package com.residencia.ecommerce.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EnderecoDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idEndereco;
	@NotBlank(message = "O cep não pode ser nulo.")
	private String cep;
	@NotBlank(message = "A rua não pode ser nula.")
	private String rua;
	@NotBlank(message = "O bairro não pode ser nulo.")
	private String bairro;
	private String cidade;
	@Min(value = 1, message="Numero nao pode ser menor que 1")
	@NotNull(message = "Numero não pode ser nulo")
	private Integer numero;
	private String complemento;
	private String uf;

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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Rua = " + rua + "<br>"
				+ "Numero = " + numero + "<br>"
				+ "Bairro = " + bairro + "<br>"
				+ "Cidade = " + cidade + "<br>"				
				+ "UF = " + uf + "<br>"
				+ "Cep = " + cep + "<br>"
				+ "Complemento = " + complemento + "<br>";
	}
	
	

}
