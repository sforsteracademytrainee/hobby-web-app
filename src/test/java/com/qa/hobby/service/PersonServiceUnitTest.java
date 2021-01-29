package com.qa.hobby.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.hobby.HobbyWebAppApplication;
import com.qa.hobby.persistence.domain.PersonDomain;
import com.qa.hobby.persistence.domain.VehicleDomain;
import com.qa.hobby.persistence.dto.PersonDTO;
import com.qa.hobby.persistence.repos.PersonRepo;

@SpringBootTest(classes = HobbyWebAppApplication.class)
public class PersonServiceUnitTest {
	
	@Autowired
	private PersonService service;
	
	@MockBean
	private PersonRepo repoMock;
	
	public void testConstructor() {
		
	}
	
	public void testMapToDTO(PersonDomain model) {
		
	}
	
	// CREATE
	@Test
	public void testCreate() { 
		// Rules
		Mockito.when(this.repoMock.save(Mockito.any(PersonDomain.class))).thenReturn(new PersonDomain());
		
		// Action
		PersonDTO result = this.service.create(new PersonDomain());
		
		// Assertions
		Assertions.assertThat(result).isEqualTo(new PersonDTO());
	
		// Verify
		Mockito.verify(this.repoMock, Mockito.times(1)).save(new PersonDomain());
	}
	
	// READ
	@Test
	public void testRead() {
		
		PersonDomain expected = new PersonDomain(1L, "Rupert", "Robinson", "32 Drive Way", "07 111 111 111", null);
		PersonDTO expectedDTO = new PersonDTO(1L, "Rupert", "Robinson", "32 Drive Way", "07 111 111 111");
		
		// Rules
		Mockito.when(this.repoMock.findById(Mockito.anyLong())).thenReturn(Optional.of(expected));
		
		// Action
		PersonDTO result = this.service.read(expected.getId());
		
		// Assertions
		Assertions.assertThat(result).isEqualTo(expectedDTO);
		
		// Verify
		Mockito.verify(this.repoMock, Mockito.times(1)).findById(Mockito.anyLong());
		
	}
	
	@Test
	public void testReadAll() {
		List<PersonDomain> expectedRaw = new ArrayList<PersonDomain>();
		expectedRaw.add(new PersonDomain(1L, "Rupert", "Robinson", "32 Drive Way", "07 111 111 111", null));
		expectedRaw.add(new PersonDomain(2L, "Tex", "Johnson", "17 Lane Road", "07 222 222 222", null));
		
		List<PersonDTO> expected = new ArrayList<PersonDTO>();
		expected.add(new PersonDTO(1L, "Rupert", "Robinson", "32 Drive Way", "07 111 111 111"));
		expected.add(new PersonDTO(2L, "Tex", "Johnson", "17 Lane Road", "07 222 222 222"));
		
		// Rules
		Mockito.when(this.repoMock.findAll()).thenReturn(expectedRaw);
		
		// Action
		List<PersonDTO> result = this.service.readAll();
		
		// Assertions
		Assertions.assertThat(result).isEqualTo(expected);
		
		// Verify
		Mockito.verify(this.repoMock, Mockito.times(1)).findAll();
	}
	
	// UPDATE
	@Test
	public void testUpdate() {
		final Long ID = Mockito.anyLong();
		
		PersonDomain expected = new PersonDomain(ID, "Patrick", "Star", "2 Spongebob Lane", "01", null);
		PersonDTO expectedDTO = new PersonDTO(ID, "Patrick", "Star", "2 Spongebob Lane", "01");
		
		// Rules
		Mockito.when(this.repoMock.findById(ID)).thenReturn(Optional.of(new PersonDomain(ID, "Patrick", "Star", "2 Spongebob Lane", "01", null)));
		Mockito.when(this.repoMock.save(expected)).thenReturn(expected);
		
		// Action
		PersonDTO result = this.service.update(ID, expected);
		
		// Assertions
		Assertions.assertThat(result).isEqualTo(expectedDTO);
		
		// Verify
		Mockito.verify(this.repoMock, Mockito.times(1)).findById(ID);
		Mockito.verify(this.repoMock, Mockito.times(1)).save(expected);
	}
	
	// DELETE
	@Test
	public void testDelete() {
		final Long ID = Mockito.anyLong();
		
		// Rules
		Mockito.when(this.repoMock.existsById(ID)).thenReturn(false);
		
		// Action
		Assertions.assertThat(this.service.delete(ID)).isTrue();
		
		// Verify
		Mockito.verify(this.repoMock, Mockito.times(1)).existsById(ID);
	}
}
