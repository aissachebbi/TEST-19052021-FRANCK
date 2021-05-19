package com.example.demo.dto;

import java.util.List;

import com.example.demo.entities.PersonsEntity;

public class PersonListDto {
	private List<PersonsEntity> personsEntities;
	private int subListSize ;
	public PersonListDto(List<PersonsEntity> personsEntities, int subListSize) {
		super();
		this.personsEntities = personsEntities;
		this.subListSize = subListSize;
	}
	public PersonListDto() {
		super();
	}
	public List<PersonsEntity> getPersonsEntities() {
		return personsEntities;
	}
	public void setPersonsEntities(List<PersonsEntity> personsEntities) {
		this.personsEntities = personsEntities;
	}
	public int getSubListSize() {
		return subListSize;
	}
	public void setSubListSize(int subListSize) {
		this.subListSize = subListSize;
	}
	
}
