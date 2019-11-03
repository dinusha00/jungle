package com.jungle.common;

import java.util.ArrayList;
import java.util.List;

import com.jungle.base.ValidationServiceHolder;
import com.jungle.domain.entity.Audit;
import com.jungle.domain.entity.Ip;

public class ValidationServiceHolderImplTest implements ValidationServiceHolder {

	@Override
	public Audit write(final Audit audit) {
		return audit;
	}

	@Override
	public List<Ip> readIpsByType(final String type) {
		return new ArrayList<Ip>();
	}

	@Override
	public List<Ip> readIps() {
		return new ArrayList<Ip>();
	}
}