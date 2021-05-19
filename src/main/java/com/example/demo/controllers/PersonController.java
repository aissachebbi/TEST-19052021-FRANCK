package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PersonListDto;
import com.example.demo.entities.PersonsEntity;
import com.example.demo.services.PersonServices;

@RestController
public class PersonController {
	@Autowired
	private PersonServices personServices;
	
	@GetMapping("/persons")
	public List<PersonsEntity> findAllPersons(){
		List<PersonsEntity> dataList = new ArrayList<PersonsEntity>();
		dataList.add(new PersonsEntity(null, "N1", "F1"));
		dataList.add(new PersonsEntity(null, "N2", "F1"));
		dataList.add(new PersonsEntity(null, "N3", "F1"));
		dataList.add(new PersonsEntity(null, "N4", "F1"));
		dataList.add(new PersonsEntity(null, "N5", "F1"));
		
		PersonListDto personsListDto = new PersonListDto(dataList, 2);
		List<List<PersonsEntity>> result = personServices.service1(personsListDto,true);
		
		return personServices.findAllPersons();
	}
	
	@GetMapping("/persons/{id}")
	public PersonsEntity findAllPersons(@PathVariable Long id){
		return personServices.findPersonById(id);
	}
	
	@PostMapping(value ="savepersons")
	public List<PersonsEntity> savePersons(@RequestBody PersonListDto personListDto){
		return personServices.service2(personListDto, true);
	}
	

}
