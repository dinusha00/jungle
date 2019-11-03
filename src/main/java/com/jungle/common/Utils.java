package com.jungle.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

	private final static Logger logger = LoggerFactory.getLogger(Utils.class);

	private static class InstanceHolder {
		public static Utils instance = new Utils();
	}

	private Utils() {
	}

	public static Utils getInstance() {
		return InstanceHolder.instance;
	}

	public String getAuditMessage(final String action, final String message) {
		logger.info("calling getAuditMessage action:{} message:{}", action, message);
		final String auditMessage = "[" + action + "] " + message;
		return auditMessage;
	}
}