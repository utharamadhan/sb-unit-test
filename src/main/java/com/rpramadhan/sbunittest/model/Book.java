package com.rpramadhan.sbunittest.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6358381915366283196L;
	
	private Book() {}
	
	public static Book createInstance(Long id, String title, String author) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setAuthor(author);
		return book;
	}
	
	public static Book createInstance(String title, String author) {
		return Book.createInstance(null, title, author);
	}
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("author")
	private String author;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

}
