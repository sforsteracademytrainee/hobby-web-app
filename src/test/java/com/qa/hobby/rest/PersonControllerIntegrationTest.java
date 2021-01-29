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
import com.qa.hobby.persistence.domain.PersonDomain;
import com.qa.hobby.persistence.dto.PersonAltDTO;
import com.qa.hobby.persistence.dto.PersonDTO;
import com.qa.hobby.persistence.dto.VehicleDTO;

@SpringBootTest(classes = HobbyWebAppApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema-test.sql", "classpath:data-test.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class PersonControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ObjectMapper jsonifier;
	
	private PersonDTO mapToDTO(PersonDomain model) {
		return this.mapper.map(model, PersonDTO.class);
	}
	
	// TESTS
	
	// CREATE
	@Test
	public void testCreate() throws Exception {
		
		PersonDomain TEST_DOMAIN = new PersonDomain("Patrick", "Star", "2 Spongebob Lane", "01");
		TEST_DOMAIN.setId(3L);
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.POST, "/person/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_DOMAIN))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(TEST_DOMAIN)));
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
		PersonDTO TEST_DTO = new PersonDTO(ID, "Rupert", "Robinson", "32 Drive Way", "07 111 111 111");
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/person/read/"+ID)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_DTO));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform & assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	@Test
	public void testReadVehicles() throws Exception {
		final Long ID = 1L;
		List<VehicleDTO> TEST_VEHICLES = new ArrayList<VehicleDTO>();
		TEST_VEHICLES.add(new VehicleDTO(1L, "AA11 AAA", "Ford", "Ka"));
		TEST_VEHICLES.add(new VehicleDTO(3L, "CC33 CCC", "BMW", "Gran Tourer"));
		PersonAltDTO TEST_DTO = new PersonAltDTO(ID, "Rupert", "Robinson", "32 Drive Way", "07 111 111 111", TEST_VEHICLES);
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.GET, "/person/readVehicles/"+ID)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_DTO));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		// Perform & assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	@Test
	public void testReadAll() throws Exception {
		List<PersonDTO> TEST_DTOS = new ArrayList<PersonDTO>();
		TEST_DTOS.add(new PersonDTO(1L, "Rupert", "Robinson", "32 Drive Way", "07 111 111 111"));
		TEST_DTOS.add(new PersonDTO(2L, "Tex", "Johnson", "17 Lane Road", "07 222 222 222"));
		
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.GET, "/person/readAll")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(TEST_DTOS));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		
		//Perform & assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// UPDATE
	@Test
	public void testUpdate() throws Exception {
		
		final Long ID = 2L;
		
		PersonDomain TEST_DOMAIN = new PersonDomain("Patrick", "Star", "2 Spongebob Lane", "01");
		TEST_DOMAIN.setId(ID);
		// Prepared REST request
		MockHttpServletRequestBuilder mockRequest = 
				MockMvcRequestBuilders.request(HttpMethod.PUT, "/person/update/"+ID)
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(TEST_DOMAIN))
				.accept(MediaType.APPLICATION_JSON);
		
		// Assertion checks
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(mapToDTO(TEST_DOMAIN)));
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		
		// Perform & assert
		this.mock.perform(mockRequest)
		.andExpect(matchStatus)
		.andExpect(matchContent);
	}
	
	// DELETE
	@Test
	public void testDelete() throws Exception {
		final Long ID = 1L;
		
		MockHttpServletRequestBuilder mockRequest =
				MockMvcRequestBuilders.request(HttpMethod.DELETE, "/person/delete/"+ID);
		
		this.mock.perform(mockRequest)
		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
}
