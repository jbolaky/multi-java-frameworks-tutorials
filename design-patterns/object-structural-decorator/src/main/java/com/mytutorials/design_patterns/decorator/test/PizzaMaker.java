package com.mytutorials.design_patterns.decorator.test;

import com.mytutorials.design_patterns.concretedecorator.Mozzarella;
import com.mytutorials.design_patterns.concretedecorator.TomatoSauce;
import com.mytutorials.design_patterns.decorator.component.Pizza;
import com.mytutorials.design_patterns.decorator.component.impl.PlainPizza;

public class PizzaMaker {

	public static void main(String[] args) {

		// The PlainPizza object is sent to the Mozzarella constructor
		// and then to the TomatoSauce constructor

		Pizza basicPizza = new TomatoSauce(new Mozzarella(new PlainPizza()));

		System.out.println("Ingredients: " + basicPizza.getDescription());
		System.out.println("Price: " + basicPizza.getCost());

	}
}
