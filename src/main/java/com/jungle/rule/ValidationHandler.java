package com.jungle.rule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jungle.base.ValidationRuleBase;
import com.jungle.base.ValidationServiceHolder;
import com.jungle.domain.entity.Request;
import com.jungle.domain.vo.Status;

public class ValidationHandler {

	private final static Logger logger = LoggerFactory.getLogger(ValidationHandler.class);

	private ValidationRuleBase validationRule;

	private static class InstanceHolder {
		public static ValidationHandler instance = new ValidationHandler();
	}

	private ValidationHandler() {
		validationRule = new ValidationRule3(new ValidationRule2(new ValidationRule1(new ValidationRule())));
	}

	public static ValidationHandler getInstance() {
		return InstanceHolder.instance;
	}

	public String validate(final Request request, final ValidationServiceHolder validationWriter, final String message) {
		logger.info("calling ValidationHandler.validate request:{} validationWriter:{} message:{}", request, validationWriter, message);
		final ValidationRuleBase validationRule0 = new ValidationRule0(new ValidationRule());
		final List<String> outOfRangeErrors = validationRule0.validate(request, validationWriter, message);
		if (outOfRangeErrors.isEmpty()) {
			final List<String> validationErrors = validationRule.validate(request, validationWriter, message);
			return (validationErrors.isEmpty()) ? Status.APPROVED.toString() : Status.REJECTED.toString();
		} else {
			return Status.REJECTED.toString();
		}
	}
}