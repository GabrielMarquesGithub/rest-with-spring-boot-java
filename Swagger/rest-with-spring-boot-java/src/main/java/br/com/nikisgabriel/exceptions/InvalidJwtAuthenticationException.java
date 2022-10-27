package br.com.nikisgabriel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public abstract class InvalidJwtAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	// construtor para passar a mensagem de error
	public InvalidJwtAuthenticationException(String ex) {
		super(ex);
	}
}
