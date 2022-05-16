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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SQLDelete(sql= "UPDATE peliculas SET deleted = true WHERE id=?")// para aplicar el SoftDelete
@Where(clause = "deleted=false")
@Table(name = "peliculas")
public class PeliculaEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="pelicula_id")
	private Long id;
	
	private String imagen;
	
	private String titulo;
	
	private boolean deleted = Boolean.FALSE;
	
	@Column(name="fecha_creacion")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date fechaCreacion;
	
	private String calificacion;	
	
	@ManyToMany (cascade= {
			CascadeType.PERSIST,
			CascadeType.MERGE })
	@JoinTable (
			name="pelicula_personaje",
			joinColumns= @JoinColumn (name="pelicula_id"),
			inverseJoinColumns = @JoinColumn (name="personaje_id")
			)
	private Set<PersonajeEntity> personajes= new HashSet<>();
	
	@ManyToOne(fetch = FetchType.EAGER,// no es necesario, por defecto
			  cascade = { CascadeType.MERGE,
			              CascadeType.PERSIST})
	@JoinColumn
	private GeneroEntity genero;
	
	public void addPersonaje (PersonajeEntity personaje) {
		this.personajes.add(personaje);
	}
	
	public void delPersonaje (PersonajeEntity personaje) {
		for (PersonajeEntity character : personajes){
			if (character.getPersonajeId()== personaje.getPersonajeId()) {
				personajes.remove(personaje);
			}
		}
	}
	
	private static final long serialVersionUID = 1L;
	
}
