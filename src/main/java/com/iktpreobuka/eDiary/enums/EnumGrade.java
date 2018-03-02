package com.iktpreobuka.eDiary.enums;

public enum EnumGrade {

	neocenjen(0),
	nedovoljan(1),
	dovoljan(2),
	dobar(3),
	vrlodobar(4),
	odlican(5);
	
	private final int value;
	
	private int getValue() {
		return value;
	}

	private EnumGrade(int value) {
		this.value = value;
	}
	
	
	
	
}
