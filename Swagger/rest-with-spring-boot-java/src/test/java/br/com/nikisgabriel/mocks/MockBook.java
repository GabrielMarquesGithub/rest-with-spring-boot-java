package br.com.nikisgabriel.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.nikisgabriel.data.vo.v1.BookVO;
import br.com.nikisgabriel.mapper.DozerMapper;
import br.com.nikisgabriel.model.Book;

public class MockBook {
	public Book mockEntity() {
		Book entity = new Book();
		entity.setAuthor("autor");
		entity.setTitle("titulo");
		entity.setLaunchDate(new Date());
		entity.setPrice(0.0);
		return entity;
	}

	public List<Book> mockEntityList() {
		List<Book> entityList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Book entity = new Book();
			entity.setAuthor("autor " + i);
			entity.setTitle("titulo " + i);
			entity.setLaunchDate(new Date());
			entity.setPrice(0.0 + i);
			entityList.add(entity);
		}

		return entityList;
	}
	
	public BookVO mockEntityVO() {
		return DozerMapper.parseObject(this.mockEntity(), BookVO.class);
	}

	public List<BookVO> mockEntityVOList() {
		return DozerMapper.parseListObjects(this.mockEntityList(), BookVO.class);
	}
}
