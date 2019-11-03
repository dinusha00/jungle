package com.jungle.regex;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IPAddressValidatorTest {

	private IPAddressValidator1 ipAddressValidator1;
	private IPAddressValidator2 ipAddressValidator2;

	@BeforeClass
	public void initData() {
		ipAddressValidator1 = new IPAddressValidator1();
		ipAddressValidator2 = new IPAddressValidator2();
	}

	@Test
	public void ValidIPAddressTest1() {
		String ip = "10.236.1.2";
		boolean valid = ipAddressValidator1.validate(ip);
		System.out.println("IPAddress is valid : " + ip + " , " + valid);
		Assert.assertEquals(true, valid);
	}

	@Test
	public void ValidIPAddressTest2() {
		String ip = "10.236.17.0";
		boolean valid = ipAddressValidator2.validate(ip);
		System.out.println("IPAddress is valid : " + ip + " , " + valid);
		Assert.assertEquals(true, valid);
	}
}