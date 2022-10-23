package br.com.nikisgabriel.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nikisgabriel.controllers.PersonController;
import br.com.nikisgabriel.data.vo.v1.PersonVO;
import br.com.nikisgabriel.exceptions.ResourceNotFoundException;
import br.com.nikisgabriel.mapper.DozerMapper;
import br.com.nikisgabriel.model.Person;
import br.com.nikisgabriel.repositories.PersonRepository;

// o spring boot utiliza a annotation @Service para realização de dependências  em tempo de execução
@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	private PersonRepository repository;

	public List<PersonVO> findAll() {
		logger.info("Finding all people!!");
		List<PersonVO> vos = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		vos.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));

		return vos;
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person!!");
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma pessoa cadastrada com este ID"));
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}

	public PersonVO create(PersonVO person) {
		logger.info("Creating one person!!");
		Person entity = DozerMapper.parseObject(person, Person.class);
		PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(entity.getId())).withSelfRel());
		return vo;
	}

	public PersonVO update(PersonVO person) {
		logger.info("Updating one person!!");
		Person entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma pessoa cadastrada com este ID"));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());

		PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(entity.getId())).withSelfRel());
		return vo;
	}

	// all versions
	public void delete(Long id) {
		logger.info("Deleting one person!!");
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma pessoa cadastrada com este ID"));
		repository.delete(entity);
	}
}
