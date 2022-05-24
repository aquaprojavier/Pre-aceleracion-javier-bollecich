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

import com.disney.api.dto.PersonajeBasicDto;
import com.disney.api.dto.PersonajeDto;
import com.disney.api.service.IPersonajeService;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

	@Autowired
	private IPersonajeService personajeService;

	@GetMapping
	public ResponseEntity<List<PersonajeBasicDto>> index() {
		List<PersonajeBasicDto> response = personajeService.listAllBasicPersonaje();
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PersonajeDto> showCharacter(@PathVariable Long id) {
		PersonajeDto personajeDto = personajeService.showCharacter(id);
		return ResponseEntity.ok().body(personajeDto);
	}

	@PostMapping
	public ResponseEntity<PersonajeDto> saveCharacter(@RequestBody PersonajeDto dto) {
		PersonajeDto characterSaved = personajeService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PersonajeDto> update(@PathVariable Long id, @RequestBody PersonajeDto dto) {
		PersonajeDto response = personajeService.update(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
		personajeService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
