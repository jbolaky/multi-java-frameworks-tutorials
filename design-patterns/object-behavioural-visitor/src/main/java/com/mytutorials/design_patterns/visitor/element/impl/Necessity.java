package com.mytutorials.design_patterns.visitor.element.impl;

import com.mytutorials.design_patterns.visitor.Visitor;
import com.mytutorials.design_patterns.visitor.element.Visitable;

public class Necessity implements Visitable {

	private double price;

	public Necessity(double item) {

		price = item;

	}

	public double getNewPrice(Visitor visitor) {

		return visitor.visit(this);

	}

	public double getPrice() {

		return price;

	}

}
