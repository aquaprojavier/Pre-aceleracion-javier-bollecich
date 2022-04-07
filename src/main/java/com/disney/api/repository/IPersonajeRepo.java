package com.disney.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.disney.api.entities.Personaje;

public interface IPersonajeRepo extends CrudRepository<Personaje, Long>{
	

}
