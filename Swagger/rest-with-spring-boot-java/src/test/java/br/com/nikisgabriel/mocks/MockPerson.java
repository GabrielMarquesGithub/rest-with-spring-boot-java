package br.com.nikisgabriel.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.nikisgabriel.model.Person;

public class MockPerson {
	public Person mockEntity() {
		Person entity = new Person();
		entity.setFirstName("nome");
		entity.setLastName("sobrenome");
		entity.setAdress("edereco");
		entity.setGender("genêro");
		return entity;
	}

	public List<Person> mockEntityList() {
		List<Person> entityList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Person entity = new Person();
			entity.setFirstName("nome " + i);
			entity.setLastName("sobrenome " + i);
			entity.setAdress("edereco " + i);
			entity.setGender("genêro " + i);
			entityList.add(entity);
		}

		return entityList;
	}
}
