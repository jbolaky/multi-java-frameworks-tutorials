package com.mytutorials.design_patterns.visitor;

import com.mytutorials.design_patterns.visitor.element.impl.Liquor;
import com.mytutorials.design_patterns.visitor.element.impl.Necessity;
import com.mytutorials.design_patterns.visitor.element.impl.Tobacco;

public interface Visitor {

	// Created to automatically use the right
	// code based on the Object sent
	// Method Overloading

	public double visit(Liquor liquorItem);

	public double visit(Tobacco tobaccoItem);

	public double visit(Necessity necessityItem);
}
