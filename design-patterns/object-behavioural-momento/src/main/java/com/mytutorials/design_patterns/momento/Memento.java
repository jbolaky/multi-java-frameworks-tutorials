package com.mytutorials.design_patterns.momento;

public class Memento {

	// The article stored in memento Object

	private String article;

	// Save a new article String to the memento Object

	public Memento(String articleSave) {
		article = articleSave;
	}

	// Return the value stored in article

	public String getSavedArticle() {
		return article;
	}

}
