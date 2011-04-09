package com.javaid.tutorials.oval.entity;

import java.util.Calendar;
import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CustomerTest {
	private Customer customer;
	private Validator validator;

	@Before
	public void setup() {
		customer = new Customer();
		validator = new Validator();
	}

	private void printErrors(String string, List<ConstraintViolation> errors) {
		if (errors.size() == 0) {
			return;
		}
		System.out.println(string);
		for (ConstraintViolation error : errors) {
			System.out.println("\t" + error.getErrorCode());
		}
	}

	@Test
	public void test1() {
		// empty object with NULL values
		List<ConstraintViolation> errors = validator.validate(customer);
		Assert.assertTrue(errors.size() == 0);
		printErrors("test1", errors);
	}

	@Test
	public void test2() {
		// break some rules
		// too short
		customer.setFirstName("A");
		// too long
		customer.setLastName("abcdefghijklmnoprstuwxyz");
		List<ConstraintViolation> errors = validator.validate(customer);
		Assert.assertTrue(errors.size() > 0);
		printErrors("test2", errors);
	}

	@Test @Ignore
	public void test3() {
		// break some rules
		customer.setPassword("doesNotHaveDigits");
		List<ConstraintViolation> errors = validator.validate(customer);
		Assert.assertTrue(errors.size() > 0);
		printErrors("test3", errors);
	}

	@Test
	public void test4() {
		// break some rules
		customer.setPassword("doesNotHaveDigits");
		customer.setPasswordVerification("differentPassword");
		List<ConstraintViolation> errors = validator.validate(customer);
		printErrors("test4", errors);
		Assert.assertTrue(errors.size() > 0);
	}

	@Test
	public void test5() {
		// break some rules
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -200);
		// customer is too old
		customer.setBirthDate(cal.getTime());
		List<ConstraintViolation> errors = validator.validate(customer);
		printErrors("test5", errors);
		Assert.assertTrue(errors.size() > 0);
	}

	@Test
	public void test6() {
		// break some rules
		// invalid URL
		customer.setHomePage("qwq");
		List<ConstraintViolation> errors = validator.validate(customer);
		printErrors("test6", errors);
		Assert.assertTrue(errors.size() > 0);
	}

	@Test @Ignore
	public void test7() {
		// break some rules
		// justAFlag mocks the validation of PESEL number in isValidPESEL method
		customer.setJustAFlag(false);
		List<ConstraintViolation> errors = validator.validate(customer);
		printErrors("test7", errors);
		Assert.assertTrue(errors.size() > 0);
	}
}
