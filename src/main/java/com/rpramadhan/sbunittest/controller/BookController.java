package com.rpramadhan.sbunittest.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rpramadhan.sbunittest.model.Book;
import com.rpramadhan.sbunittest.model.Response;
import com.rpramadhan.sbunittest.service.IBookService;

@RestController
public class BookController {
	
	@Autowired
	private IBookService service;
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<Response> findById(@PathVariable("id") Long id) {
		Response resp = new Response();
		Book book = service.findById(id);
		if (book != null) {
			resp.setResponseCode("00");
			resp.setResponseDesc("success");
			resp.setResult(book);
			return ResponseEntity.accepted().body(resp);
		} else {
			resp.setResponseCode("04");
			resp.setResponseDesc("not found");
			return ResponseEntity.badRequest().body(resp);
		}
	}
	
	@RequestMapping(value = "/", method=RequestMethod.POST)
	public ResponseEntity<Response> post(@RequestBody Book book) {
		Response resp = new Response();
		try {
			validateBook(book);
			service.saveOrUpdate(book);
			resp.setResponseCode("00");
			resp.setResponseDesc("success");
			resp.setResult(book);
			return ResponseEntity.accepted().body(resp);
		} catch (IllegalArgumentException argEx){
			resp.setResponseCode("10");
			resp.setResponseDesc(argEx.getMessage());
		} catch (Exception ex) {
			resp.setResponseCode("04");
			resp.setResponseDesc(ex.getMessage());
		}
		return ResponseEntity.badRequest().body(resp);
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Response> put(@PathVariable("id") Long id, @RequestBody Book book) {
		Response resp = new Response();
		try {
			validateEditBook(id, book);
			service.saveOrUpdate(book);
			resp.setResponseCode("00");
			resp.setResponseDesc("success");
			resp.setResult(book);
			return ResponseEntity.accepted().body(resp);
		} catch (IllegalArgumentException argEx){
			resp.setResponseCode("10");
			resp.setResponseDesc(argEx.getMessage());
		} catch (Exception ex) {
			resp.setResponseCode("04");
			resp.setResponseDesc(ex.getMessage());
		}
		return ResponseEntity.badRequest().body(resp);
	}
	
	private void validateEditBook(Long id, Book book) throws IllegalArgumentException {
		if (id == null) {
			throw new IllegalArgumentException("id is required");
		} else {
			book.setId(id);
		}
		validateBook(book);
	}
	
	private void validateBook(Book book) throws IllegalArgumentException {
		if (StringUtils.isEmpty(book.getTitle())) {
			throw new IllegalArgumentException("title is required");
		} else if(StringUtils.isEmpty(book.getAuthor())) {
			throw new IllegalArgumentException("author is required");
		}
	}
	
}
