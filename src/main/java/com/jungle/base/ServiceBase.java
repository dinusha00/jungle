package com.jungle.base;

import org.springframework.beans.factory.annotation.Value;

public class ServiceBase {

	@Value("${request.cannotbe.empty}")
	protected String msgRequestCannotBeEmpty;

	@Value("${request.already.approved}")
	protected String msgRequestAlreadyApproved;

	@Value("${request.already.exists}")
	protected String msgRequestAlreadyExists;

	@Value("${request.doesnot.exists}")
	protected String msgRequestDoesnotExists;

	@Value("${audit.cannotbe.empty}")
	protected String msgAuditCannotBeEmpty;

	@Value("${audit.message.new}")
	protected String msgAuditNew;

	@Value("${audit.message.reset}")
	protected String msgAuditReset;

	@Value("${audit.message.update}")
	protected String msgAuditUpdate;

	@Value("${audit.message.validation}")
	protected String msgAuditValidation;
}