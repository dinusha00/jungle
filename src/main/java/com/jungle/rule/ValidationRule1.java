package com.jungle.rule;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jungle.base.ValidationBase;
import com.jungle.base.ValidationRuleBase;
import com.jungle.base.ValidationServiceHolder;
import com.jungle.common.CommonConstants;
import com.jungle.common.RuleName;
import com.jungle.common.Type;
import com.jungle.common.Utils;
import com.jungle.domain.entity.Audit;
import com.jungle.domain.entity.Ip;
import com.jungle.domain.entity.Request;
import com.jungle.domain.vo.AuditType;

public class ValidationRule1 extends ValidationBase implements ValidationRuleBase {

	private final static Logger logger = LoggerFactory.getLogger(ValidationRule1.class);

	private final ValidationRuleBase validationRule;

	public ValidationRule1(final ValidationRuleBase validationRule) {
		this.validationRule = validationRule;
	}

	@Override
	public List<String> validate(final Request request, final ValidationServiceHolder validationServiceHolder, final String message) {
		final List<String> validationErrors = validationRule.validate(request, validationServiceHolder, message);
		logger.info("calling " + this.getClass().getSimpleName());
		AuditType auditType;
		String errorDetail = "";
		RuleName ruleName;

		// PROD to NON-PROD
		ruleName = RuleName.RULE1;
		final String sourceIpInProdList = getViolatedIps(request.getSourceIp(), validationServiceHolder, Type.PROD);
		final String destinationIpInNonProdList = getViolatedIps(request.getDestinationIp(), validationServiceHolder, Type.NON_PROD);
		if (sourceIpInProdList.isEmpty() != destinationIpInNonProdList.isEmpty()) {
			// PASS
			logger.info(this.getClass().getSimpleName() + " validationErrors [PASS]");
			auditType = AuditType.SUCCESS;
		} else {
			logger.info(this.getClass().getSimpleName() + " validationErrors [FAILED]");
			if (!sourceIpInProdList.isEmpty()) {
				errorDetail += (" (" + Type.PROD + ":" + sourceIpInProdList + ")");
			}
			if (!destinationIpInNonProdList.isEmpty()) {
				errorDetail += (" (" + Type.NON_PROD + ":" + destinationIpInNonProdList + ")");
			}
			validationErrors.add(CommonConstants.RULE + " " + ruleName.getValue() + " " + CommonConstants.VIOLATION + "." + errorDetail);
			auditType = AuditType.FAILURE;
		}

		final String auditMessage = Utils.getInstance().getAuditMessage(auditType.name(), message + ruleName.getValue() + errorDetail);
		final Audit audit = new Audit(request.getId(), new Date(), auditType.getVal(), auditMessage);
		validationServiceHolder.write(audit);
		return validationErrors;
	}

	private String getViolatedIps(final String requestIp, final ValidationServiceHolder validationServiceHolder, final Type type) {
		final List<Ip> prodIps = validationServiceHolder.readIpsByType(type.getValue());
		final StringBuilder violatedIps = new StringBuilder();
		prodIps.forEach((ip) -> {
			final String ipStr = ip.getIp().replaceAll("(\\[|\\]| )", "").split("/")[0].trim();
			final boolean containsIp = containsIp(ipStr, requestIp);
			if (containsIp) {
				violatedIps.append(ip.getIp() + "|");
			}
		});
		return violatedIps.toString();
	}
}