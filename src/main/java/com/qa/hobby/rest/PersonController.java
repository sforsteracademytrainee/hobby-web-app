package com.qa.hobby.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.hobby.persistence.domain.PersonDomain;
import com.qa.hobby.persistence.dto.PersonAltDTO;
import com.qa.hobby.persistence.dto.PersonDTO;
import com.qa.hobby.service.PersonService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {
	
	private PersonService service;
	
	// Create
	
	@PostMapping("/create")
	public ResponseEntity<PersonDTO> create(@RequestBody PersonDomain model) {
		return new ResponseEntity<>(service.create(model), HttpStatus.CREATED);
	}
	
	// Read
	
	@GetMapping("/read/{id}")
	public ResponseEntity<PersonDTO> read(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.read(id), HttpStatus.OK);
	}
	
	@GetMapping("/readVehicles/{id}")
	public ResponseEntity<PersonAltDTO> readVehicles(@PathVariable("id") Long id) {
		return new ResponseEntity<>(service.readVehicles(id), HttpStatus.OK);
	}
	
	@GetMapping("/readAll")
	public ResponseEntity<List<PersonDTO>> readAll() {
		return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
	}

	// Update
	
	@PutMapping("/update/{id}")
	public ResponseEntity<PersonDTO> update(@PathVariable("id") Long id, @RequestBody PersonDomain model) {
		return new ResponseEntity<PersonDTO>(this.service.update(id, model), HttpStatus.ACCEPTED);
	}
	
	// Delete
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<PersonDTO> delete(@PathVariable("id") Long id) {
		return this.service.delete(id) ?
				new ResponseEntity<>(HttpStatus.NO_CONTENT) :
				new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
