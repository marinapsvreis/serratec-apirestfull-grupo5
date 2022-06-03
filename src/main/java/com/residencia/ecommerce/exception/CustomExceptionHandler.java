package com.residencia.ecommerce.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Erro Interno no Servidor", details);

		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(DescricaoProdutoException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(DescricaoProdutoException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Descrição de produtos não podem se repetir",
				details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoSuchElementFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(NoSuchElementFoundException ex,
			WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Registro Não Encontrado", details);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PedidoException.class)
	public final ResponseEntity<Object> handlePedidoException(PedidoException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Ocorreu um erro no pedido", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EnderecoException.class)
	public final ResponseEntity<Object> handleEnderecoException(EnderecoException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Ocorreu um erro no endereço", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ClienteException.class)
	public final ResponseEntity<Object> handleClienteException(ClienteException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Ocorreu um erro no cadastro do cliente", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CategoriaException.class)
	public final ResponseEntity<Object> handleCategoriaException(CategoriaException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Ocorreu um erro no cadastro da categoria", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Ocorreu um erro nos dados", details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		ErrorResponse error = new ErrorResponse(httpStatus.value(), "Falha na Validação dos Dados da Requisição",
				details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
