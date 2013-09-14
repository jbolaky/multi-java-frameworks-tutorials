package com.mytutorials.design_patterns.mediator;

import com.mytutorials.design_patterns.mediator.colleague.Colleague;

public interface Mediator {

	public void saleOffer(String stock, int shares, int collCode);

	public void buyOffer(String stock, int shares, int collCode);

	public void addColleague(Colleague colleague);
}
