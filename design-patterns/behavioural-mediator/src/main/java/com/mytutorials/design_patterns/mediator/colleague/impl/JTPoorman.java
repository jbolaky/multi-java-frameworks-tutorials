package com.mytutorials.design_patterns.mediator.colleague.impl;

import com.mytutorials.design_patterns.mediator.Mediator;
import com.mytutorials.design_patterns.mediator.colleague.Colleague;

public class JTPoorman extends Colleague {

	public JTPoorman(Mediator newMediator) {
		super(newMediator);

		System.out.println("JT Poorman signed up with the stockexchange\n");

	}

}
