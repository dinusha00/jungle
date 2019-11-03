package com.jungle.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jungle.base.ServiceBase;
import com.jungle.base.ValidationServiceHolder;
import com.jungle.common.Utils;
import com.jungle.domain.entity.Audit;
import com.jungle.domain.entity.Request;
import com.jungle.domain.repository.RquestRepository;
import com.jungle.domain.vo.AuditType;
import com.jungle.domain.vo.Status;
import com.jungle.rule.ValidationHandler;
import com.jungle.rule.ValidationServiceHolderImpl;

@Service
public class RequestService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RquestRepository rquestRepository;

	@Autowired
	private AuditService auditService;

	@Autowired
	private IpService ipService;

	public List<Request> readRequests() {
		logger.info("calling RequestService.readRequests");
		final List<Request> requests = rquestRepository.findAll();
		logger.info("returning from RequestService.readRequests requests:{}", requests);
		return requests;
	}

	public Request readRequest(final Long id) {
		logger.info("calling RequestService.readRequest id:" + id);
		final Request request = rquestRepository.findOne(id);
		logger.info("returning from RequestService.readRequest request:{}", request);
		return request;
	}

	public Request createRequest(final Request requestParam) {
		logger.info("calling RequestService.createRequest requestParam:{}", requestParam);
		final Request request = new Request(requestParam.getSourceIp(), requestParam.getDestinationIp(), Status.NEW.toString());
		if (invalidContent(request)) {
			throw new IllegalArgumentException(msgRequestCannotBeEmpty);
		} else if (rquestRepository.findBySourceIpAndDestinationIp(request.getSourceIp(), request.getDestinationIp()) != null) {
			throw new IllegalArgumentException(msgRequestAlreadyExists);
		}
		final Request createdRequest = rquestRepository.save(request);
		final String auditMessage = Utils.getInstance().getAuditMessage(AuditType.SUCCESS.name(), msgAuditNew);
		final Audit audit = new Audit(createdRequest.getId(), new Date(), AuditType.SUCCESS.getVal(), auditMessage);
		auditService.createAudit(audit);
		logger.info("returning RequestService.createRequest createdRequest:{} audit:{}", createdRequest, audit);
		return createdRequest;
	}

	public Request updateRequest(final Request request) {
		logger.info("calling RequestService.updateRequest request:{}", request);
		if (invalidContent(request)) {
			throw new IllegalArgumentException(msgRequestCannotBeEmpty);
		}
		if (rquestRepository.findOne(request.getId()) == null) {
			throw new IllegalArgumentException(msgRequestDoesnotExists);
		}
		final Request updatedRequest = rquestRepository.save(request);
		logger.info("returning from RequestService.updateRequest updatedRequest:{}", updatedRequest);
		return updatedRequest;
	}

	public Request validateRequest(final Long id) {
		logger.info("calling RequestService.validateRequest id:" + id);
		final Request request = readRequest(id);
		if (Status.APPROVED.toString().equals(request.getStatus())) {
			throw new IllegalArgumentException(msgRequestAlreadyApproved);
		}
		final ValidationServiceHolder validationWriter = new ValidationServiceHolderImpl(auditService, ipService);
		final String result = ValidationHandler.getInstance().validate(request, validationWriter, msgAuditValidation);
		final AuditType auditType = Status.APPROVED.toString().equals(result) ? AuditType.SUCCESS : AuditType.FAILURE;
		final String auditMessage = Utils.getInstance().getAuditMessage(auditType.name(), msgAuditUpdate + " to [" + result + "]");
		final Audit audit = new Audit(request.getId(), new Date(), auditType.getVal(), auditMessage);
		validationWriter.write(audit);
		final Request validatedRequest = updateRequest(new Request(request.getId(), request.getSourceIp(), request.getDestinationIp(), result));
		logger.info("returning from RequestService.validateRequest validatedRequest:{} audit:{}", validatedRequest, audit);
		return validatedRequest;
	}

	public Request resetRequest(final Request request) {
		logger.info("calling RequestService.resetRequest request:{}", request);
		if (invalidContent(request)) {
			throw new IllegalArgumentException(msgRequestCannotBeEmpty);
		}
		if (rquestRepository.findOne(request.getId()) == null) {
			throw new IllegalArgumentException(msgRequestDoesnotExists);
		}
		final Request resetRequest = rquestRepository.save(request);
		final String auditMessage = Utils.getInstance().getAuditMessage(AuditType.SUCCESS.name(), msgAuditReset + " to [" + resetRequest.getStatus() + "]");
		final Audit audit = new Audit(resetRequest.getId(), new Date(), AuditType.SUCCESS.getVal(), auditMessage);
		auditService.createAudit(audit);
		logger.info("returning from RequestService.resetRequest resetRequest:{} audit:{}", resetRequest, audit);
		return resetRequest;
	}

	private boolean invalidContent(final Request request) {
		return request == null || null == request.getSourceIp() || request.getSourceIp().isEmpty() || null == request.getDestinationIp() || request.getDestinationIp().isEmpty()
				|| null == request.getStatus() || request.getStatus().isEmpty();
	}

	public void deleteRequest(final Long id) {
		logger.info("calling RequestService.deleteRequest id:{}", id);
		rquestRepository.delete(id);
	}
}