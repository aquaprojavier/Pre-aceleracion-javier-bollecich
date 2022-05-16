package com.disney.api.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "personajes")
public class PersonajeEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name= "personaje_id")
	private Long personajeId;
	
	private String imagen;
	
	@NotEmpty
	@Column(nullable = false, unique =true)
	private String nombre;
	
	private Integer edad;
	
	private Double peso;
	
	@NotEmpty
	private String historia;	
	
	@ManyToMany(mappedBy = "personajes")	
	private Set <PeliculaEntity> peliculas = new HashSet<>();

	
	private static final long serialVersionUID = 1L;

}
