package com.disney.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.disney.api.dto.PeliculaBasicDto;
import com.disney.api.dto.PeliculaDto;
import com.disney.api.service.IPeliculaService;

@RestController
@RequestMapping("/movies")
public class PeliculaController {

	@Autowired
	private IPeliculaService peliculaService;

	//traer lista de péliculas básicas
	@GetMapping
	public ResponseEntity<List<PeliculaBasicDto>> getAllBasicMovies() {
		List<PeliculaBasicDto> peliculas = peliculaService.getAllBasicPeliculas();
		return ResponseEntity.ok().body(peliculas);
	}
	
	//traer una pelicula con todos los detalles y personajes asociados
	@GetMapping ("{idMovie}")
	public ResponseEntity<PeliculaDto> showMovie (@PathVariable (value = "idMovie") Long id){
		return ResponseEntity.ok().body(peliculaService.showMovie(id));
	}

	@PostMapping 
	public ResponseEntity<PeliculaDto> save(@RequestBody PeliculaDto dto) {
		PeliculaDto peliculaDtoSaved = peliculaService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(peliculaDtoSaved);
	}
	
	@PostMapping ("{idMovie}/characters/{idCharacter}")
	public ResponseEntity<PeliculaDto> addCharFromMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
		PeliculaDto peliculaSavedChar = peliculaService.addCharToMovie(idMovie, idCharacter);
		return ResponseEntity.status(HttpStatus.CREATED).body(peliculaSavedChar);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		peliculaService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping("{idMovie}/characters/{idCharacter}")
	public ResponseEntity<Void> delCharFromMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
		peliculaService.delCharFromMovie(idMovie, idCharacter);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<PeliculaDto> update (@PathVariable Long id, @RequestBody PeliculaDto dto){
		PeliculaDto response = peliculaService.update(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
