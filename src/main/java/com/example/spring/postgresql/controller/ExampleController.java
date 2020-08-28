package com.example.spring.postgresql.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.spring.postgresql.model.ExampleModel;
import com.example.spring.postgresql.service.ExampleService;

@RestController
public class ExampleController {
	
	@Autowired
	ExampleService exampleService;
	
	@GetMapping(path = "/people")
	public ResponseEntity<Iterable<ExampleModel>> getAllInfo() {
		Iterable<ExampleModel> response;
		
		try {
			response = exampleService.getAll();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}
	
	@GetMapping(path = "/people/id/{id}")
	public ResponseEntity<ExampleModel> getOneInfo(@PathVariable("id") @NotNull Integer id) {
		ExampleModel response;
		
		try {
			response = exampleService.getById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}
	
	@PostMapping(path = "/people")
	public ResponseEntity<ExampleModel> includeInfo(@RequestBody ExampleModel model) {
		ExampleModel response;
		
		try {
			response = exampleService.include(model);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}
	
	@PutMapping(path = "/people")
	public ResponseEntity<ExampleModel> updateAllInfo(@RequestBody ExampleModel model) {
		ExampleModel response;
		
		try {
			response = exampleService.update(model);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}
	
	@PatchMapping(path = "/people/id/{id}/add-year-old")
	public ResponseEntity<ExampleModel> addOneYearOld(@PathVariable("id") @NotNull Integer id) {
		ExampleModel response;
		
		try {
			response = exampleService.increaseAge(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
		}
	}
}
