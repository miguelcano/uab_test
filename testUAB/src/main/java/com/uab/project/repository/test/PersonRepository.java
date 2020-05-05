package com.uab.project.repository.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uab.project.model.test.PersonModel;

public interface PersonRepository extends JpaRepository<PersonModel, Integer> {

	@Query(value = "select * from person where firstName like ?1 order by firstName desc", nativeQuery = true)
	PersonModel getByName(String name);

	@Query(value = "select count(1) from person where firstName = ?1 and lastName = ?2", nativeQuery = true)
	Integer nameExists(String firstName, String lastName);

}
