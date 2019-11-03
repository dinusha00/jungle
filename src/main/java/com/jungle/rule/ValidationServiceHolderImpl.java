package com.jungle.rule;

import java.util.List;

import com.jungle.base.ValidationServiceHolder;
import com.jungle.domain.entity.Audit;
import com.jungle.domain.entity.Ip;
import com.jungle.service.AuditService;
import com.jungle.service.IpService;

public class ValidationServiceHolderImpl implements ValidationServiceHolder {

	private final AuditService auditService;

	private final IpService ipService;

	public ValidationServiceHolderImpl(final AuditService auditService, final IpService ipService) {
		this.auditService = auditService;
		this.ipService = ipService;
	}

	@Override
	public Audit write(final Audit audit) {
		return auditService.createAudit(audit);
	}

	@Override
	public List<Ip> readIpsByType(final String type) {
		return ipService.readIpsByType(type);
	}

	@Override
	public List<Ip> readIps() {
		return ipService.readIps();
	}
}