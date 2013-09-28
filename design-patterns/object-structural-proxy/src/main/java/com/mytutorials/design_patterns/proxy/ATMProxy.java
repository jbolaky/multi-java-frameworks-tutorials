package com.mytutorials.design_patterns.proxy;

import com.mytutorials.design_patterns.proxy.i.ATMState;
import com.mytutorials.design_patterns.proxy.i.GetATMData;
import com.mytutorials.design_patterns.proxy.i.impl.ATMMachine;

//In this situation the proxy both creates and destroys
//an ATMMachine Object

public class ATMProxy implements GetATMData {

	// Allows the user to access getATMState in the

	// Object ATMMachine

	public ATMState getATMState() {

		ATMMachine realATMMachine = new ATMMachine();

		return realATMMachine.getATMState();

	}

	// Allows the user to access getCashInMachine

	// in the Object ATMMachine

	public int getCashInMachine() {

		ATMMachine realATMMachine = new ATMMachine();

		return realATMMachine.getCashInMachine();

	}

}
