package com.disney.api.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disney.api.entities.Personaje;
import com.disney.api.repository.IPersonajeRepo;

import dto.PersonajeDto;

@Service
public class PersonajeServiceImplement implements IPersonajeService {

	@Autowired
	private IPersonajeRepo personajeRepo;
	
	@Autowired
	private ModelMapper mapper;
	

	@Override
	public List<PersonajeDto> personajesDto(List<Personaje>personajes) {
		List <PersonajeDto> personajesDto = new ArrayList<>();
		for (Personaje personaje : personajes){
			personajesDto.add(mapper.map(personaje, PersonajeDto.class));
		}
		return personajesDto;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Personaje> findAll() {
		return (List<Personaje>) personajeRepo.findAll();
	}

	@Override
	public Personaje save(Personaje personaje) {
		return personajeRepo.save(personaje);
	}

	@Override
	public Personaje findById(Long id) {
		return personajeRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		personajeRepo.deleteById(id);
		
	}

}
