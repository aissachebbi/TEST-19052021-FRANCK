package com.example.demo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dto.PersonListDto;
import com.example.demo.entities.PersonsEntity;
import com.example.demo.services.PersonServices;

@SpringBootApplication
public class Personlist1Application implements CommandLineRunner{

	@Autowired
	private PersonServices personServices;
	
	public static void main(String[] args) {
		SpringApplication.run(Personlist1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Service 1
		List<PersonsEntity> dataList = new ArrayList<PersonsEntity>();
		dataList.add(new PersonsEntity(null, "N1", "F1"));
		dataList.add(new PersonsEntity(null, "N2", "F1"));
		dataList.add(new PersonsEntity(null, "N3", "F1"));
		dataList.add(new PersonsEntity(null, "N4", "F1"));
		dataList.add(new PersonsEntity(null, "N5", ""));
		
		PersonListDto personsListDto = new PersonListDto(dataList, 2);
		List<List<PersonsEntity>> result = personServices.service1(personsListDto, true);
		
		result.forEach(list->{
			list.forEach(person->{
				System.out.println(person.getFirstName() + " " + person.getLastName());
			});
		});
		
		
		//Service 2
		List<PersonsEntity> dataList2 = new ArrayList<PersonsEntity>();
		dataList2.add(new PersonsEntity(null, "N10", "F10", 10));
		dataList2.add(new PersonsEntity(null, "N20", "F20", 20));
		dataList2.add(new PersonsEntity(null, "N30", "F30", 50));
		dataList2.add(new PersonsEntity(null, "N40", "F40", 5));
		dataList2.add(new PersonsEntity(null, "N50", "F50",48));
		
		PersonListDto personsListDto2 = new PersonListDto(dataList2, 2);
		List<PersonsEntity> result2 = personServices.service2(personsListDto2, true);
		result2.forEach(person->{
				System.out.println(person.getFirstName() + " " + person.getLastName());
		});
	
	}

}
