package com.mytutorials.design_patterns.strategy.impl;

import com.mytutorials.design_patterns.strategy.Flys;

// Class used if the Animal can't fly

public class CantFly implements Flys {

	public String fly() {

		return "I can't fly";
	}
}
