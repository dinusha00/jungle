package com.jungle.service;

import com.jungle.base.ServiceBase;
import com.jungle.domain.entity.Audit;
import com.jungle.domain.repository.AuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AuditRepository auditRepository;

	public List<Audit> readAuditsByRequestId(final Long requestId) {
		logger.info("calling AuditService.readAuditsByRequestId requestId{}", requestId);
		final List<Audit> audits = auditRepository.findByRequestId(requestId);
		logger.info("returning from AuditService.readAuditsByRequestId audits:{}", audits);
		return audits;
	}

	public List<Audit> readAuditsByRequestIdByType(final Long requestId, final int type) {
		logger.info("calling AuditService.readAuditsByRequestIdByType requestId:{} type:{}", requestId, type);
		final List<Audit> audits = auditRepository.findByRequestIdAndType(requestId, type);
		logger.info("returning from AuditService.readAuditsByRequestIdByType audits:{}", audits);
		return audits;
	}

	public Audit readAudit(final Long id) {
		logger.info("calling AuditService.readAudit id:{}", id);
		final Audit audit = auditRepository.findOne(id);
		logger.info("returning from AuditService.readAudit audit:{}", audit);
		return audit;
	}

	public Audit createAudit(final Audit auditParam) {
		logger.info("calling AuditService.createAudit auditParam:{}", auditParam);
		final Audit audit = new Audit(auditParam.getRequestId(), auditParam.getDate(), auditParam.getType(), auditParam.getMessage());
		if (invalidContent(audit)) {
			throw new IllegalArgumentException(msgAuditCannotBeEmpty);
		}
		final Audit createdAudit = auditRepository.save(audit);
		logger.info("returning AuditService.createAudit createdAudit:{}", createdAudit);
		return createdAudit;
	}

	private boolean invalidContent(final Audit audit) {
		return audit == null || null == audit.getRequestId() || null == audit.getMessage() || audit.getMessage().isEmpty();
	}

	public void deleteAudit(final Long id) {
		logger.info("calling AuditService.deleteAudit id:{}", id);
		auditRepository.delete(id);
	}
}