package com.residencia.ecommerce.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClienteDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idCliente;
	@NotBlank(message = "O email não pode ser nulo.")
	private String email;
	@NotBlank(message = "O nome não pode ser nulo.")
	private String nomeCompleto;

	@Schema(example = "111.111.111-11", description = "CPF do cliente")
	@NotBlank(message = "O cpf não pode ser nulo.")
	private String cpf;
	@Schema(example = "(11)11111-1111", description = "Telefone do cliente")
	private String telefone;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Past(message = "A data de nascimento deve estar no passado")
	private Date dataNascimento;
	private Integer idEndereco;
	
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	@Override
	public String toString() {
		return "Nome Completo = " + nomeCompleto + "<br>"
				+ "Email = " + email + "<br>" 
				+ "CPF = " + cpf + "<br>"
				+ "Telefone = " + telefone + "<br>"
				+ "Data Nascimento = " + simpleDateFormat.format(dataNascimento) + "<br>";
	}

}
