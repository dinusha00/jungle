package com.jungle.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jungle.base.ServiceBase;
import com.jungle.domain.entity.Ip;
import com.jungle.domain.repository.IpRepository;

@Service
public class IpService extends ServiceBase {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IpRepository ipRepository;

	public List<Ip> readIpsByType(final String type) {
		logger.info("calling IpService.readIpsByType type{}", type);
		final List<Ip> ips = ipRepository.findByType(type);
		logger.info("returning from IpService.readIpsByType ips:{}", ips);
		return ips;
	}

	public Ip readIp(final Long id) {
		logger.info("calling IpService.readIp id:{}", id);
		final Ip ip = ipRepository.findOne(id);
		logger.info("returning from IpService.readIp ip:{}", ip);
		return ip;
	}

	public List<Ip> readIps() {
		logger.info("calling IpService.readIps");
		final List<Ip> ips = ipRepository.findAll();
		logger.info("returning from IpService.readIps ips:{}", ips);
		return ips;
	}
}