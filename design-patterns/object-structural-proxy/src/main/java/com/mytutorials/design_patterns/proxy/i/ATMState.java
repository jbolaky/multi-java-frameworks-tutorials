package com.mytutorials.design_patterns.proxy.i;

public interface ATMState {

	  void insertCard();

	  void ejectCard();

	  void insertPin(int pinEntered);

	  void requestCash(int cashToWithdraw);
}
