package com.mytutorials.design_patterns.state.impl;

import com.mytutorials.design_patterns.state.ATMState;
import com.mytutorials.design_patterns.state.context.ATMMachine;

public class NoCash implements ATMState {

	ATMMachine atmMachine;

	public NoCash(ATMMachine newATMMachine) {

		atmMachine = newATMMachine;

	}

	public void insertCard() {

		System.out.println("We don't have any money");

		System.out.println("Your card is ejected");

	}

	public void ejectCard() {

		System.out.println("We don't have any money");

		System.out.println("There is no card to eject");

	}

	public void requestCash(int cashToWithdraw) {

		System.out.println("We don't have any money");

	}

	public void insertPin(int pinEntered) {

		System.out.println("We don't have any money");

	}

}
