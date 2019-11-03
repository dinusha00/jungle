package com.jungle.rule;

import java.util.ArrayList;
import java.util.List;

import com.jungle.base.ValidationRuleBase;
import com.jungle.base.ValidationServiceHolder;
import com.jungle.domain.entity.Request;

public class ValidationRule implements ValidationRuleBase {

	@Override
	public List<String> validate(final Request request, final ValidationServiceHolder validationWriter, final String message) {
		return new ArrayList<>();
	}
}