package com.residencia.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class CategoriaDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idCategoria;

	@NotBlank(message = "Nome da categoria não poder ser nulo")
	private String nomeCategoria;
	private String descricaoCategoria;

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}

	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}

}
