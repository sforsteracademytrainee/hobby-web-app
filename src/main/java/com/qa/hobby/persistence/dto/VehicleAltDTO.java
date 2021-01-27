package com.qa.hobby.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// used to return vehicle without keeper data for keeper lists

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAltDTO {
	
	private Long id;
	
	private String registrationNumber;
	private String model;
	private String make;
	
	private PersonDTO keeper;
}
