package com.mytutorials.design_patterns.observer.subject;

import com.mytutorials.design_patterns.observer.Observer;


public interface Subject {

	public void register(Observer o);

	public void unregister(Observer o);

	public void notifyObserver();

}
