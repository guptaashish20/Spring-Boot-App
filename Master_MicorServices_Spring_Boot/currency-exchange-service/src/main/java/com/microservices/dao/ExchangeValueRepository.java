package com.microservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.model.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{

	ExchangeValue findByFromAndTo(String from, String to);
}
