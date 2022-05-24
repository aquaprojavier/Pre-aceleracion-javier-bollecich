package com.disney.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.disney.api.dto.PersonajeBasicDto;
import com.disney.api.dto.PersonajeDto;
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
		return personajeMapper.personajeEntitySet2BasicDto(entities);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonajeEntity> findAll() {
		return personajeRepo.findAll();
	}

	@Override
	@Transactional
	public PersonajeDto save(PersonajeDto dto) {
		PersonajeEntity entity = personajeMapper.personajeDto2Entity(dto, true);
		PersonajeEntity entitySaved =  personajeRepo.save(entity);
		return personajeMapper.personajeEntity2Dto(entitySaved, true);
	}

	@Override
	@Transactional(readOnly = true)
	public PersonajeDto showCharacter(Long id) {
		PersonajeEntity characterEntity = personajeRepo.findById(id).orElse(null);
		return personajeMapper.personajeEntity2Dto(characterEntity, true);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		personajeRepo.deleteById(id);
	}

	@Override
	public PersonajeDto update(Long id, PersonajeDto dto) {
		PersonajeEntity personaje = personajeRepo.findById(id).orElse(null);
		personaje.setEdad(dto.getEdad());
		personaje.setNombre(dto.getNombre());
		personaje.setHistoria(dto.getHistoria());
		personaje.setImagen(dto.getImagen());
		personaje.setPeso(dto.getPeso());
		return personajeMapper.personajeEntity2Dto(personaje, true);
	}
}
