package com.mytutorials.design_patterns.visitor.test;

import com.mytutorials.design_patterns.visitor.element.impl.Liquor;
import com.mytutorials.design_patterns.visitor.element.impl.Necessity;
import com.mytutorials.design_patterns.visitor.element.impl.Tobacco;
import com.mytutorials.design_patterns.visitor.impl.TaxHolidayVisitor;
import com.mytutorials.design_patterns.visitor.impl.TaxVisitor;

public class VisitorTest {

	public static void main(String[] args) {

		TaxVisitor taxCalc = new TaxVisitor();
		TaxHolidayVisitor taxHolidayCalc = new TaxHolidayVisitor();
		Necessity milk = new Necessity(3.47);
		Liquor vodka = new Liquor(11.99);
		Tobacco cigars = new Tobacco(19.99);

		System.out.println(milk.getNewPrice(taxCalc) + "\n");
		System.out.println(vodka.getNewPrice(taxCalc) + "\n");
		System.out.println(cigars.getNewPrice(taxCalc) + "\n");

		System.out.println("TAX HOLIDAY PRICES\n");
		System.out.println(milk.getNewPrice(taxHolidayCalc) + "\n");
		System.out.println(vodka.getNewPrice(taxHolidayCalc) + "\n");
		System.out.println(cigars.getNewPrice(taxHolidayCalc) + "\n");

	}
}
