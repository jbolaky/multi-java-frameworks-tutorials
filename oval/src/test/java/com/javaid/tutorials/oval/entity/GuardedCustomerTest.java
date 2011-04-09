package com.javaid.tutorials.oval.entity;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

public class GuardedCustomerTest {

	@Test(expected=ConstraintsViolatedException.class)
	public void testGuardedAnnotationWorking(){
		GuardedCustomer guardedCustomer=new GuardedCustomer();
		guardedCustomer.setName(null);
	}
	
}
