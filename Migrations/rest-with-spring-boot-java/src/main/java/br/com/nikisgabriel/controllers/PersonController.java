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
import org.springframework.web.bind.annotation.RestController;

import br.com.nikisgabriel.data.vo.v1.PersonVO;
import br.com.nikisgabriel.data.vo.v2.PersonVOV2;
import br.com.nikisgabriel.services.PersonServices;

@RestController
public class PersonController {

	// o @Autowired indica para o spring boot um service a ser injetado
	// dinamicamente
	@Autowired
	private PersonServices service;
	
	//v1
	@GetMapping(value = "/person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@GetMapping(value = "/person/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVO> findAll() {
		return service.findAll();
	}
	
	//v2
	@GetMapping(value = "/person/v2/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVOV2 findByIdV2(@PathVariable(value = "id") Long id) {
		return service.findByIdV2(id);
	}
	
	@GetMapping(value = "/person/v2/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonVOV2> findAllV2() {
		return service.findAllV2();
	}
	
	@PostMapping(value = "/person/v2", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
		return service.createV2(person);
	}
	
	@PutMapping(value = "/person/v2", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonVOV2 updateV2(@RequestBody PersonVOV2 person) {
		return service.updateV2(person);
	}
		
	//methods all versions
	@DeleteMapping(value = "/person/v2/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		//ResponseEntity é um classe genérica fornecida para possibilitar diferentes meios para manipularmos a resposta
		//noContent é um resposta com status padrão de 204
		return ResponseEntity.noContent().build();
	}

}
