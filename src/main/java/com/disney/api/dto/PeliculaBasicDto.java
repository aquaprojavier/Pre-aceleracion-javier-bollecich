package com.disney.api.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaBasicDto {

	private Long id;

	private String imagen;

	private String titulo;

	private Date fechaCreacion;
}
