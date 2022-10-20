package br.com.nikisgabriel.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nikisgabriel.data.vo.v1.PersonVO;
import br.com.nikisgabriel.data.vo.v2.PersonVOV2;
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

	//v1
	public List<PersonVO> findAll() {
		logger.info("Finding all people!!");
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person!!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma pessoa cadastrada com este ID"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	//v2
	public List<PersonVOV2> findAllV2() {
		logger.info("Finding all people!!");
		return DozerMapper.parseListObjects(repository.findAll(), PersonVOV2.class);
	}

	public PersonVOV2 findByIdV2(Long id) {
		logger.info("Finding one person!!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma pessoa cadastrada com este ID"));
		return DozerMapper.parseObject(entity, PersonVOV2.class);
	}

	public PersonVOV2 createV2(PersonVOV2 person) {
		logger.info("Creating one person!!");
		Person entity = DozerMapper.parseObject(person, Person.class);
		return DozerMapper.parseObject(repository.save(entity), PersonVOV2.class);
	}

	public PersonVOV2 updateV2(PersonVOV2 person) {
		logger.info("Updating one person!!");
		Person entity = repository.findById(person.getId())
		.orElseThrow(() -> new ResourceNotFoundException("Nenhuma pessoa cadastrada com este ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		entity.setBirthDay(person.getBirthDay());
		
		return DozerMapper.parseObject(repository.save(entity), PersonVOV2.class);	
		}
	
	//all versions
	public void delete(Long id) {
		logger.info("Deleting one person!!");
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhuma pessoa cadastrada com este ID"));
		repository.delete(entity);
	}
}
