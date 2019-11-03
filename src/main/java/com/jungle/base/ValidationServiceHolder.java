package com.jungle.base;

import java.util.List;

import com.jungle.domain.entity.Audit;
import com.jungle.domain.entity.Ip;

public interface ValidationServiceHolder {
	Audit write(final Audit audit);

	List<Ip> readIpsByType(final String type);

	List<Ip> readIps();
}