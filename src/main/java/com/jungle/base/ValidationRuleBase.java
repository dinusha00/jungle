package com.jungle.base;

import com.jungle.domain.entity.Request;

import java.util.List;

public interface ValidationRuleBase {
	List<String> validate(final Request request, final ValidationServiceHolder validationWriter, final String message);
}