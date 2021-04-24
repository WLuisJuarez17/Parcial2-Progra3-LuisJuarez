package com.example.demo.controlc;
import java.util.Collection;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entityc.Tarifa;
import com.example.demo.repositoryc.RateRepository;

@RestController
@RequestMapping(value = "tarifas")
public class RateControl {
	
	@Autowired
	RateRepository repository;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Tarifa> getListaTarifas(){
		 Iterable<Tarifa> listaTarifas = repository.findAll();
		return (Collection<Tarifa>) listaTarifas;
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Tarifa getTarifa(@PathVariable(name = "id") Long id) {
		Optional<Tarifa> tarifa = repository.findById(id);
		Tarifa result = null;
		if(tarifa.isPresent()) {
			result = tarifa.get();
		}
		return result;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Tarifa createTarifa(@RequestBody Tarifa tarifa) {
		Tarifa nuevoTarifa = repository.save(tarifa);
		return nuevoTarifa;
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void deleteTarifa(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Tarifa updateTarifa(@PathVariable(name = "id")Long id, 
			@RequestBody Tarifa tarifa) {
		Optional<Tarifa> oTarifa = repository.findById(id);
		if(oTarifa.isPresent()) {
			Tarifa actual = oTarifa.get();
			actual.setId(id);
			actual.setName(tarifa.getName());
			actual.setDesc(tarifa.getDesc());
			actual.setPrice(tarifa.getPrice());
			actual.setcDate(tarifa.getcDate());
			Tarifa updatedRate = repository.save(actual);
			return updatedRate;
		}
		return null;
	}
}