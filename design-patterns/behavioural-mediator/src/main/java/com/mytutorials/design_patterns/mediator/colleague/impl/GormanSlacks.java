package com.mytutorials.design_patterns.mediator.colleague.impl;

import com.mytutorials.design_patterns.mediator.Mediator;
import com.mytutorials.design_patterns.mediator.colleague.Colleague;

public class GormanSlacks extends Colleague {

	public GormanSlacks(Mediator newMediator) {
		super(newMediator);

		System.out.println("Gorman Slacks signed up with the stockexchange\n");

	}

}
