package com.mytutorials.design_patterns.interpreter.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.mytutorials.design_patterns.interpreter.context.ConversionContext;
import com.mytutorials.design_patterns.interpreter.expression.AbstractExpression;

public class MeasurementConversion {

	private static final String PACKAGE_NAME = "com.mytutorials.design_patterns.interpreter.expression.impl.";

	public static void main(String[] args) {
		// Create pop up window that asks for a question

		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// String questionAsked =
		// JOptionPane.showInputDialog(frame,"What is your question");

		String questionAsked = "1 gallon to cups";

		// Add the question to the context for analysis

		ConversionContext question = new ConversionContext(questionAsked);

		String fromConversion = PACKAGE_NAME + question.getFromConversion();

		String toConversion = question.getToConversion();

		double quantity = question.getQuantity();

		try {

			// Get class based on the from conversion string

			Class tempClass = Class.forName(fromConversion);

			// Get the constructor dynamically for the conversion string

			Constructor con = tempClass.getConstructor();

			// Create a new instance of the object you want to convert from

			Object convertFrom = (AbstractExpression) con.newInstance();

			// Define the method parameters expected by the method that

			// will convert to your chosen unit of measure

			Class[] methodParams = new Class[] { Double.TYPE };

			// Get the method dynamically that will be needed to convert

			// into your chosen unit of measure

			Method conversionMethod = tempClass.getMethod(toConversion,
					methodParams);

			// Define the method parameters that will be passed to the above
			// method

			Object[] params = new Object[] { new Double(quantity) };

			// Get the quantity after the conversion

			String toQuantity = (String) conversionMethod.invoke(convertFrom,
					params);

			// Print the results

			String answerToQues = question.getResponse() +

			toQuantity + " " + toConversion;

			JOptionPane.showMessageDialog(null, answerToQues);

			// Closes the frame after OK is clicked

			frame.dispose();

		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (NoSuchMethodException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (SecurityException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (InstantiationException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IllegalAccessException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IllegalArgumentException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (InvocationTargetException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
	}

}
