package com.jungle.common;

public enum Type {

	OUT_OF_RANGE("OUT OF RANGE"), PROD("PROD"), NON_PROD("NON PROD");

	private String value;

	Type(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}