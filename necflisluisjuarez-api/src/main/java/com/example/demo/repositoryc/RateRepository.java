package com.example.demo.repositoryc;

import org.springframework.data.repository.CrudRepository;




import com.example.demo.entityc.Tarifa;

public interface RateRepository extends CrudRepository<Tarifa, Long>{

}
