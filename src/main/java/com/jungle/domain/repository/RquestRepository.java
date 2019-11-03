package com.jungle.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jungle.domain.entity.Request;

@Transactional
public interface RquestRepository extends JpaRepository<Request, Long> {

	public Request findBySourceIpAndDestinationIp(final String sourceIp, final String destinationip);
}