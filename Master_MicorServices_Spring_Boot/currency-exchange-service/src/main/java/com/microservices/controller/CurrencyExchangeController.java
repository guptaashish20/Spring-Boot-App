package com.microservices.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.dao.ExchangeValueRepository;
import com.microservices.model.ExchangeValue;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to)	{
		
		// ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));
		/* Hibernate: select exchangeva0_.id as id1_0_, exchangeva0_.conversion_multiple as conversi2_0_, exchangeva0_.currency_from as currency3_0_, exchangeva0_.port as port4_0_, exchangeva0_.currency_to as currency5_0_ from exchange_value exchangeva0_ where exchangeva0_.currency_from=? and exchangeva0_.currency_to=? */

		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}
}
