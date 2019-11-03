package com.jungle.common;

import com.jungle.domain.entity.Request;
import com.jungle.domain.vo.Status;
import com.jungle.rule.ValidationHandler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidationHandlerTest {

	@Test
	public void testValidate() {
		final Request ipRequest = new Request(0L, "1.1.1.1", "11.11.11.11", Status.NEW.toString());
		final String result = ValidationHandler.getInstance().validate(ipRequest, new ValidationServiceHolderImplTest(), "TEST");
		assertEquals("validation failed to approve", Status.REJECTED.toString(), result);
	}
}