package com.disney.api.service;

import java.util.List;

import com.disney.api.dto.PeliculaBasicDto;
import com.disney.api.dto.PeliculaDto;

public interface IPeliculaService {
	
	PeliculaDto showMovie (Long id);
	
	PeliculaDto save(PeliculaDto dto);

	List<PeliculaDto> getAllPeliculas();
	
	PeliculaDto addCharToMovie(Long idMovie, Long idChar);
	
	void delCharFromMovie(Long idMovie, Long idChar);
	
	List<PeliculaBasicDto> getAllBasicPeliculas();
	
	void delete (Long id);
	
	PeliculaDto update (Long id, PeliculaDto dto);
}
