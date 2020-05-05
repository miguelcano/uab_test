package com.uab.project.model.test;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.uab.project.model.test.dto.PersonDTO;
import com.uab.project.utils.DateUtils;

@Entity
@Table(name = "Person", schema = "dbo")
public class PersonModel {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "birthDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "active")
    private Boolean active;

	public PersonModel() {
		
	}
	public PersonModel(PersonDTO personDto) {
		this.id = personDto.getId();
		this.firstName = personDto.getFirstName();
		this.lastName = personDto.getLastName();
		this.birthDate = DateUtils.dateConvert(personDto.getBirthDate(), "dd/MM/yyyy");
		this.phone = personDto.getPhone();
		this.active = true;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
