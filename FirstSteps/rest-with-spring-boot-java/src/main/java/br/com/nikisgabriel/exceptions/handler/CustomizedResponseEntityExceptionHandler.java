package br.com.nikisgabriel.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.nikisgabriel.exceptions.ExceptionResponse;

//o @ControllerAdvice é um  responsável pelo tratamento de exceções globais substituindo a necessidade de em cada controller ter de realizar o tratamento 
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	// @ExceptionHandler define qual tipo de exceção o método ira tratar neste caso
	// todas referentes a Exception.class as mais genéricas do Java
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handlerAllExceptions(
			Exception ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), 
				ex.getMessage(), 
				request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UnsupportedOperationException.class)
	public ResponseEntity<ExceptionResponse> handlerBedRequestExceptions(
			Exception ex, WebRequest request) {
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), 
				ex.getMessage(), 
				request.getDescription(false));
		
		//retornando um entidade de resposta com a exceção e o seu respectivo código
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
