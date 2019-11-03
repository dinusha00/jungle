package com.jungle.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jungle.domain.entity.Audit;

@Transactional
public interface AuditRepository extends JpaRepository<Audit, Long> {

	public List<Audit> findByRequestId(final Long requestId);

	public List<Audit> findByRequestIdAndType(final Long requestId, final int type);
}