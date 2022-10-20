package br.com.nikisgabriel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nikisgabriel.model.Person;

//interface oferecida pelo string com o crud básico já setado
public interface PersonRepository extends JpaRepository<Person, Long> {}
