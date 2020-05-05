package com.uab.project.model.test.dto;

import java.util.Date;

import com.uab.project.model.test.PersonModel;
import com.uab.project.utils.DateUtils;

public class PersonDTO {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String phone;
	
	public PersonDTO(){
	}

	public PersonDTO(PersonModel person) {
		this.id = person.getId();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.birthDate = DateUtils.dateConvert(person.getBirthDate(), "dd/MM/yyyy");
		this.phone = person.getPhone();
	}
	
	public PersonDTO(Object[] o) {
		this.id = (Integer) o[0];
		this.firstName = (String) o[1];
		this.lastName = (String) o[2];
		this.birthDate = DateUtils.dateConvert((Date) o[3], "dd/MM/yyyy");
		this.phone = (String) o[4];
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
