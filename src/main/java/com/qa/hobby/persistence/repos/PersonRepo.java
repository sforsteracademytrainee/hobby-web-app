package com.qa.hobby.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.hobby.persistence.domain.PersonDomain;

public interface PersonRepo extends JpaRepository<PersonDomain, Long> {

	// CRUD functionality for PersonDomain class
	
}
