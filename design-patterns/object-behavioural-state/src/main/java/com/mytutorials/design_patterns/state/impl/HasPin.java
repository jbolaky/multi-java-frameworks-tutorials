package com.mytutorials.design_patterns.state.impl;

import com.mytutorials.design_patterns.state.ATMState;
import com.mytutorials.design_patterns.state.context.ATMMachine;

public class HasPin implements ATMState {

	ATMMachine atmMachine;

	public HasPin(ATMMachine newATMMachine) {

		atmMachine = newATMMachine;

	}

	public void insertCard() {

		System.out.println("You already entered a card");

	}

	public void ejectCard() {

		System.out.println("Your card is ejected");

		atmMachine.setATMState(atmMachine.getNoCardState());

	}

	public void requestCash(int cashToWithdraw) {

		if (cashToWithdraw > atmMachine.getCashInMachine()) {

			System.out.println("You don't have that much cash available");

			System.out.println("Your card is ejected");

			atmMachine.setATMState(atmMachine.getNoCardState());

		} else {

			System.out.println(cashToWithdraw + " is provided by the machine");

			atmMachine.setCashInMachine(atmMachine.getCashInMachine()
					- cashToWithdraw);

			System.out.println("Your card is ejected");

			atmMachine.setATMState(atmMachine.getNoCardState());

			if (atmMachine.getCashInMachine() <= 0) {

				atmMachine.setATMState(atmMachine.getNoCashState());

			}

		}

	}

	public void insertPin(int pinEntered) {

		System.out.println("You already entered a PIN");

	}

}
