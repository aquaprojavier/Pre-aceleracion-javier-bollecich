package com.disney.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pelicula implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pelicula_id", unique = true, nullable = false)
	private Long id;
	private String imagen;
	
	@Column(length = 50)
	private String titulo;
	
	private Date fechaCreacion;
	
	private byte calificacion;
	
	@ManyToMany(mappedBy = "peliculas")
	private Set<Personaje> personajes= new HashSet<>();
	
	
	private static final long serialVersionUID = 1L;
	
}
