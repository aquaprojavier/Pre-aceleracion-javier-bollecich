package com.disney.api.service;
import java.util.List;

import com.disney.api.dto.PersonajeBasicDto;
import com.disney.api.dto.PersonajeDto;
import com.disney.api.entities.PersonajeEntity;

public interface IPersonajeService {	
	
	public List <PersonajeEntity> findAll();
	
	public List <PersonajeBasicDto> listAllBasicPersonaje();
	
	public PersonajeDto showCharacter(Long id);
		
	public PersonajeDto save (PersonajeDto dto);
	
	public void delete (Long id);
}
