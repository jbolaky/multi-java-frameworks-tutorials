package com.mytutorials.design_patterns.interpreter.expression.impl;

import com.mytutorials.design_patterns.interpreter.expression.AbstractExpression;

public class Quarts extends AbstractExpression{

	public String gallons(double quantity) {

		return Double.toString(quantity / 4);

	}

	public String quarts(double quantity) {

		return Double.toString(quantity);

	}

	public String pints(double quantity) {

		return Double.toString(quantity * 2);

	}

	public String cups(double quantity) {

		return Double.toString(quantity * 4);

	}

	public String tablespoons(double quantity) {

		return Double.toString(quantity * 64);

	}

}
