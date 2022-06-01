package com.residencia.ecommerce.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
	private final int status;
	private final String message;
	private List<String> details;

	public ErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ErrorResponse(int status, String message, List<String> details) {
		super();
		this.status = status;
		this.message = message;
		this.details = details;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}