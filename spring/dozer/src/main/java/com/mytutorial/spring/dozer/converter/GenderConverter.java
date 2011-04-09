package com.mytutorial.spring.dozer.converter;

import org.dozer.DozerConverter;

import com.mytutorial.spring.dozer.entity.structure.two.enumeration.Gender;

public class GenderConverter extends DozerConverter<String, Gender> {

	public GenderConverter(Class<String> prototypeA, Class<Gender> prototypeB) {
		super(prototypeA, prototypeB);
	}

	@Override
	public String convertFrom(Gender destination, String source) {

		return destination.getCode();
	}

	@Override
	public Gender convertTo(String source, Gender destination) {

		Gender gender = Gender.getEnumFromString(source);

		if (gender != null) {
			return gender;
		}

		throw new IllegalArgumentException("Could not find equivalent enum constant for gender " + source);
	}
}
