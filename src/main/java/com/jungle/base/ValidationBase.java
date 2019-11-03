package com.jungle.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import inet.ipaddr.IPAddressString;

public class ValidationBase {

	private final static Logger logger = LoggerFactory.getLogger(ValidationBase.class);

	protected final boolean containsIp(final String network, final String address) {
		final IPAddressString parent = new IPAddressString(network);
		final IPAddressString child = new IPAddressString(address);
		final boolean result = parent.contains(child);
		logger.info(parent + " contains " + child + ": " + result);
		return result;
	}
}