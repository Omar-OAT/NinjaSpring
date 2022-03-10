package com.project.ninjas.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
@Sql(scripts = { "classpath:ninja-schema.sql",
		"classpath:ninja-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class NinjaControllerIntegrationTest {

	@Autowired
	private MockMvc mvc; // performs requests for testing
	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {
		Ninja testNinja = new Ninja(null, "Kakashi", 33, "Hidden leaf village", "Chidori");
		String testNinjaAsJson = this.mapper.writeValueAsString(testNinja);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testNinjaAsJson);
		Ninja testCreatedNinja = new Ninja(3, "Kakashi", 33, "Hidden leaf village", "Chidori");
		String testCreatedNinjaAsJson = this.mapper.writeValueAsString(testCreatedNinja);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedNinjaAsJson);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void getAllTest() throws Exception {
		RequestBuilder req = get("/getAll");
		List<Ninja> testNinja = List.of(new Ninja(1, "Naruto", 17, "Hidden leaf", "Shadow clone jutsu"),
				new Ninja(2, "Gaara", 33, "Hidden sand", "Sand control jutsu"));
		String json = this.mapper.writeValueAsString(testNinja);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void getByIDTest() throws Exception {
		RequestBuilder req = get("/get/1");
		String NinjaAsJson = this.mapper.writeValueAsString(new Ninja(1, "Naruto", 17, "Hidden leaf", "Shadow clone jutsu"));
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(NinjaAsJson);
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void getByNameTest() throws Exception {

		RequestBuilder req = get("/getByName/Naruto");
		List<Ninja> testNinja = List.of(new Ninja(1, "Naruto", 17, "Hidden leaf", "Shadow clone jutsu"));
		String json = this.mapper.writeValueAsString(testNinja);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void getByVillageTest() throws Exception {

		RequestBuilder req = get("/getByVillage/Hidden leaf");
		List<Ninja> testNinja = List.of(new Ninja(1, "Naruto", 17, "Hidden leaf", "Shadow clone jutsu"));
		String json = this.mapper.writeValueAsString(testNinja);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);

		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);

	}
	@Test
	void getByAgeTest() throws Exception{
		
		RequestBuilder req = get("/getByAge/17");
		List<Ninja> testNinja = List.of(new Ninja(1,"Naruto", 17,"Hidden leaf","Shadow clone jutsu"));
		String json = this.mapper.writeValueAsString(testNinja);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
			
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
			
		}
	
	@Test
	void getByJutsuTest() throws Exception{
		
		RequestBuilder req = get("/getByJutsu/Shadow clone jutsu");
		List<Ninja> testNinja = List.of(new Ninja(1,"Naruto", 17,"Hidden leaf","Shadow clone jutsu"));
		String json = this.mapper.writeValueAsString(testNinja);
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
			
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
			
		}
	@Test
	void testReplace() throws Exception {
		
		Ninja testNinja = new Ninja(null,"Sasuke",19,"Hidden leaf","Sharingan");
		String testNinjaAsJSON = this.mapper.writeValueAsString(testNinja);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testNinjaAsJSON);
		
		Ninja testCreatedNinja = new Ninja(1,"Sasuke",19,"Hidden leaf","Sharingan");
		String testCreatedNinjaAsJSON= this.mapper.writeValueAsString(testCreatedNinja);
		ResultMatcher checkStatus = status().isAccepted(); 
		ResultMatcher checkBody = content().json(testCreatedNinjaAsJSON);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void TestDelete() throws Exception {
		
		this.mvc.perform(delete("/remove/1")).andExpect(status().isNoContent());
		
		
	}
}
