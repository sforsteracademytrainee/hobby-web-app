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

import com.qa.hobby.persistence.domain.VehicleDomain;
import com.qa.hobby.persistence.dto.VehicleAltDTO;
import com.qa.hobby.persistence.dto.VehicleDTO;
import com.qa.hobby.service.VehicleService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/vehicle")
@AllArgsConstructor
public class VehicleController {
	
		private VehicleService service;
		
		// Create
		
		@PostMapping("/create")
		public ResponseEntity<VehicleDTO> create(@RequestBody VehicleDomain model) {
			return new ResponseEntity<>(service.create(model), HttpStatus.CREATED);
		}
		
		// Read
		
		@GetMapping("/read/{id}")
		public ResponseEntity<VehicleDTO> read(@PathVariable("id") Long id) {
			return new ResponseEntity<>(service.read(id), HttpStatus.OK);
		}
		
		@GetMapping("/readKeeper/{id}")
		public ResponseEntity<VehicleAltDTO> readKeeper(@PathVariable("id") Long id) {
			return new ResponseEntity<>(service.readKeeper(id), HttpStatus.OK);
		}
		
		@GetMapping("/readAll")
		public ResponseEntity<List<VehicleDTO>> readAll() {
			return new ResponseEntity<>(service.readAll(), HttpStatus.OK);
		}
		
		// Update
		
		@PutMapping("/update/{id}")
		public ResponseEntity<VehicleDTO> update(@PathVariable("id") Long id, @RequestBody VehicleDomain model) {
			return new ResponseEntity<VehicleDTO>(this.service.update(id, model), HttpStatus.ACCEPTED);
		}
		
		// Delete
		
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<VehicleDTO> delete(@PathVariable("id") Long id) {
			return this.service.delete(id) ?
					new ResponseEntity<>(HttpStatus.NO_CONTENT) :
					new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}