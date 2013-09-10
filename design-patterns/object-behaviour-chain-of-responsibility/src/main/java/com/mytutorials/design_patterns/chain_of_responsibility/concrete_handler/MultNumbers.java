package com.mytutorials.design_patterns.chain_of_responsibility.concrete_handler;

import com.mytutorials.design_patterns.chain_of_responsibility.client.Numbers;
import com.mytutorials.design_patterns.chain_of_responsibility.handler.Chain;

public class MultNumbers implements Chain {

	private Chain nextInChain;

	// Defines the next Object to receive the
	// data if this one can't use it
	public void setNextChain(Chain nextChain) {
		nextInChain = nextChain;
	}

	public void calculate(Numbers request) {
		if (request.getCalcWanted() == "sub") {
			System.out.print(request.getNumber1() + " - "
					+ request.getNumber2() + " = "
					+ (request.getNumber1() - request.getNumber2()));
		} else {
			nextInChain.calculate(request);
		}
	}
}// - See more at:
// http://www.newthinktank.com/2012/10/chain-of-responsibility-design-pattern-tutorial/#sthash.A8zoK1Qy.dpuf
