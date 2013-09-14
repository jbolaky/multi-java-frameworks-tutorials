package com.mytutorials.design_patterns.interpreter.expression.impl;

import com.mytutorials.design_patterns.interpreter.expression.AbstractExpression;

public class TableSpoons extends AbstractExpression {

	public String gallons(double quantity) {

		return Double.toString(quantity / 256);

	}

	public String quarts(double quantity) {

		return Double.toString(quantity / 64);

	}

	public String pints(double quantity) {

		return Double.toString(quantity / 32);

	}

	public String cups(double quantity) {

		return Double.toString(quantity / 16);

	}

	public String tablespoons(double quantity) {

		return Double.toString(quantity);

	}
}
