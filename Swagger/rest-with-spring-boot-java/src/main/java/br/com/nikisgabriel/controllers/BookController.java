package br.com.nikisgabriel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nikisgabriel.data.vo.v1.BookVO;
import br.com.nikisgabriel.services.BookServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

// @CrossOrigin pode ser aplicado localmente até diretamente em requisições 
@RestController
@RequestMapping("/api/book/v1")
//@Tag é usado para personalização da documentação pelo swagger para um controller
@Tag(name = "Books", description = "Endpoints para gerenciamento de livros")
public class BookController {
	
	@Autowired
	private BookServices service;

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Encontrar um livro", description = "Retorna um livro pelo ID passado",
			// tag recebe o mesmo valor do controller
			tags = { "Books" },
			// responses apresenta as possíveis respostas do Endpoint
			responses = {
					// Resposta de sucesso com status 200
					@ApiResponse(description = "Sucess", responseCode = "200",
							// Conteúdo retornado
							content = @Content(schema = @Schema(implementation = BookVO.class))),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public BookVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}

	@GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Retornar uma lista de livros", description = "Retornar uma lista de livros",
			// tag recebe o mesmo valor do controller
			tags = { "Books" },
			// responses apresenta as possíveis respostas do Endpoint
			responses = {
					// Resposta de sucesso com status 200
					@ApiResponse(description = "Sucess", responseCode = "200",
							// Conteúdo retornado
							content = { @Content(
									// tipo de retorno
									mediaType = "application/json",
									// objeto de retorno, passamos um schema que implementa BookVO
									array = @ArraySchema(schema = @Schema(implementation = BookVO.class))) }),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public List<BookVO> findAll() {
		return service.findAll();
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Persistir um livro", description = "Persiste um livro que deve ser passada em formato de JSON ou XML e o recebe como resposta",
			// tag recebe o mesmo valor do controller
			tags = { "Books" },
			// responses apresenta as possíveis respostas do Endpoint
			responses = {
					// Resposta de sucesso com status 200
					@ApiResponse(description = "Sucess", responseCode = "200",
							// Conteúdo retornado
							content = @Content(schema = @Schema(implementation = BookVO.class))),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public BookVO create(@RequestBody BookVO person) {
		return service.create(person);
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	@Operation(summary = "Atualizar um livro", description = "Atualiza um livro que deve ser passada em formato de JSON ou XML e o recebe como resposta",
			// tag recebe o mesmo valor do controller
			tags = { "Books" },
			// responses apresenta as possíveis respostas do Endpoint
			responses = {
					// Resposta de sucesso com status 200
					@ApiResponse(description = "Sucess", responseCode = "200",
							// Conteúdo retornado
							content = @Content(schema = @Schema(implementation = BookVO.class))),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public BookVO update(@RequestBody BookVO person) {
		return service.update(person);
	}

	// methods all versions
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remover um livro", description = "Remove um livro e salva no banco de dados deve receber um ID",
			// tag recebe o mesmo valor do controller
			tags = { "Books" },
			// responses apresenta as possíveis respostas do Endpoint
			responses = {
					// Resposta de sucesso com status 200
					@ApiResponse(description = "Sucess", responseCode = "204", content = @Content),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content) })
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		// ResponseEntity é um classe genérica fornecida para possibilitar diferentes
		// meios para manipularmos a resposta
		// noContent é um resposta com status padrão de 204
		return ResponseEntity.noContent().build();
	}
}
