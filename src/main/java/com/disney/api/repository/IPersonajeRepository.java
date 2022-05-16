package com.disney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.api.entities.PersonajeEntity;

@Repository
public interface IPersonajeRepository extends JpaRepository<PersonajeEntity, Long>{
	
}
