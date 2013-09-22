package com.mytutorials.design_patterns.mediator.test;

import com.mytutorials.design_patterns.mediator.StockMediator;
import com.mytutorials.design_patterns.mediator.colleague.impl.GormanSlacks;
import com.mytutorials.design_patterns.mediator.colleague.impl.JTPoorman;

public class TestStockMediator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		StockMediator nyse = new StockMediator();

		GormanSlacks broker = new GormanSlacks(nyse);

		JTPoorman broker2 = new JTPoorman(nyse);

		broker.saleOffer("MSFT", 100);
		broker.saleOffer("GOOG", 50);

		broker2.buyOffer("MSFT", 100);
		broker2.saleOffer("NRG", 10);

		broker.buyOffer("NRG", 10);

		nyse.getstockOfferings();
	}

}
