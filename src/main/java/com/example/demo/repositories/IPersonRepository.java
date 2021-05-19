package com.example.demo.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.PersonsEntity;

public interface IPersonRepository extends JpaRepository<PersonsEntity, Long>{
	
}
