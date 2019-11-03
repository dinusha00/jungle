package com.jungle.controller;

import com.jungle.domain.entity.Audit;
import com.jungle.exception.JungleException;
import com.jungle.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit")
public class AuditController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuditService auditService;

	@GetMapping(value = "/{id}")
	public Audit readAudit(@PathVariable final Long id) {
		try {
			logger.info("calling AuditController.readAudit id:{}", id);
			final Audit audit = auditService.readAudit(id);
			logger.info("returning from AuditController.readAudit audit:{}", audit);
			return audit;
		} catch (final Exception e) {
			throw new JungleException(e);
		}
	}

	@GetMapping(value = "/request/{requestId}")
	public List<Audit> readAuditsByRequest(@PathVariable final Long requestId) {
		try {
			logger.info("calling AuditController.readAuditsByRequest requestId:{}", requestId);
			final List<Audit> audits = auditService.readAuditsByRequestId(requestId);
			logger.info("returning from AuditController.readAuditsByRequest audit:{}", audits);
			return audits;
		} catch (final Exception e) {
			throw new JungleException(e);
		}
	}

	@GetMapping(value = "/request/{requestId}/type/{type}")
	public List<Audit> readAuditsByRequestByType(@PathVariable final Long requestId, @PathVariable final int type) {
		try {
			logger.info("calling AuditController.readAuditsByRequestByType requestId:{} type:{}", requestId, type);
			final List<Audit> audits = auditService.readAuditsByRequestIdByType(requestId, type);
			logger.info("returning from AuditController.readAuditsByRequestByType audit:{}", audits);
			return audits;
		} catch (final Exception e) {
			throw new JungleException(e);
		}
	}
}