package com.disney.api.service;
import java.util.List;

import com.disney.api.dto.PersonajeBasicDto;
import com.disney.api.dto.PersonajeDto;
import com.disney.api.entities.PersonajeEntity;

public interface IPersonajeService {	
	
	List <PersonajeEntity> findAll();
	
	List <PersonajeBasicDto> listAllBasicPersonaje();
	
	PersonajeDto showCharacter(Long id);
		
	PersonajeDto save (PersonajeDto dto);
	
	void delete (Long id);
	
	PersonajeDto update(Long id, PersonajeDto dto);
}
