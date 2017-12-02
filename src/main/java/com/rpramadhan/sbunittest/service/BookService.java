package com.rpramadhan.sbunittest.service;

import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.rpramadhan.sbunittest.model.Book;

@Component
public class BookService implements IBookService {
	
	private static final TreeMap<Long, Book> BOOKS = new TreeMap<>();
	static {
		BOOKS.put(1L, Book.createInstance(1L, "How To Win Friends And Influence People", "Dale Carnegie"));
		BOOKS.put(2L, Book.createInstance(2L, "Outliers", "Malcolm Gladwell"));
		BOOKS.put(3L, Book.createInstance(3L, "Man’s Search for Meaning", "Viktor E. Frankl"));
	}
	
	
	@Override
	public Book findById(Long id) {
		return BOOKS.containsKey(id) ? BOOKS.get(id) : null;
	}

	@Override
	public void saveOrUpdate(Book book) throws Exception {
		if (book.getId() != null && !BOOKS.containsKey(book.getId())) {
			throw new Exception("not found");
		} else if (book.getId() == null) {
			book.setId(getLatestId());	
		}
		BOOKS.put(book.getId(), book);
	}
	
	private Long getLatestId() {
		Long _newId = 0L;
		for(Long _id : BOOKS.keySet()) {
			if (_newId < _id) {
				_newId = _id;
			}
		}
		return ++_newId;
	}
	

}
