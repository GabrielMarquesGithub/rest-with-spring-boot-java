package br.com.nikisgabriel.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.nikisgabriel.data.vo.v1.PersonVO;
import br.com.nikisgabriel.mapper.DozerMapper;
import br.com.nikisgabriel.mocks.MockPerson;
import br.com.nikisgabriel.model.Person;
import br.com.nikisgabriel.repositories.PersonRepository;

@SpringBootTest
@AutoConfigureMockMvc
class PersonServicesTest {

	@MockBean
	private PersonRepository repository;

	@Autowired
	private PersonServices personServices;

	private MockPerson input = new MockPerson();

	Optional<Person> person;
	Optional<List<Person>> people;

	@BeforeEach
	void setUp() {
		person = Optional.of(input.mockEntity());
		people = Optional.of(input.mockEntityList());
	}

	@Test
	@DisplayName("Deve retornar uma pessoa")
	void testFindById() {
		long id = 1L;
		PersonVO output = DozerMapper.parseObject(person.get(), PersonVO.class);

		Mockito.when(repository.findById(ArgumentMatchers.eq(id))).thenReturn(person);
		assertEquals(personServices.findById(id).getFirstName(), output.getFirstName());
	}

	@Test
	@DisplayName("Deve retornar uma lista de pessoa")
	void testFindAll() {
		List<PersonVO> output = DozerMapper.parseListObjects(people.get(), PersonVO.class);

		Mockito.when(repository.findAll()).thenReturn(people.get());

		PersonVO PersonVOOne = output.get(1);
		assertEquals(personServices.findAll().get(1).getFirstName(), PersonVOOne.getFirstName());
		assertEquals(personServices.findAll().get(1).getLastName(), PersonVOOne.getLastName());

		PersonVO PersonVOTwo = output.get(2);
		assertEquals(personServices.findAll().get(2).getFirstName(), PersonVOTwo.getFirstName());
		assertEquals(personServices.findAll().get(2).getLastName(), PersonVOTwo.getLastName());
	}

	@Test
	@DisplayName("Deve persistir uma pessoa e retornala")
	void testCreate() {
		PersonVO output = DozerMapper.parseObject(person.get(), PersonVO.class);

		Mockito.when(repository.save(ArgumentMatchers.eq(person.get()))).thenReturn(person.get());

		assertEquals(personServices.create(output).getFirstName(), output.getFirstName());
	}

	@Test
	@DisplayName("Deve atualizar dados de uma pessoa e retornala")
	void testUpdate() {
		long id = 1L;
		PersonVO output = DozerMapper.parseObject(person.get(), PersonVO.class);

		output.setKey(id);

		Mockito.when(repository.findById(id)).thenReturn(person);
		Mockito.when(repository.save(ArgumentMatchers.eq(person.get()))).thenReturn(person.get());

		assertEquals(personServices.update(output).getFirstName(), output.getFirstName());
	}

	@Test
	@DisplayName("Deve remover uma pessoa")
	void testDelete() {
		long id = 1L;
		Mockito.when(repository.findById(id)).thenReturn(person);

		personServices.delete(id);

		Mockito.verify(repository, Mockito.times(1)).delete(ArgumentMatchers.any(Person.class));
	}

}
