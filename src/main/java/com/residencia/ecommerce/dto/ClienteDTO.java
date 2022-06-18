package com.residencia.ecommerce.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClienteDTO {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Integer idCliente;
	@NotBlank(message = "O email não pode ser nulo.")
	@Size(max = 100, message = "Email da categoria não pode ter mais que 100 caracteres")
	private String email;
	@NotBlank(message = "A senha não pode ser nula.")
	@Size(max = 100, message = "A senha não pode ter mais que 100 caracteres")
	private String password;
	@NotBlank(message = "O nome não pode ser nulo.")
	@Size(max = 100, message = "Nome completo não pode ter mais que 100 caracteres")
	private String nomeCompleto;


	@Schema(example = "111.111.111-11", description = "CPF do cliente")
	@NotBlank(message = "O cpf não pode ser nulo.")
	@Size(max = 14, message = "CPF não pode ter mais que 34 caracteres")
	private String cpf;

	@Schema(example = "(11)11111-1111", description = "Telefone do cliente")
	@Size(max = 14, message = "Telefone não pode ter mais que 11 caracteres")
	private String telefone;

	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo" )
	@Past(message = "A data de nascimento deve estar no passado")
	private Date dataNascimento;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
