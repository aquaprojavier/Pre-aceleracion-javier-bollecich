package com.disney.api.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.disney.api.dto.PeliculaDto;
import com.disney.api.dto.PersonajeBasicDto;
import com.disney.api.dto.PersonajeDto;
import com.disney.api.entities.PersonajeEntity;
import com.disney.api.entities.PeliculaEntity;


@Component
public class PersonajeMapper {

	@Autowired
	private PeliculaMapper peliculaMapper;

	public PersonajeEntity personajeDto2Entity(PersonajeDto dto, boolean loadPeliculas) {
		PersonajeEntity entity = new PersonajeEntity();
		entity.setEdad(dto.getEdad());
		entity.setHistoria(dto.getHistoria());
		entity.setImagen(dto.getImagen());
		entity.setNombre(dto.getNombre());
		entity.setPeso(dto.getPeso());
		if (loadPeliculas) {
			Set<PeliculaEntity> entities = peliculaMapper.peliculaDtoList2Entity(dto.getPeliculas());
			entity.setPeliculas(entities);
		}
		return entity;
	}

	public PersonajeDto personajeEntity2Dto(PersonajeEntity entity, boolean loadPeliculas) {
		PersonajeDto dto = new PersonajeDto();
		dto.setPersonajeId(entity.getPersonajeId());
		dto.setEdad(entity.getEdad());
		dto.setHistoria(entity.getHistoria());
		dto.setNombre(entity.getNombre());
		dto.setPeso(entity.getPeso());
		dto.setImagen(entity.getImagen());
		if (loadPeliculas) {
			List<PeliculaDto> peliculasDto = peliculaMapper.peliculaEntitySet2Dto(entity.getPeliculas(), false);
			dto.setPeliculas(peliculasDto);
		}
		return dto;
	}

	public List<PersonajeDto> personajeEntitySet2Dto(Collection<PersonajeEntity> entities, boolean loadPeliculas) {
		List<PersonajeDto> dtos = new ArrayList<PersonajeDto>();
		for (PersonajeEntity entity : entities) {
			dtos.add(personajeEntity2Dto(entity, loadPeliculas));
		}
		return dtos;
	}

	public List<PersonajeBasicDto> personajeEntitySet2BasicDto(Collection<PersonajeEntity> entities) {
		List<PersonajeBasicDto> basicDtos = new ArrayList<PersonajeBasicDto>();

		for (PersonajeEntity entity : entities) {
			PersonajeBasicDto basicDto = new PersonajeBasicDto();
			basicDto.setImagen(entity.getImagen());
			basicDto.setNombre(entity.getNombre());
			basicDtos.add(basicDto);
		}
		return basicDtos;
	}
	
	public Set<PersonajeEntity> personajeDtoList2Entity (List<PersonajeDto> dtos, boolean loadPeliculas){
		Set<PersonajeEntity> entities = new HashSet<PersonajeEntity>();
		for (PersonajeDto dto : dtos) {
			entities.add(personajeDto2Entity(dto, loadPeliculas));
		}
		return entities;
	}

}
