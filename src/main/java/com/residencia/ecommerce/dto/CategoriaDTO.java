package com.residencia.ecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoriaDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idCategoria;

	@NotBlank(message = "Nome da categoria não poder ser nulo")
	@Size(max = 30, message = "Nome da categoria não pode ter mais que 30 caracteres")
	private String nomeCategoria;
	@Size(max = 150, message = "Nome da categoria não pode ter mais que 150 caracteres")
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
