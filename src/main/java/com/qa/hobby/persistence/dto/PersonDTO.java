package com.qa.hobby.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
	
	private Long id;
	
	private String firstName;
	private String surname;
	private String address;
	private String phone;
	
}
