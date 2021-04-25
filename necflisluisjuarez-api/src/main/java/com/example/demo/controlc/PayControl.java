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
import com.example.demo.entityc.Pago;
import com.example.demo.repositoryc.PayRepository;

//Se crea el controlador
@RestController
@RequestMapping(value = "pagos")
public class PayControl {
	
	@Autowired
	PayRepository repository;
	
	//Para poder mostrar la lista
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Pago> getListPagos(){
		 Iterable<Pago> listPagos = repository.findAll();
		return (Collection<Pago>) listPagos;
	}
	
	
	//Para poder mostrar un elemento en especifico de la lista
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Pago getOnePago(@PathVariable(name = "id") Long id) {
		Optional<Pago> pago = repository.findById(id);
		Pago last = null;
		if(pago.isPresent()) {
			last = pago.get();
		}
		return last;
	}
	
	//Para crer un nuevo elemento en la lista
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Pago addPago(@RequestBody Pago pago) {
		Pago newPago = repository.save(pago);
		return newPago;
	}
	
	//Para eleiminar un elemento de la lista
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void removePago(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}
	
	 //Para actualizar/ cambiar datos de un elemento
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Pago giveAndtakePago(@PathVariable(name = "id")Long id, 
			@RequestBody Pago pago) {
		Optional<Pago> oPago = repository.findById(id);
		if(oPago.isPresent()) {
			Pago latest = oPago.get();
			latest.setId(id);
			latest.setDate(pago.getDate());
			latest.setAmount(pago.getAmount());
			latest.setCardnum(pago.getCardnum());
			latest.setState(pago.getState());
			Pago giveAndtakedPago = repository.save(latest);
			return giveAndtakedPago;
		}
		return null;
	}
}