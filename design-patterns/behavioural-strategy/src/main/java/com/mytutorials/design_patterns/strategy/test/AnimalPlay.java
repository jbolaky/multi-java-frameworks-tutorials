package com.mytutorials.design_patterns.strategy.test;

import com.mytutorials.design_patterns.strategy.context.Animal;
import com.mytutorials.design_patterns.strategy.context.Bird;
import com.mytutorials.design_patterns.strategy.context.Dog;
import com.mytutorials.design_patterns.strategy.impl.ItFlys;

public class AnimalPlay {

	public static void main(String[] args) {

		Animal sparky = new Dog();

		Animal tweety = new Bird();

		System.out.println("Dog: " + sparky.tryToFly());

		System.out.println("Bird: " + tweety.tryToFly());

		// This allows dynamic changes for flyingType

		sparky.setFlyingAbility(new ItFlys());

		System.out.println("Dog: " + sparky.tryToFly());

	}
}
