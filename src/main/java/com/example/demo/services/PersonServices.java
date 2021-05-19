package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.demo.dto.PersonListDto;
import com.example.demo.entities.PersonsEntity;
import com.example.demo.repositories.IPersonRepository;

@Service
public class PersonServices {
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServices.class);
	@Autowired
	private IPersonRepository personRepository;
	
	public List<PersonsEntity> findAllPersons() {
		return personRepository.findAll();
	}
	
	public PersonsEntity findPersonById(Long id) {
		Optional<PersonsEntity> person = personRepository.findById(id);
		if(person.isPresent()) {
			return personRepository.findById(id).get();
		}
		else {
			return null;
		}
	}

	public List<List<PersonsEntity>> service1(PersonListDto personListDto, boolean persist) {
		if(personListDto != null && !CollectionUtils.isEmpty(personListDto.getPersonsEntities())){
				List<PersonsEntity> savedPersons = new ArrayList<PersonsEntity>(personListDto.getPersonsEntities().size());
		personListDto.getPersonsEntities().forEach(p->{
			if(!StringUtils.isEmpty(p.getLastName()) && !StringUtils.isEmpty(p.getFirstName())) {
				if(persist) {
					personRepository.save(p);
				}
				savedPersons.add(p);
			}
			else {
				LOGGER.error("Firstname or lastname of this person is empty");
			}
			});
			List<List<PersonsEntity>> personList = chunkList(savedPersons, personListDto.getSubListSize());
			return personList;
		}
		return null;
	
	}
	/**
	 * Le service doit pouvoir diviser (Split) la liste des Person en une seule liste de sous-listes dont la taille de chacune des sous-listes est égale au maximum à subListSize.
	 * @param list
	 * @param chunkSize
	 * @return
	 */
	public static  List<List<PersonsEntity>> chunkList(List<PersonsEntity> list, int chunkSize) {
	    if (chunkSize <= 0) {
	        throw new IllegalArgumentException("Invalid chunk size: " + chunkSize);
	    }
	    List<List<PersonsEntity>> chunkList = new ArrayList<>(list.size() / chunkSize);
	    for (int i = 0; i < list.size(); i += chunkSize) {
	        chunkList.add(list.subList(i, i + chunkSize >= list.size() ? list.size() : i + chunkSize));
	    }
	    return chunkList;
	}

	public List<PersonsEntity> service2(PersonListDto personsListDto, boolean persist) {
		if(personsListDto != null && !CollectionUtils.isEmpty(personsListDto.getPersonsEntities())) {
			List<PersonsEntity> filteredList = personsListDto.getPersonsEntities()
			.stream().filter(p->p.getAge() > 45)
			.collect(Collectors.toList());
			Collections.sort(filteredList, new SortbyAge());
			filteredList.forEach(p->{
				if(persist) {
					PersonsEntity savedPerson = personRepository.save(p);
					LOGGER.info("La personne : " + 
					(savedPerson.getId()) + 
					" " + savedPerson.getFirstName() 
					+" " +savedPerson.getLastName()
					+ " de "+ savedPerson.getAge() 
					+ "ans a été ajouté avec succès");
				}
			});
			
			return filteredList;
		}
		return null;
	}
	
	class SortbyAge implements Comparator<PersonsEntity>
	{
	    // Used for sorting in ascending order of age
		@Override
		public int compare(PersonsEntity person1, PersonsEntity person2) {
			return person1.getAge() - person2.getAge();
		}
	}

}
