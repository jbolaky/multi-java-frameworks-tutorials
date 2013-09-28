package com.mytutorials.design_patterns.facade.test;

import com.mytutorials.design_patterns.facade.BankAccountFacade;

public class TestBankAccount {

	public static void main(String[] args) {

		BankAccountFacade accessingBank = new BankAccountFacade(12345678, 1234);

		accessingBank.withdrawCash(50.00);

		accessingBank.withdrawCash(990.00);

	}
}
