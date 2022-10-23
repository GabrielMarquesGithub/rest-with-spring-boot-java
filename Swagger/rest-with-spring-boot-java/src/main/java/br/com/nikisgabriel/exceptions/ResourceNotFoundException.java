package br.com.nikisgabriel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Essa exceção retorna um StatusCode neste caso de bad request
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	//construtor para passar a mensagem de error
	public ResourceNotFoundException(String ex) {
		super(ex);
	}
}
