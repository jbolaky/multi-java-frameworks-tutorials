package com.mytutorials.design_patterns.chain_of_responsibility.handler;

import com.mytutorials.design_patterns.chain_of_responsibility.client.Numbers;

public interface Chain {

	// Defines the next Object to receive the data
	// if this Object can't process it
	void setNextChain(Chain nextChain);

	// Either solves the problem or passes the data
	// to the next Object in the chain
	void calculate(Numbers request);

}