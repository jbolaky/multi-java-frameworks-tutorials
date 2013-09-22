package com.mytutorials.design_patterns.visitor.element.impl;

import com.mytutorials.design_patterns.visitor.Visitor;
import com.mytutorials.design_patterns.visitor.element.Visitable;

public class Liquor implements Visitable {

	private double price;

	public Liquor(double item) {

		price = item;
	}

	public double getNewPrice(Visitor visitor) {

		return visitor.visit(this);
	}

	public double getPrice() {

		return price;
	}

}
