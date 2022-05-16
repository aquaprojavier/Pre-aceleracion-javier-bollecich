package com.disney.api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonajeDto {

	private Long personajeId;

	private String imagen;

	private String nombre;

	private Integer edad;

	private Double peso;

	private String historia;

	private List<PeliculaDto> peliculas;
}
