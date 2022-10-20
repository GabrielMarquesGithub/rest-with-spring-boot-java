package br.com.nikisgabriel.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.nikisgabriel.data.vo.v1.PersonVO;
import br.com.nikisgabriel.mocks.MockPerson;

@SpringBootTest
class DozerMapperTest {

	MockPerson inputObject = new MockPerson();
	
	@Test
	public void parseEntityToVOTest() {
		PersonVO output = DozerMapper.parseObject(inputObject.mockEntity(), PersonVO.class);
		System.out.println(output.getFirstName());
		
	}

}
