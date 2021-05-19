package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.dto.PersonListDto;
import com.example.demo.entities.PersonsEntity;
import com.example.demo.repositories.IPersonRepository;
import com.example.demo.services.PersonServices;


@SpringBootTest
public class PersonTest {
	
	@Autowired
	private PersonServices personServices;
	
	@MockBean
	private IPersonRepository personRepository;
	
	@Test
	public void testFindAllPersons() {
		Mockito.when(personRepository.findAll())
		.thenReturn(Stream
				.of(new PersonsEntity(1L, "N1", "F1"), new PersonsEntity(2L, "N2","F2"))
				.collect(Collectors.toList()));
		
		List<PersonsEntity> allPersons = personServices.findAllPersons();
		assertNotNull(allPersons);
		assertEquals(2, allPersons.size());
	}
	

	@Test
	public void testService1() {
		assertNull(personServices.service1(null, false));
		
		List<PersonsEntity> dataList = new ArrayList<PersonsEntity>();
		dataList.add(new PersonsEntity(null, "N1", "F1"));
		dataList.add(new PersonsEntity(null, "N2", "F1"));
		dataList.add(new PersonsEntity(null, "N3", "F1"));
		dataList.add(new PersonsEntity(null, "N4", "F1"));
		dataList.add(new PersonsEntity(null, "N5", ""));
		
		PersonListDto personsListDto = new PersonListDto(dataList, 2);
		List<List<PersonsEntity>> result = personServices.service1(personsListDto, false);
		assertEquals(2, result.size());
		result.forEach(list->{
			list.forEach(person->{
				System.out.println(person.getFirstName() + " " + person.getLastName());
			});
		});
	}
	
	@Test
	public void testService2() {
		assertNull(personServices.service1(null, false));
		
		List<PersonsEntity> dataList = new ArrayList<PersonsEntity>();
		dataList.add(new PersonsEntity(null, "N1", "F1", 10));
		dataList.add(new PersonsEntity(null, "N2", "F1", 20));
		dataList.add(new PersonsEntity(null, "N3", "F1", 50));
		dataList.add(new PersonsEntity(null, "N4", "F1", 5));
		dataList.add(new PersonsEntity(null, "N5", "",48));
		
		PersonListDto personsListDto = new PersonListDto(dataList, 2);
		List<PersonsEntity> result = personServices.service2(personsListDto, false);
		assertEquals(2, result.size());
		assertEquals(48, result.get(0).getAge());
		result.forEach(person->{
				System.out.println(person.getFirstName() + " " + person.getLastName());
		});
	}

}
