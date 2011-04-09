package com.mytutorial.spring.dozer.entity.structure.two.enumeration;

public enum Gender {
	MALE("M"), FEMALE("F");

	String code;

	Gender(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static Gender getEnumFromString(String genderCode) {

		for (Gender gender : Gender.values()) {
			if (gender.getCode().equals(genderCode)) {
				return gender;
			}
		}

		return null;
	}

}
