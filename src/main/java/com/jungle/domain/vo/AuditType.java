package com.jungle.domain.vo;

public enum AuditType {
	SUCCESS(0), FAILURE(1);

	private int val;

	AuditType(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}
}