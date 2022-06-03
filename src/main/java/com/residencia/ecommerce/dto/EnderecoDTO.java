package com.residencia.ecommerce.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;


public class EnderecoDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idEndereco;
	@Size(max = 9, message = "Cep não pode ter mais que 9 caracteres")
	@NotBlank(message = "O cep não pode ser nulo.")
	private String cep;
	@NotBlank(message = "A rua não pode ser nula.")
	@Size(max = 100, message = "Rua não pode ter mais que 100 caracteres")
	private String rua;
	@Size(max = 30, message = "Bairro não pode ter mais que 30 caracteres")
	@NotBlank(message = "O bairro não pode ser nulo.")
	private String bairro;
	@Size(max = 30, message = "Cidade não pode ter mais que 30 caracteres")
	private String cidade;
	@Min(value = 1, message="Numero nao pode ser menor que 1")
	@NotNull(message = "Numero não pode ser nulo")
	private Integer numero;
	@Size(max = 20, message = "Complemento não pode ter mais que 20 caracteres")
	private String complemento;
	@Size(max = 2, message = "UF não pode ter mais que 2 caracteres")
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
