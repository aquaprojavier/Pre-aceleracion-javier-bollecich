package com.disney.api.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Genero implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(unique = true, nullable = false)
	private Long id;
	private String nombre;
	private String imagen;
	
	@OneToMany(mappedBy = "genero")
	private List<Pelicula> peliculas;


	private static final long serialVersionUID = 1L;

}
