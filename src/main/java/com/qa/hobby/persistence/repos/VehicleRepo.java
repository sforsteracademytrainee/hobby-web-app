package com.qa.hobby.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.hobby.persistence.domain.VehicleDomain;

public interface VehicleRepo extends JpaRepository<VehicleDomain, Long> {
	
	// CRUD functionality for the VehicleDomain class
	
}
