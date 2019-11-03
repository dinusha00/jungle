package com.jungle.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPAddressValidator1 {
	private Pattern pattern;
	private Matcher matcher;

	private static final String IPADDRESS_PATTERN = "^" + "(10)" + "\\." + "(236)" + "\\." + "([0-9]|1[0-5])" + "\\." + "(2)" + "$";

	public IPAddressValidator1() {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
	}

	public boolean validate(final String ip) {
		matcher = pattern.matcher(ip);
		return matcher.matches();
	}
}