package com.disney.api.service;
import java.util.List;

import com.disney.api.entities.Personaje;

import dto.PersonajeDto;



public interface IPersonajeService {	
	
	public List <Personaje> findAll();
	
	public List <PersonajeDto> personajesDto(List <Personaje> personajes);
	
	public Personaje findById(Long id);
		
	public Personaje save (Personaje personaje);
	
	public void delete (Long id);
}
