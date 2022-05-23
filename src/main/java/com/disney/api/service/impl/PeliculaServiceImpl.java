package com.disney.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.api.dto.PeliculaBasicDto;
import com.disney.api.dto.PeliculaDto;
import com.disney.api.entities.PeliculaEntity;
import com.disney.api.mapper.PeliculaMapper;
import com.disney.api.repository.IPeliculaRepository;
import com.disney.api.repository.IPersonajeRepository;
import com.disney.api.service.IPeliculaService;

@Service
public class PeliculaServiceImpl implements IPeliculaService {

	@Autowired
	private PeliculaMapper peliculaMapper;

	@Autowired
	private IPeliculaRepository peliculaRepository;
	
	@Autowired
	private IPersonajeRepository characterRepository;

	public PeliculaDto save(PeliculaDto dto) {
		PeliculaEntity entity = peliculaMapper.peliculaDto2Entity(dto, true);
		PeliculaEntity entitySaved = peliculaRepository.save(entity);
		PeliculaDto result = peliculaMapper.peliculaEntity2Dto(entitySaved, false);
		return result;
	}

	public List<PeliculaDto> getAllPeliculas() {
		List<PeliculaEntity> entities = peliculaRepository.findAll();
		List<PeliculaDto> result = peliculaMapper.peliculaEntitySet2Dto(entities, false);
		return result;
	}

	public void delete(Long id) {
		peliculaRepository.deleteById(id);
	}

	public PeliculaDto update(Long id, PeliculaDto dto) {
		PeliculaEntity entity = peliculaRepository.getById(id);
		entity.setImagen(dto.getImagen());
		entity.setCalificacion(dto.getCalificacion());
		entity.setFechaCreacion(dto.getFechaCreacion());
		entity.setTitulo(dto.getTitulo());
		entity.setGenero(dto.getGenero());
		PeliculaEntity peliculaUpdated = peliculaRepository.save(entity);
		return peliculaMapper.peliculaEntity2Dto(peliculaUpdated, false);
	}

	public List<PeliculaBasicDto> getAllBasicPeliculas() {
		List<PeliculaEntity> entities = peliculaRepository.findAll();
		List<PeliculaBasicDto> basicDtos = peliculaMapper.peliculaEntitySet2BasicDtos(entities);
		return basicDtos;
	}

	@Override
	public PeliculaDto addCharToMovie(Long idMovie, Long idChar) {
		PeliculaEntity movie = peliculaRepository.getById(idMovie);		
		movie.addPersonaje(characterRepository.getById(idChar));
		PeliculaDto result = peliculaMapper.peliculaEntity2Dto(movie, true);
		return result;
	}

	@Override
	public void delCharFromMovie(Long idMovie, Long idChar) {
		PeliculaEntity movie = peliculaRepository.getById(idMovie);	
		movie.delPersonaje(characterRepository.getById(idChar));
	}

	@Override
	public PeliculaDto showMovie(Long id) {
		PeliculaEntity entity = peliculaRepository.findById(id).orElse(null);
		return peliculaMapper.peliculaEntity2Dto(entity, true);
	}
}
