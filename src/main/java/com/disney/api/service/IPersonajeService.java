package com.disney.api.service;
import java.util.List;

import com.disney.api.dto.PersonajeBasicDto;
import com.disney.api.entities.PersonajeEntity;

public interface IPersonajeService {	
	
	public List <PersonajeEntity> findAll();
	
	public List <PersonajeBasicDto> listAllBasicPersonaje();
	
	public PersonajeEntity findById(Long id);
		
	public PersonajeEntity save (PersonajeEntity personaje);
	
	public void delete (Long id);
}
