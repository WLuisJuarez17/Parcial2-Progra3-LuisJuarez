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

//Se crea el controlador
@RestController
@RequestMapping(value = "tarifas")
public class RateControl {
	
	@Autowired
	RateRepository repository;
	
	//Para poder mostrar la lista
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Tarifa> getListTarifas(){
		 Iterable<Tarifa> listTarifas = repository.findAll();
		return (Collection<Tarifa>) listTarifas;
	}
	
	
	//Para poder mostrar un elemento en especifico de la lista
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Tarifa getOneTarifa(@PathVariable(name = "id") Long id) {
		Optional<Tarifa> tarifa = repository.findById(id);
		Tarifa last = null;
		if(tarifa.isPresent()) {
			last = tarifa.get();
		}
		return last;
	}
	
	//Para crear un nuevo elemento en la lista
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Tarifa addTarifa(@RequestBody Tarifa tarifa) {
		Tarifa newTarifa = repository.save(tarifa);
		return newTarifa;
	}
	
	//Para eliminar un nuevo elemento en la lista
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void removeTarifa(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}
	
	 //Para actualizar/ cambiar datos de un elemento
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Tarifa giveAndtakeTarifa(@PathVariable(name = "id")Long id, 
			@RequestBody Tarifa tarifa) {
		Optional<Tarifa> oTarifa = repository.findById(id);
		if(oTarifa.isPresent()) {
			Tarifa latest = oTarifa.get();
			latest.setId(id);
			latest.setName(tarifa.getName());
			latest.setDesc(tarifa.getDesc());
			latest.setPrice(tarifa.getPrice());
			latest.setcDate(tarifa.getcDate());
			Tarifa giveAndtakedRate = repository.save(latest);
			return giveAndtakedRate;
		}
		return null;
	}
}