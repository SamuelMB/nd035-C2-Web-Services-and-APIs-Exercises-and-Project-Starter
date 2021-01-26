package com.udacity.pricing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.pricing.domain.price.Price;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricingServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void shouldGetPriceByVehicleId() {
		ResponseEntity<Price> entity = restTemplate.getForEntity("http://localhost:" + port + "/services/price/15", Price.class);
		Price price = entity.getBody();
		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
		Assert.assertNotNull(price);
		Assert.assertEquals("USD", price.getCurrency());
		Assert.assertEquals(new BigDecimal("9830.83"), price.getPrice());
	}
}
