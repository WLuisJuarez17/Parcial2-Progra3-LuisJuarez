package com.example.demo.repositoryc;


import org.springframework.data.repository.CrudRepository;


import com.example.demo.entityc.Cliente;

public interface ClientRepository extends CrudRepository<Cliente, Long>{

}
