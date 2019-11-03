package com.jungle.common;

public enum RuleName {

	RULE0("OUT OF RANGE"), RULE1("PROD TO NON-PROD"), RULE2("NON-PROD TO PROD");

	private String value;

	RuleName(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}