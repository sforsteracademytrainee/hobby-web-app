package com.qa.hobby.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.hobby.persistence.domain.PersonDomain;
import com.qa.hobby.persistence.dto.PersonAltDTO;
import com.qa.hobby.persistence.dto.PersonDTO;
import com.qa.hobby.persistence.repos.PersonRepo;
import com.qa.hobby.utils.MyBeanUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PersonService {
	
	private PersonRepo repo;
	private ModelMapper mapper;
	
	private PersonDTO mapToDTO(PersonDomain model) {
		return this.mapper.map(model, PersonDTO.class);
	}
	
	private PersonAltDTO mapToAltDTO(PersonDomain model) {
		return this.mapper.map(model, PersonAltDTO.class);
	}
	
	// Create
	public PersonDTO create(PersonDomain model) {
		return this.mapToDTO(this.repo.save(model));
	}
	
	// Read
	public PersonDTO read(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow());
	}
	
	public PersonAltDTO readVehicles(Long id) {
		return this.mapToAltDTO(this.repo.findById(id).orElseThrow());
	}
	
	public List<PersonDTO> readAll() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	// Update
	public PersonDTO update(Long id, PersonDomain model) {
		PersonDomain updatedModel = this.repo.findById(id).orElseThrow();
		MyBeanUtils.mergeNotNull(model, updatedModel);
		return this.mapToDTO(this.repo.save(updatedModel));
	}
	
	// Delete
	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
}
