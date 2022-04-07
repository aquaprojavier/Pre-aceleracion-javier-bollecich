package com.disney.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.disney.api.entities.Personaje;
import com.disney.api.service.IPersonajeService;

import dto.PersonajeDto;

@RestController
@RequestMapping("/characters")
public class PersonajeRestController {

	@Autowired
	private IPersonajeService personajeService;
	
	@GetMapping
	public List<PersonajeDto> index(){		
		return personajeService.personajesDto(personajeService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> mostrar(@PathVariable Long id) {
		Personaje personaje = null;
		Map<String, Object> response = new HashMap<>();

		try {
			personaje = personajeService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (personaje == null) {
			response.put("mensaje",
					"El personaje con el ID ".concat(id.toString().concat(" no existe en la base de datos!.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Personaje>(personaje, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> guardar(@Valid @RequestBody Personaje personaje, BindingResult result) {
		Personaje personajeNuevo = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {
			personajeNuevo = personajeService.save(personaje);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la inserción en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El personaje ha sido creado con éxito");
		response.put("personaje", personajeNuevo);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Personaje personaje, BindingResult result,
			@PathVariable Long id) {

		Personaje personajeActual = personajeService.findById(id);
		Personaje personajeActualizado = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (personajeActual == null) {
			response.put("mensaje", "Error: No se pudo editar el personaje con el ID "
					.concat(id.toString().concat(" no existe en la base de datos!.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			personajeActual.setImagen(personaje.getImagen());
			personajeActual.setNombre(personaje.getNombre());
			personajeActual.setEdad(personaje.getEdad());
			personajeActual.setHistoria(personaje.getHistoria());
			personajeActual.setPeso(personaje.getPeso());
			personajeActual.setPeliculas(personaje.getPeliculas());
			personajeActualizado = personajeService.save(personajeActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));

			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}

		response.put("mensaje", "El personaje ha sido editado con éxito");
		response.put("personaje", personajeActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			personajeService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar personaje.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El personaje ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
