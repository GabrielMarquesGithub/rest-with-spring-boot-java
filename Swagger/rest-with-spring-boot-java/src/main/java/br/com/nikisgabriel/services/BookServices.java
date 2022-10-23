package br.com.nikisgabriel.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nikisgabriel.controllers.BookController;
import br.com.nikisgabriel.data.vo.v1.BookVO;
import br.com.nikisgabriel.exceptions.ResourceNotFoundException;
import br.com.nikisgabriel.mapper.DozerMapper;
import br.com.nikisgabriel.model.Book;
import br.com.nikisgabriel.repositories.BookRepository;

@Service
public class BookServices {
	
	@Autowired
	private BookRepository repository;
	
	public List<BookVO> findAll(){
		List<BookVO> VOs = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		VOs.forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		return VOs;
	}
	
	public BookVO findById(long id){
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum livro cadastrada com este ID"));
		BookVO VO = DozerMapper.parseObject(entity, BookVO.class);
		VO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return VO;
	}
	
	public BookVO create(BookVO book){
		Book entity = DozerMapper.parseObject(book, Book.class);
		book = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		book.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return book;
	}
	
	public BookVO update(BookVO book){
		//retornando o resultado da busca ou lançando erro
		Book entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum livro cadastrada com este ID"));
		
		//setando novos valores a entidade vindos do value object
		entity.setAuthor(book.getAuthor());
		entity.setTitle(book.getTitle());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		
		book = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		book.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return book;
	}
	
	public void delete(long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum livro cadastrada com este ID"));
		repository.delete(entity);
	}
}
