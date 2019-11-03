package com.jungle.controller;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jungle.base.ServiceBaseTest;
import com.jungle.domain.entity.Request;
import com.jungle.domain.vo.Status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RequestControllerTest extends ServiceBaseTest {

	@Test
	public void testInvalidPath() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/request/invalid")).andExpect(status().is4xxClientError()).andExpect(content().string(""));
	}

	@Test
	public void testGetRequests() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/request")).andExpect(status().isOk()).andExpect(content().contentType(contentType))
				.andExpect(jsonPath("$.size()", greaterThan(3)));
	}

	@Test
	public void testRequest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/request/1")).andExpect(status().isOk()).andExpect(content().contentType(contentType)).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.sourceIp", is("10.236.10.0"))).andExpect(jsonPath("$.destinationIp", is("10.236.11.0")));
	}

	@Test
	public void testGetRequestNotAvailable() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/request/0")).andExpect(status().isOk()).andExpect(content().string(""));
	}

	@Test
	public void testAddRequest() throws Exception {
		final String requestJson = json(new Request(0L, "5.5.5.5", "55.55.55.55", Status.NEW.toString()));
		mvc.perform(MockMvcRequestBuilders.post("/request").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(requestJson)).andExpect(status().isCreated())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$.sourceIp", is("5.5.5.5"))).andExpect(jsonPath("$.destinationIp", is("55.55.55.55")));
	}

	@Test
	public void testAddRequestDuplicate() throws Exception {
		final String requestJson = json(new Request(0L, "6.6.6.6", "66.66.66.66", Status.NEW.toString()));
		mvc.perform(MockMvcRequestBuilders.post("/request").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(requestJson)).andExpect(status().isCreated())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$.sourceIp", is("6.6.6.6"))).andExpect(jsonPath("$.destinationIp", is("66.66.66.66")));

		mvc.perform(MockMvcRequestBuilders.post("/request").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(requestJson))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void testUpdateRequest() throws Exception {
		final String requestJson = json(new Request(2L, "7.7.7.7", "77.77.77.77", Status.NEW.toString()));
		mvc.perform(MockMvcRequestBuilders.put("/request").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(requestJson)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType)).andExpect(jsonPath("$.sourceIp", is("7.7.7.7"))).andExpect(jsonPath("$.destinationIp", is("77.77.77.77")));
	}

	@Test
	public void testUpdateRequestDuplicate() throws Exception {
		final String requestJson = json(new Request(4L, "1.1.1.1", "11.11.11.11", Status.NEW.toString()));
		mvc.perform(MockMvcRequestBuilders.put("/request").accept(MediaType.APPLICATION_JSON).contentType(contentType).content(requestJson)).andExpect(status().is5xxServerError());
	}

	@Test
	public void testDeleteRequest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/request/4").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testDeleteRequestDoesNotExists() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/request/0").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is5xxServerError());
	}

	@Ignore
	public void testRequestValidateAndReset() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/request/1/validate")).andExpect(status().isOk()).andExpect(content().contentType(contentType)).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.sourceIp", is("10.236.10.0"))).andExpect(jsonPath("$.destinationIp", is("10.236.10.0"))).andExpect(jsonPath("$.status", is("APPROVED")));

		mvc.perform(MockMvcRequestBuilders.get("/request/1/reset")).andExpect(status().isOk()).andExpect(content().contentType(contentType)).andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.sourceIp", is("1.1.1.1"))).andExpect(jsonPath("$.destinationIp", is("11.11.11.11"))).andExpect(jsonPath("$.status", is("NEW")));
	}
}
