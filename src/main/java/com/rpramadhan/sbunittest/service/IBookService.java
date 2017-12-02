package com.rpramadhan.sbunittest.service;

import com.rpramadhan.sbunittest.model.Book;

public interface IBookService {
	
	public Book findById(Long id);
	
	public void saveOrUpdate(Book book) throws Exception;

}
