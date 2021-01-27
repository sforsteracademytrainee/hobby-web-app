package com.qa.hobby.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.hobby.HobbyWebAppApplication;
import com.qa.hobby.persistence.domain.VehicleDomain;
import com.qa.hobby.persistence.dto.VehicleDTO;

import net.bytebuddy.asm.Advice.This;

@SpringBootTest(classes = HobbyWebAppApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class VehicleControllerIntegrationTest {
	
	// TODO make sure all dependencies above are actually done
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private VehicleDTO mapToDTO(VehicleDomain model) {
		return this.mapper.map(model, VehicleDTO.class);
	}
	
	// TESTS
	
	// CREATE
	@Test
	public void testCreate() throws Exception { // NEED FIX
		
		VehicleDomain TEST_VEHICLEDOMAIN = new VehicleDomain("AA11 AAA", "Ford", "Ka");
		TEST_VEHICLEDOMAIN.setId(3L);
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/vehicle/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_VEHICLEDOMAIN))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(TEST_VEHICLEDOMAIN)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		
		// Perform & assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// READ
	@Test
	public void testRead() throws Exception {

		final Long ID = 1L;
//		VehicleDomain TEST_VEHICLEDOMAIN = new VehicleDomain("AA11 AAA", "Ford", "Ka");
		VehicleDTO TEST_VEHICLEDTO = new VehicleDTO(1L, "AA11 AAA", "Ford", "Ka");
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/vehicle/read/"+ID)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_VEHICLEDTO));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform & assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	@Test
	public void testReadAll() throws Exception{
		List<VehicleDTO> TEST_VEHICLES = new ArrayList<VehicleDTO>();
		TEST_VEHICLES.add(new VehicleDTO(1L, "AA11 AAA", "Ford", "Ka"));
		TEST_VEHICLES.add(new VehicleDTO(2L, "BB22 BBB", "Toyota", "Prius"));
		TEST_VEHICLES.add(new VehicleDTO(3L, "CC33 CCC", "BMW", "Gran Tourer"));
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/vehicle/readAll")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_VEHICLES));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		//Perform & assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// UPDATE
	@Test
	public void testUpdate() throws Exception {
		
		final Long ID = 3L;
		
		VehicleDomain TEST_VEHICLEDOMAIN = new VehicleDomain("DA11 AAA", "Fokd", "Ka");
		TEST_VEHICLEDOMAIN.setId(ID);
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/vehicle/update/"+ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_VEHICLEDOMAIN))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(TEST_VEHICLEDOMAIN)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		
		// Perform & assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// DELETE
	@Test
	public void testDelete() {
		final Long ID = 1L;
		
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/vehicle/delete/"+ID);
		
//		this.mock.perform(mockRequest)
//		.andExpect();
		
		// TODO
	}
	
}
