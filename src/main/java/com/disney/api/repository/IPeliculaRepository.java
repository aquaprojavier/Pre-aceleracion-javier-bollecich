package com.disney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.disney.api.entities.PeliculaEntity;

@Repository
public interface IPeliculaRepository extends JpaRepository<PeliculaEntity, Long>{
	
}
