package br.com.nikisgabriel.mapper;

//assertEquals deve ser exportado utilizando Static
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.nikisgabriel.data.vo.v1.PersonVO;
import br.com.nikisgabriel.mocks.MockPerson;

@SpringBootTest
class DozerMapperTest {

	
	private MockPerson input = new MockPerson();

	@Test
	@DisplayName("Uma entidade deve ser convertida em um VO")
	void parseEntityToVOTest() {
		PersonVO output = DozerMapper.parseObject(input.mockEntity(), PersonVO.class);
		assertEquals("nome", output.getFirstName());
	}

	@Test
	@DisplayName("Uma lista de entidades devem ser convertidas em uma listas de VOs")
	void parseEntityListToVOTest() {
		List<PersonVO> output = DozerMapper.parseListObjects(input.mockEntityList(), PersonVO.class);
		assertEquals("nome 0", output.get(0).getFirstName());
	}

}
