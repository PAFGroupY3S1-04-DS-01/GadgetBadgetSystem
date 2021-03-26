package com.model;

public class Research {
	
	private int research_id;
	private String authors;
	private String field_of_study;
	private String key_words;
	private String description;
	
	
	public int getResearch_id() {
		return research_id;
	}
	public void setResearch_id(int research_id) {
		this.research_id = research_id;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public String getField_of_study() {
		return field_of_study;
	}
	public void setField_of_study(String field_of_study) {
		this.field_of_study = field_of_study;
	}
	public String getKey_words() {
		return key_words;
	}
	public void setKey_words(String key_words) {
		this.key_words = key_words;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
