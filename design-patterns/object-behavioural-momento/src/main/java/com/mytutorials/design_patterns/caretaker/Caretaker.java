package com.mytutorials.design_patterns.caretaker;

import java.util.ArrayList;
import java.util.List;

import com.mytutorials.design_patterns.momento.Memento;

public class Caretaker {

	// Where all mementos are saved

	private List<Memento> savedArticles = new ArrayList<Memento>();

	// Adds memento to the ArrayList

	public void addMemento(Memento m) {
		savedArticles.add(m);
	}

	// Gets the memento requested from the ArrayList

	public Memento getMemento(int index) {
		return savedArticles.get(index);
	}

}
