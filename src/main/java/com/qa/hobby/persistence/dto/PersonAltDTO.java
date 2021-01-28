package com.qa.hobby.persistence.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonAltDTO {
	
	private Long id;
	
	private String firstName;
	private String surname;
	private String address;
	private String phone;
	
	private List<VehicleDTO> vehicleList;
}
