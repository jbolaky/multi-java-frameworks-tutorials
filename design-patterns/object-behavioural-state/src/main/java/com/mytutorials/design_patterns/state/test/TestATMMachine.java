package com.mytutorials.design_patterns.state.test;

import com.mytutorials.design_patterns.state.context.ATMMachine;

public class TestATMMachine {

	public static void main(String[] args) {

		ATMMachine atmMachine = new ATMMachine();

		atmMachine.insertCard();
		atmMachine.ejectCard();
		atmMachine.insertCard();
		atmMachine.insertPin(1234);
		atmMachine.requestCash(2000);
		atmMachine.insertCard();
		atmMachine.insertPin(1234);

	}
}
