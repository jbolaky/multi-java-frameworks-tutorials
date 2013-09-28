package com.mytutorials.design_patterns.proxy.i.impl;

import com.mytutorials.design_patterns.proxy.i.ATMState;

public class HasCard implements ATMState {

	private ATMMachine atmMachine;

	public HasCard(ATMMachine newATMMachine) {

		atmMachine = newATMMachine;

	}

	public void insertCard() {

		System.out.println("You can only insert one card at a time");

	}

	public void ejectCard() {

		System.out.println("Your card is ejected");

		atmMachine.setATMState(atmMachine.getNoCardState());

	}

	public void requestCash(int cashToWithdraw) {

		System.out.println("You have not entered your PIN");

	}

	public void insertPin(int pinEntered) {

		if (pinEntered == 1234) {

			System.out.println("You entered the correct PIN");

			atmMachine.setCorrectPinEntered(true);

			atmMachine.setATMState(atmMachine.getHasPin());

		} else {

			System.out.println("You entered the wrong PIN");

			atmMachine.setCorrectPinEntered(false);

			System.out.println("Your card is ejected");

			atmMachine.setATMState(atmMachine.getNoCardState());

		}
	}

}
