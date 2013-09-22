package com.mytutorials.design_patterns.strategy.impl;

import com.mytutorials.design_patterns.strategy.Flys;

// Class used if the Animal can fly

public class ItFlys implements Flys {

	public String fly() {

		return "Flying High";
	}
}
