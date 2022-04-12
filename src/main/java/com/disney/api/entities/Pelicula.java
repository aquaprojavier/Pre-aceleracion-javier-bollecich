package com.disney.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "peliculas")
public class Pelicula implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="pelicula_id", unique = true, nullable = false)
	private Long id;
	
	private String imagen;
	
	@Column(length = 100)
	private String titulo;
	
	@Column(name="fecha_creacion")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date fechaCreacion;
	
	private byte calificacion;	
	
	@ManyToOne(fetch = FetchType.EAGER,// no es necesario, por defecto
			  cascade = { CascadeType.MERGE,
			              CascadeType.PERSIST})
	@JoinColumn
	private Genero genero;
	
	
	@ManyToMany (cascade= {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable (
			name="pelicula-personaje",
			joinColumns= @JoinColumn (name="pelicula_id"),
			inverseJoinColumns = @JoinColumn (name="personaje_id")
			)
	private Set<Personaje> personajes= new HashSet<>();
	
	
	private static final long serialVersionUID = 1L;
	
}
