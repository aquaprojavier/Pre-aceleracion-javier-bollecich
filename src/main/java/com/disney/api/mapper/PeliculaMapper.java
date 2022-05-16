package com.disney.api.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.disney.api.dto.PeliculaBasicDto;
import com.disney.api.dto.PeliculaDto;
import com.disney.api.dto.PersonajeDto;
import com.disney.api.entities.PeliculaEntity;
import com.disney.api.entities.PersonajeEntity;

@Component
public class PeliculaMapper {
	
	@Autowired
	private PersonajeMapper personajeMapper;
	
	public PeliculaBasicDto peliculaEntity2BasicDto (PeliculaEntity entity) {
		PeliculaBasicDto dto = new PeliculaBasicDto();
		dto.setId(entity.getId());
		dto.setTitulo(entity.getTitulo());
		dto.setImagen(entity.getImagen());
		dto.setFechaCreacion(entity.getFechaCreacion());
		return dto;
		
	}

	public PeliculaEntity peliculaDto2Entity(PeliculaDto dto, boolean loadPersonajes) {
		PeliculaEntity peliculaEntity = new PeliculaEntity();
		peliculaEntity.setImagen(dto.getImagen());
		peliculaEntity.setTitulo(dto.getTitulo());
		peliculaEntity.setCalificacion(dto.getCalificacion());
		peliculaEntity.setGenero(dto.getGenero());
		if (loadPersonajes) {
			Set <PersonajeEntity> entities = personajeMapper.personajeDtoList2Entity(dto.getPersonajes(), false);
			peliculaEntity.setPersonajes(entities);
		}
		return peliculaEntity;
	}
	
	public PeliculaDto peliculaEntity2Dto(PeliculaEntity entity, boolean loadPersonajes) {
		PeliculaDto dto = new PeliculaDto();
		dto.setId(entity.getId());
		dto.setImagen(entity.getImagen());
		dto.setTitulo(entity.getTitulo());
		dto.setCalificacion(entity.getCalificacion());
		dto.setGenero(entity.getGenero());
		if (loadPersonajes) {
			List <PersonajeDto> personajesDto = personajeMapper.personajeEntitySet2Dto(entity.getPersonajes(), false);
			dto.setPersonajes(personajesDto);
		}		
		return dto;		
	}
	
	public Set<PeliculaEntity> peliculaDtoList2Entity (List<PeliculaDto> dtos){
		Set<PeliculaEntity>entities = new HashSet<PeliculaEntity>();
		for (PeliculaDto dto : dtos) {
			entities.add(peliculaDto2Entity(dto, false));
		}
		return entities;		
	}
	
	
	public List<PeliculaDto> peliculaEntitySet2Dto (Collection<PeliculaEntity> peliculas, boolean loadPersonajes){
		
		List <PeliculaDto> dtos = new ArrayList<PeliculaDto>();
		for (PeliculaEntity entity : peliculas) {
			dtos.add(peliculaEntity2Dto(entity, loadPersonajes));
		}		
		return dtos;
	}
	
	public List<PeliculaBasicDto> peliculaEntitySet2BasicDtos (Collection<PeliculaEntity> entities){
		
		List<PeliculaBasicDto> basicDtos = new ArrayList<PeliculaBasicDto>();
		for (PeliculaEntity entity : entities) {
			basicDtos.add(peliculaEntity2BasicDto(entity));
		}
		return basicDtos;
		
	}
	
}
