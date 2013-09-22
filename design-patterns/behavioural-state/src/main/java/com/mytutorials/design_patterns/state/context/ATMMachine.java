package com.mytutorials.design_patterns.state.context;

import com.mytutorials.design_patterns.state.ATMState;
import com.mytutorials.design_patterns.state.impl.HasCard;
import com.mytutorials.design_patterns.state.impl.HasPin;
import com.mytutorials.design_patterns.state.impl.NoCard;
import com.mytutorials.design_patterns.state.impl.NoCash;

public class ATMMachine {

	private ATMState hasCard;

	private ATMState noCard;

	private ATMState hasCorrectPin;

	private ATMState atmOutOfMoney;

	private ATMState currentState;

	private int cashInMachine = 2000;

	private boolean correctPinEntered = false;

	public ATMMachine() {

		hasCard = new HasCard(this);

		noCard = new NoCard(this);

		hasCorrectPin = new HasPin(this);

		atmOutOfMoney = new NoCash(this);

		setATMState(noCard);

		if (cashInMachine < 0) {

			currentState = atmOutOfMoney;

		}

	}

	public void setATMState(ATMState newATMState) {

		currentState = newATMState;
		System.out.println(currentState.toString());
	}

	public void setCashInMachine(int newCashInMachine) {

		cashInMachine = newCashInMachine;

	}

	public void insertCard() {

		currentState.insertCard();

	}

	public void ejectCard() {

		currentState.ejectCard();

	}

	public void requestCash(int cashToWithdraw) {

		currentState.requestCash(cashToWithdraw);

	}

	public void insertPin(int pinEntered) {

		currentState.insertPin(pinEntered);

	}

	public ATMState getYesCardState() {
		return hasCard;
	}

	public ATMState getNoCardState() {
		return noCard;
	}

	public ATMState getHasPin() {
		return hasCorrectPin;
	}

	public ATMState getNoCashState() {
		return atmOutOfMoney;
	}

	public boolean isCorrectPinEntered() {
		return correctPinEntered;
	}

	public void setCorrectPinEntered(boolean correctPinEntered) {
		this.correctPinEntered = correctPinEntered;
	}

	public int getCashInMachine() {
		return cashInMachine;
	}
	
}
