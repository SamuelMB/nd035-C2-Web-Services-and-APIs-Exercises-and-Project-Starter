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
	public void shouldGetAllPrices() throws IOException {
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + port + "/services/price", String.class);
		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void shouldGetPriceByVehicleId() {
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:" + port + "/services/price/15", String.class);
		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
}
