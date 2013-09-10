package com.mytutorials.design_patterns.chain_of_responsibility.concrete_handler;

import com.mytutorials.design_patterns.chain_of_responsibility.client.Numbers;
import com.mytutorials.design_patterns.chain_of_responsibility.handler.Chain;

public class SubtractNumbers implements Chain {

	@SuppressWarnings("unused")
	private Chain nextInChain;

	// Defines the next Object to receive the
	// data if this one can't use it
	public void setNextChain(Chain nextChain) {
		nextInChain = nextChain;
	}

	public void calculate(Numbers request) {
		if (request.getCalcWanted() == "div") {
			System.out.print(request.getNumber1() + " / "
					+ request.getNumber2() + " = "
					+ (request.getNumber1() / request.getNumber2()));
		} else {
			System.out.print("Only works for add, sub, mult, and div");
		}
	}
}