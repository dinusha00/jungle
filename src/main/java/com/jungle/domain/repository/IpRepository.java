package com.jungle.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jungle.domain.entity.Ip;

@Transactional
public interface IpRepository extends JpaRepository<Ip, Long> {

	public List<Ip> findByType(final String type);
}