package br.com.nikisgabriel.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import br.com.nikisgabriel.data.vo.v1.BookVO;
import br.com.nikisgabriel.exceptions.ResourceNotFoundException;
import br.com.nikisgabriel.mocks.MockBook;
import br.com.nikisgabriel.model.Book;
import br.com.nikisgabriel.repositories.BookRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class BookServicesTest {

	@MockBean
	private BookRepository repository;

	@Autowired
	private BookServices bookServices;

	private MockBook input = new MockBook();

	Book book;
	List<Book> bookList;
	BookVO bookVO;
	List<BookVO> bookVOList;
	Optional<Book> optionalBook;

	@BeforeEach
	void setUp() {
		book = input.mockEntity();
		bookList = input.mockEntityList();
		bookVO = input.mockEntityVO();
		bookVOList = input.mockEntityVOList();
		optionalBook = Optional.of(book);
	}

	@Test
	@DisplayName("Deve retornar um livro")
	void testFindById() {
		// pré teste
		long id = 1L;
		book.setId(id);
		bookVO.setKey(id);

		// mock de métodos
		Mockito.when(repository.findById(ArgumentMatchers.eq(id))).thenReturn(optionalBook);
		BookVO entity = bookServices.findById(id);

		// verificando existência
		assertNotNull(entity);
		assertNotNull(entity.getKey());
		assertNotNull(entity.getAuthor());
		assertTrue(entity.toString().contains("api/book/v1/1"));

		// verificando integridade
		assertEquals(bookVO.getKey(), entity.getKey());
		assertEquals(bookVO.getAuthor(), entity.getAuthor());
		assertEquals(bookVO.getTitle(), entity.getTitle());

		// validando chamada de métodos
		Mockito.verify(repository, Mockito.times(1)).findById(ArgumentMatchers.any(Long.class));
	}

	@Test
	@DisplayName("Deve retornar uma lista de livros")
	void testFindAll() {
		// mock de IDs
		long index = 0;
		for (Book book : bookList) {
			book.setId(index);
			;
			index++;
		}

		// mock de métodos
		Mockito.when(repository.findAll()).thenReturn(bookList); // método mocked
		List<BookVO> entities = bookServices.findAll();

		// verificando existência
		assertNotNull(entities);
		assertNotNull(entities.get(1));
		assertTrue(entities.get(1).toString().contains("api/book/v1/1"));
		assertNotNull(entities.get(1).getAuthor());
		assertNotNull(entities.get(1).getTitle());

		assertNotNull(entities.get(7));
		assertTrue(entities.get(7).toString().contains("api/book/v1/7"));
		assertNotNull(entities.get(7).getTitle());
		assertNotNull(entities.get(7).getAuthor());

		// verificando integridade
		BookVO bookTestOne = bookVOList.get(1);
		BookVO bookOne = entities.get(1);
		assertEquals(bookTestOne.getAuthor(), bookOne.getAuthor());
		assertEquals(bookTestOne.getTitle(), bookOne.getTitle());

		BookVO bookTestSeven = bookVOList.get(7);
		BookVO bookSeven = entities.get(7);
		assertEquals(bookTestSeven.getAuthor(), bookSeven.getAuthor());
		assertEquals(bookTestSeven.getTitle(), bookSeven.getTitle());

	}

	@Test
	@DisplayName("Deve persistir um livro e retorna-lo")
	void testCreate() {
		long id = 1L;
		book.setId(id);
		bookVO.setKey(id);

		Mockito.when(repository.save(ArgumentMatchers.eq(book))).thenReturn(book);
		BookVO entity = bookServices.create(bookVO);

		// verificando existência
		assertNotNull(entity);
		assertNotNull(entity.getKey());
		assertNotNull(entity.getAuthor());
		assertTrue(entity.toString().contains("api/book/v1/1"));

		// verificando integridade
		assertEquals(bookVO.getKey(), entity.getKey());
		assertEquals(bookVO.getAuthor(), entity.getAuthor());
		assertEquals(bookVO.getTitle(), entity.getTitle());
	}

	@Test
	@DisplayName("Deve atualizar dados de um livro e retorna-lo")
	void testUpdate() {
		// pré teste
		long id = 1L;
		book.setId(id);
		bookVO.setKey(id);

		Mockito.when(repository.findById(id)).thenReturn(optionalBook);
		Mockito.when(repository.save(ArgumentMatchers.eq(book))).thenReturn(book);

		BookVO entity = bookServices.update(bookVO);

		// verificando existência
		assertNotNull(entity);
		assertNotNull(entity.getKey());
		assertNotNull(entity.getAuthor());
		assertTrue(entity.toString().contains("api/book/v1/1"));

		// verificando integridade
		assertEquals(bookVO.getKey(), entity.getKey());
		assertEquals(bookVO.getAuthor(), entity.getAuthor());
		assertEquals(bookVO.getTitle(), entity.getTitle());

		// validando chamada de métodos
		Mockito.verify(repository, Mockito.times(1)).findById(ArgumentMatchers.any(Long.class));
	}

	@Test
	@DisplayName("Deve remover um livro")
	void testDelete() {
		// pré teste
		long id = 1L;

		// mock de método
		Mockito.when(repository.findById(id)).thenReturn(optionalBook);
		bookServices.delete(id);

		// validando chamada de métodos
		Mockito.verify(repository, Mockito.times(1)).delete(ArgumentMatchers.any(Book.class));
		Mockito.verify(repository, Mockito.times(1)).findById(ArgumentMatchers.any(Long.class));

	}

	@Test
	@DisplayName("Deve lançar uma exceção por não encontrar algo")
	void testFindWithWrongId() {
		// pré teste
		long id = 100L;
		Mockito.when(repository.findById(ArgumentMatchers.eq(id))).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> bookServices.findById(id));
	}

	@Test
	@DisplayName("Deve lançar uma exceção por não encontrar algo")
	void testUpdateWithWrongId() {
		// pré teste
		long id = 100L;
		bookVO.setKey(id);
		Mockito.when(repository.findById(ArgumentMatchers.eq(id))).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> bookServices.update(bookVO));
	}

	@Test
	@DisplayName("Deve lançar uma exceção por não encontrar algo")
	void testDeleteWithWrongId() {
		// pré teste
		long id = 100L;
		Mockito.when(repository.findById(ArgumentMatchers.eq(id))).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> bookServices.delete(id));
	}
}
