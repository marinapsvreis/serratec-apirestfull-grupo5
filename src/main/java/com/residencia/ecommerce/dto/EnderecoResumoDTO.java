package com.residencia.ecommerce.dto;

public class EnderecoResumoDTO {
	private String cep;
	private Integer numero;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "EnderecoResumoDTO [cep=" + cep + ", numero=" + numero + "]";
	}

}
