package com.disney.api.dto;

import java.util.Date;
import java.util.List;
import com.disney.api.entities.GeneroEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PeliculaDto {

	private Long id;

	private String imagen;

	private String titulo;

	private Date fechaCreacion;

	private String calificacion;
	
	private GeneroEntity genero;
	
	private List<PersonajeDto> personajes;

}
