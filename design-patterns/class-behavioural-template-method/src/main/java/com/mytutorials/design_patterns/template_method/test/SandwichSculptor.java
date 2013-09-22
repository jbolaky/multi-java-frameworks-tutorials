package com.mytutorials.design_patterns.template_method.test;

import com.mytutorials.design_patterns.template_method.ItalianHoagie;
import com.mytutorials.design_patterns.template_method.VeggieHoagie;

public class SandwichSculptor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ItalianHoagie cust12Hoagie = new ItalianHoagie();

		cust12Hoagie.makeSandwich();

		System.out.println();

		VeggieHoagie cust13Hoagie = new VeggieHoagie();

		cust13Hoagie.makeSandwich();

	}

}
