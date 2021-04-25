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
import com.example.demo.entityc.Cliente;
import com.example.demo.repositoryc.ClientRepository;

//Se crea el controlador
@RestController
@RequestMapping(value = "/clientes")
public class ClientControl {
	
	@Autowired
	ClientRepository repository;
	
	//Para poder mostrar la lista
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Cliente> getListClientes(){
		 Iterable<Cliente> listClientes = repository.findAll();
		return (Collection<Cliente>) listClientes;
	}
	
	//Para poder mostrar un elemento en especifico de la lista
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Cliente getOneCliente(@PathVariable(name = "id") Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		Cliente last = null;
		if(cliente.isPresent()) {
			last = cliente.get();
		}
		return last;
	}
	
	//Para crer un nuevo elemento en la lista
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cliente addCliente(@RequestBody Cliente cliente) {
		Cliente newCliente = repository.save(cliente);
		return newCliente;
	}
	
	//Para eleiminar un elemento de la lista
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void removeCliente(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}
	
	 //Para actualizar/ cambiar datos de un elemento
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Cliente giveAndtakeCliente(@PathVariable(name = "id")Long id, 
			@RequestBody Cliente cliente) {
		Optional<Cliente> oCliente = repository.findById(id);
		if(oCliente.isPresent()) {
			Cliente latest = oCliente.get();
			latest.setId(id);
			latest.setName(cliente.getName());
			latest.setAge(cliente.getAge());
			latest.setSex(cliente.getSex());
			latest.setCountry(cliente.getCountry());	
			latest.setcDate(cliente.getcDate());
			Cliente giveAndtakedCliente = repository.save(latest);
			return giveAndtakedCliente;
		}
		return null;
	}
}