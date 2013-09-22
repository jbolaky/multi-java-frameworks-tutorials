package com.mytutorials.design_patterns.interpreter.expression;

public abstract class AbstractExpression {

	public abstract String gallons(double quantity);

	public abstract String quarts(double quantity);

	public abstract String pints(double quantity);

	public abstract String cups(double quantity);

	public abstract String tablespoons(double quantity);

}
