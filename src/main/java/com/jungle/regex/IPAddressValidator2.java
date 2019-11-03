package com.jungle.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPAddressValidator2 {
	private Pattern pattern;
	private Matcher matcher;

	private static final String IPADDRESS_PATTERN = "^" + "(10)" + "\\." + "(236)" + "\\." + "(1[6-9]|2[0-9]|3[0-1])" + "\\." + "(0)" + "$";

	public IPAddressValidator2() {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
	}

	public boolean validate(final String ip) {
		matcher = pattern.matcher(ip);
		return matcher.matches();
	}
}