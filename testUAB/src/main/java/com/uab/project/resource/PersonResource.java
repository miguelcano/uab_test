package com.uab.project.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uab.project.config.exception.ResourceException.ResponseCode;
import com.uab.project.model.responseDefault.ResponseDefault;
import com.uab.project.model.test.PersonModel;
import com.uab.project.model.test.dto.PersonDTO;
import com.uab.project.service.test.PersonService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("personas")
@Api(value = "Person", tags = {"Person"})
public class PersonResource {
	
	@Autowired
	private PersonService personService;
	
	
	@GetMapping("/name/{name}")
	public ResponseEntity<ResponseDefault> getName(@PathVariable("name") String name) {
		PersonModel personModel = personService.getByName(name);
		PersonDTO personDto = personService.getDto(personModel);
		ResponseDefault responseDefault = new ResponseDefault(personDto, ResponseCode.OK.getInternalCode(), "Persona encontrada.");
		return new ResponseEntity<ResponseDefault>(responseDefault, HttpStatus.valueOf(ResponseCode.OK.getHttpStatus()));
	}
	
	@GetMapping("/name")
	public ResponseEntity<ResponseDefault> getByName(@RequestHeader("name") String name) {
		PersonModel personModel = personService.getByName(name);
		PersonDTO personDto = personService.getDto(personModel);
		ResponseDefault responseDefault = new ResponseDefault(personDto, ResponseCode.OK.getInternalCode(), "Persona encontrada.");
		return new ResponseEntity<ResponseDefault>(responseDefault, HttpStatus.valueOf(ResponseCode.OK.getHttpStatus()));
	}
	
	@ApiOperation(value="Método para crear persona.", notes="Esta operación crea una persona.", response=PersonDTO.class)
	@ApiResponses(value= {
			@ApiResponse(code=201, message="Persona creada.", response=ResponseDefault.class),
			@ApiResponse(code=406, message="Problema con algunos campos.", response=ResponseDefault.class)
	})
	@PostMapping("/nuevo")
	public ResponseEntity<ResponseDefault> inserPerson(@RequestBody PersonDTO personDto) {
		PersonModel person = personService.validate(personDto);
		person = personService.create(person);
		personDto = personService.getDto(person);
		ResponseDefault responseDefault = new ResponseDefault(personDto, ResponseCode.CREATED.getInternalCode(), "Creado.");
		return new ResponseEntity<ResponseDefault>(responseDefault, HttpStatus.valueOf(ResponseCode.CREATED.getHttpStatus()));
		
	}
	
	@GetMapping("/lista")
	public ResponseEntity<ResponseDefault> listPerson() {
		List<PersonDTO> list = personService.listPerson();
		ResponseDefault responseDefault = new ResponseDefault(list, ResponseCode.OK.getInternalCode(), "Lista de persona.");
		return new ResponseEntity<ResponseDefault>(responseDefault, HttpStatus.valueOf(ResponseCode.OK.getHttpStatus()));
	}
}
