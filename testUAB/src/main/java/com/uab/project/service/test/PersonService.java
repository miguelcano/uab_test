package com.uab.project.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uab.project.config.exception.ResourceException;
import com.uab.project.config.exception.ResourceException.ResponseCode;
import com.uab.project.model.test.PersonModel;
import com.uab.project.model.test.dto.PersonDTO;
import com.uab.project.repository.test.PersonRepository;
import com.uab.project.utils.DateUtils;

import static com.uab.project.utils.CheckObject.isNull;
import static com.uab.project.utils.CheckObject.isNullOrEmpty;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public PersonModel create(PersonModel person) {
		person = personRepository.save(person);
		return person;
	}

	public PersonModel getByName(String name) {
		name = "%" + name.replace(" ", "%") + "%";
		PersonModel person = personRepository.getByName(name);
		return person;
	}
	
	public boolean nameExists(String firstName, String lastName) {
		Integer count = personRepository.nameExists(firstName, lastName);
		return count > 0;
	}
	
	public PersonModel validate(PersonDTO personDto, boolean create) {
		
		if (create && !isNull(personDto.getId()))
			throw new ResourceException(ResponseCode.BAD_PERSON, "Id debe ser nulo.");
		
		if (isNullOrEmpty(personDto.getFirstName()))
			throw new ResourceException(ResponseCode.BAD_PERSON_NAME, "Nombre es obligatório.");
		
		if (isNullOrEmpty(personDto.getLastName()))
			throw new ResourceException(ResponseCode.BAD_PERSON_NAME, "Apellido es obligatório.");
		
		if (nameExists(personDto.getFirstName(), personDto.getLastName()))
			throw new ResourceException(ResponseCode.BAD_PERSON_NAME, "El nombre " + personDto.getFirstName() + " " + personDto.getLastName() + " ya existe.");
		
		if (isNullOrEmpty(personDto.getBirthDate()))
			throw new ResourceException(ResponseCode.BAD_PERSON_BIRTHDATE, "Fecha de cumpleaños es obligatório.");
		
		if (isNull(DateUtils.dateConvert(personDto.getBirthDate(), "dd/MM/yyyy")))
			throw new ResourceException(ResponseCode.BAD_PERSON_BIRTHDATE, "Fecha en otro formato.");
		
		if (isNullOrEmpty(personDto.getPhone()))
			throw new ResourceException(ResponseCode.BAD_PERSON_PHONE, "Teléfono es obligatório.");
		
		PersonModel person = new PersonModel(personDto);
		return person;
	}
	
	public PersonDTO getDto(PersonModel person) {
		if(isNull(person))
			throw new ResourceException(ResponseCode.BAD_PERSON, "Persona no encontrada.");
		
		PersonDTO dto = new PersonDTO(person);
		
		return dto;
	}

	public List<PersonDTO> listPerson() {
		List<PersonDTO> listDTO = new ArrayList<PersonDTO>();
		
		List<Object[]> list = personRepository.listPerson();
		list.forEach(object->{
			PersonDTO p = new PersonDTO(object);
			listDTO.add(p);
		});
		return listDTO;
	}

}
