package com.zenjobs.test.rest;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.jsonpath.JsonPath;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
@Profile("test")
public class RecommendationControllerIntegrationTest {

	@LocalServerPort
	private int localPort;

	@Autowired
	private TestRestTemplate restTemplate;

	private static String url;
	private static HttpHeaders headers = new HttpHeaders();

	@Before
	public void setup() {
		// set initials
		url = String.format("%s%s%s", "http://localhost:", localPort, "/books/");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testRetrieveRecommendationList_ReactorExistCriteriaExist_return20Book() throws Exception {

		// passing an existing reactor Id
		url = url + "1";

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		log.info("\n url : {} \n entity: {} \n restTemplate: {}", url, entity, restTemplate);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		log.info("\n Test RetrieveRecommendationList {}", response);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		log.info("\n got: {}", response.getBody());

		int length = JsonPath.parse(response.getBody()).read("$.length()");

		assertEquals(length, 20);

	}
	@Test
	public void testRetrieveRecommendationList_ReactorExistCriteriaNotExist_return20Book() throws Exception {

		// passing an existing reactor Id
		url = url + "5";

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		log.info("\n url : {} \n entity: {} \n restTemplate: {}", url, entity, restTemplate);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		log.info("\n Test RetrieveRecommendationList {}", response);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		log.info("\n got: {}", response.getBody());

		int length = JsonPath.parse(response.getBody()).read("$.length()");

		assertEquals(length, 20);

	}

	@Test
	public void testRetrieveRecommendationList_ReactorNotExistCriteriaExist_throwsRecordNotFoundException() throws Exception {
		// passing an non-existing reactor Id
		url = url + "6";

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		log.info("\n url : {} \n entity: {} \n restTemplate: {}", url, entity, restTemplate);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		log.info("\n Test RetrieveRecommendationList - ReactorNotExist {}", response);

		String expected = "{\"message\":\"No User Found With ID: 6\",\"status\":\"NOT_FOUND\",\"path\":\"http://localhost:"
				+ localPort + "/books/6\"}";

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		log.info("\n expected : {} \n got: {}", expected, response.getBody());

		JSONAssert.assertEquals(expected, response.getBody(), false);

	}
}
