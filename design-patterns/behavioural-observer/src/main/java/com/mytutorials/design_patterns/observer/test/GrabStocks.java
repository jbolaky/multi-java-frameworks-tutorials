package com.mytutorials.design_patterns.observer.test;

import com.mytutorials.design_patterns.observer.impl.StockObserver;
import com.mytutorials.design_patterns.observer.subject.impl.StockGrabber;

public class GrabStocks {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create the Subject object
		// It will handle updating all observers
		// as well as deleting and adding them

		StockGrabber stockGrabber = new StockGrabber();

		// Create an Observer that will be sent updates from Subject
		@SuppressWarnings("unused")
		StockObserver observer1 = new StockObserver(stockGrabber);

		stockGrabber.setIBMPrice(197.00);
		stockGrabber.setAAPLPrice(677.60);
		stockGrabber.setGOOGPrice(676.40);

		@SuppressWarnings("unused")
		StockObserver observer2 = new StockObserver(stockGrabber);

		stockGrabber.setIBMPrice(197.00);
		stockGrabber.setAAPLPrice(677.60);
		stockGrabber.setGOOGPrice(676.40);

		// Delete one of the observers
		// stockGrabber.unregister(observer2);

		stockGrabber.setIBMPrice(197.00);
		stockGrabber.setAAPLPrice(677.60);
		stockGrabber.setGOOGPrice(676.40);

		// Create 3 threads using the Runnable interface
		// GetTheStock implements Runnable, so it doesn't waste
		// its one extendable class option

		Runnable getIBM = new GetTheStock(stockGrabber, 2, "IBM", 197.00);
		Runnable getAAPL = new GetTheStock(stockGrabber, 2, "AAPL", 677.60);
		Runnable getGOOG = new GetTheStock(stockGrabber, 2, "GOOG", 676.40);

		// Call for the code in run to execute
		new Thread(getIBM).start();
		new Thread(getAAPL).start();
		new Thread(getGOOG).start();

	}

}
