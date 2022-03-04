package com.project.ninjas.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.ninjas.domain.Ninja;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts= {"classpath:ninja-schema.sql", "classpath:ninja-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class NinjaControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc; // performs requests for testing
	@Autowired 
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception{
		Ninja testNinja = new Ninja(null,"Kakashi", 33,"Hidden leaf village", "Chidori");
		String testNinjaAsJson=this.mapper.writeValueAsString(testNinja);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testNinjaAsJson);
		Ninja testCreatedNinja = new Ninja(1, "Kakashi", 33, "Hidden leaf village", "Chidori");
		String testCreatedNinjaAsJson = this.mapper.writeValueAsString(testCreatedNinja);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedNinjaAsJson);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

}
