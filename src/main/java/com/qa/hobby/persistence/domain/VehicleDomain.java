package com.qa.hobby.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDomain {
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String registrationNumber;
	private String make;
	private String model;
	
	@ManyToOne
	private PersonDomain keeper;
	
	public VehicleDomain(String registrationNumber, String make, String model) {
		super();
		this.id = 0L;
		this.registrationNumber = registrationNumber;
		this.make = make;
		this.model = model;
		this.keeper = null;
	}
}
