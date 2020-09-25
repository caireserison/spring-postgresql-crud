package com.example.spring.postgresql.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring.postgresql.model.ExampleModel;
import com.example.spring.postgresql.model.ExampleRepository;

@Component
public class ExampleService {
	
	@Autowired
	ExampleRepository exampleRepository;
	
	public Iterable<ExampleModel> getAll() {
		return exampleRepository.findAll();
	}
	
	public ExampleModel getById(Integer id) {
		Optional<ExampleModel> result = exampleRepository.findById(id);

		return result.orElse(null);
	}
	
	public ExampleModel include(ExampleModel model) {
		return saveModel(model);
	}
	
	public ExampleModel update(ExampleModel model) {
		return saveModel(model);
	}
	
	public ExampleModel increaseAge(Integer id) {
		Optional<ExampleModel> optResult = exampleRepository.findById(id);
		ExampleModel result = null;
		
		if (optResult.isPresent()) {
			result = optResult.get();
		} else {
			return null;
		}
		
		result.setAge(result.getAge() + 1);
		
		return saveModel(result);
	}
	
	private ExampleModel saveModel(ExampleModel model) {
		return exampleRepository.save(model);
	}
}
