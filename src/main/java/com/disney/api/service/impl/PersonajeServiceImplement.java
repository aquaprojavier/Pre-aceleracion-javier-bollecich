package com.disney.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disney.api.dto.PersonajeBasicDto;
import com.disney.api.entities.PersonajeEntity;
import com.disney.api.mapper.PersonajeMapper;
import com.disney.api.repository.IPersonajeRepository;
import com.disney.api.service.IPersonajeService;

@Service
public class PersonajeServiceImplement implements IPersonajeService {

	@Autowired
	private IPersonajeRepository personajeRepo;

	@Autowired
	private PersonajeMapper personajeMapper;

	@Override
	public List<PersonajeBasicDto> listAllBasicPersonaje(){
		List<PersonajeEntity> entities = personajeRepo.findAll();
		List<PersonajeBasicDto> personajesBasicDto = personajeMapper.personajeEntitySet2BasicDto(entities);		
		return personajesBasicDto;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonajeEntity> findAll() {
		return (List<PersonajeEntity>) personajeRepo.findAll();
	}

	@Override
	@Transactional
	public PersonajeEntity save(PersonajeEntity personaje) {
		return personajeRepo.save(personaje);
	}

	@Override
	@Transactional(readOnly = true)
	public PersonajeEntity findById(Long id) {
		return personajeRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		personajeRepo.deleteById(id);
	}
}
