package com.ejercicio.tres.spring.dao;
import com.ejercicio.tres.spring.entity.Cliente;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteDao extends CrudRepository<Cliente, Long>{
	
	
}
