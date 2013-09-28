package com.mytutorials.design_patterns.wrongdesign;

public abstract class Pizza {

	public abstract void setDescription(String newDescription);

	public abstract String getDescription();

	public abstract void setCost(double newCost);

	public abstract double getCost();

	// I could use getter and setter methods for every
	// potential Pizza topping

}
