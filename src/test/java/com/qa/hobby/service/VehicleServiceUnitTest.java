package com.qa.hobby.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.qa.hobby.HobbyWebAppApplication;
import com.qa.hobby.persistence.domain.VehicleDomain;
import com.qa.hobby.persistence.dto.VehicleDTO;
import com.qa.hobby.persistence.repos.VehicleRepo;

@SpringBootTest(classes = HobbyWebAppApplication.class)
public class VehicleServiceUnitTest {
	
	@Autowired
	private VehicleService service;
	 	
	@MockBean
	private VehicleRepo repoMock;
	
	public void testConstructor() {
	
	}
	
	public void testMapToDTO(VehicleDomain model) {
		
	}
	
	// CREATE
	@Test
	public void testCreate() { 
		// Rules
		Mockito.when(this.repoMock.save(Mockito.any(VehicleDomain.class))).thenReturn(new VehicleDomain());
		
		// Action
		VehicleDTO result = this.service.create(new VehicleDomain());
		
		// Assertions
		Assertions.assertThat(result).isEqualTo(new VehicleDTO());
	
		// Verify
		Mockito.verify(this.repoMock, Mockito.times(1)).save(new VehicleDomain());
	}
	
	// READ
	@Test
	public void testRead() {
		
		VehicleDomain expected = new VehicleDomain(1L, "AA11 AAA", "Ford", "Ka", null);
		VehicleDTO expectedDTO = new VehicleDTO(1L, "AA11 AAA", "Ford", "Ka");
		
		// Rules
		Mockito.when(this.repoMock.findById(Mockito.anyLong())).thenReturn(Optional.of(expected));
		
		// Action
		VehicleDTO result = this.service.read(expected.getId());
		
		// Assertions
		Assertions.assertThat(result).isEqualTo(expectedDTO);
		
		// Verify
		Mockito.verify(this.repoMock, Mockito.times(1)).findById(Mockito.anyLong());
		
	}
	
	@Test
	public void testReadAll() {
		List<VehicleDomain> expectedRaw = new ArrayList<VehicleDomain>();
		expectedRaw.add(new VehicleDomain(1L, "AA11 AAA", "Ford", "Ka", null));
		expectedRaw.add(new VehicleDomain(2L, "BB22 BBB", "Toyota", "Prius", null));
		expectedRaw.add(new VehicleDomain(3L, "CC33 CCC", "BMW", "Gran Tourer", null));
		
		List<VehicleDTO> expected = new ArrayList<VehicleDTO>();
		expected.add(new VehicleDTO(1L, "AA11 AAA", "Ford", "Ka"));
		expected.add(new VehicleDTO(2L, "BB22 BBB", "Toyota", "Prius"));
		expected.add(new VehicleDTO(3L, "CC33 CCC", "BMW", "Gran Tourer"));
		
		// Rules
		Mockito.when(this.repoMock.findAll()).thenReturn(expectedRaw);
		
		// Action
		List<VehicleDTO> result = this.service.readAll();
		
		// Assertions
		Assertions.assertThat(result).isEqualTo(expected);
		
		// Verify
		Mockito.verify(this.repoMock, Mockito.times(1)).findAll();
	}
	
	// UPDATE
	@Test
	public void testUpdate() {
		final Long ID = Mockito.anyLong();
		
		VehicleDomain expected = new VehicleDomain(ID, "AB12 AAA", "Forp", "Da", null);
		VehicleDTO expectedDTO = new VehicleDTO(ID, "AB12 AAA", "Forp", "Da");
		
		// Rules
		Mockito.when(this.repoMock.findById(ID)).thenReturn(Optional.of(new VehicleDomain(ID, "AC13 AAA", "Fork", "Ma", null)));
		Mockito.when(this.repoMock.save(expected)).thenReturn(expected);
		
		// Action
		VehicleDTO result = this.service.update(ID, expected);
		
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
