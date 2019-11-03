package com.jungle.rule;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jungle.base.ValidationBase;
import com.jungle.base.ValidationRuleBase;
import com.jungle.base.ValidationServiceHolder;
import com.jungle.common.Utils;
import com.jungle.domain.entity.Audit;
import com.jungle.domain.entity.Request;
import com.jungle.domain.vo.AuditType;

public class ValidationRule3 extends ValidationBase implements ValidationRuleBase {

	private final static Logger logger = LoggerFactory.getLogger(ValidationRule3.class);

	private final ValidationRuleBase validationRule;

	public ValidationRule3(final ValidationRuleBase validationRule) {
		this.validationRule = validationRule;
	}

	@Override
	public List<String> validate(final Request request, final ValidationServiceHolder validationWriter, final String message) {
		final List<String> validationErrors = validationRule.validate(request, validationWriter, message);
		logger.info("calling " + this.getClass().getSimpleName());
		final AuditType auditType;
		boolean validation = true;
		if (validation) {
			logger.info(this.getClass().getSimpleName() + " validationErrors [PASS]");
			auditType = AuditType.SUCCESS;
		} else {
			logger.info(this.getClass().getSimpleName() + " validationErrors [FAILED]");
			validationErrors.add(this.getClass().getSimpleName() + " violation");
			auditType = AuditType.FAILURE;
		}
		final String auditMessage = Utils.getInstance().getAuditMessage(auditType.name(), message + this.getClass().getSimpleName());
		final Audit audit = new Audit(request.getId(), new Date(), auditType.getVal(), auditMessage);
		validationWriter.write(audit);
		return validationErrors;
	}
}