package com.residencia.ecommerce.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
      ErrorResponse error = new ErrorResponse(httpStatus.value(), "Descrição de produtos não podem se repetir", details);
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(NoSuchElementFoundException ex, WebRequest request) {
      List<String> details = new ArrayList<>();
      details.add(ex.getLocalizedMessage());
      HttpStatus httpStatus = HttpStatus.NOT_FOUND;
      ErrorResponse error = new ErrorResponse(httpStatus.value(), "Registro Não Encontrado", details);
      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CpfClienteException.class)
    public final ResponseEntity<Object> handleCpfClienteException(CpfClienteException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse error = new ErrorResponse(httpStatus.value(), "Ja foi registrado um cliente com o CPF informado", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
