package com.ejercicio.tres.spring.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ejercicio.tres.spring.service.ClienteService;
import com.ejercicio.tres.spring.entity.Cliente;


@RestController
@RequestMapping("/api")
public class ClienteController {
	
	@Autowired
	private ClienteService servicio;
	
	@GetMapping("/clientes")
	public List<Cliente> cliente(){
		return servicio.findAll();
	}
	
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> clienteShow(@PathVariable Long id){
		Cliente cliente = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			
			cliente = servicio.findById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje","Error al realizar consulta a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
		
	}
	
	
	@PostMapping("/clientes")
	public ResponseEntity<?> saveCliente(@RequestBody Cliente cliente) {
		Cliente clienteNew = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			
			clienteNew = servicio.save(cliente);
			
		} catch (DataAccessException e) {
			response.put("mensaje","Error al realizar insert a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El cliente ha sido creado con éxito!");
		response.put("cliente", clienteNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	

	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> updateCliente(@RequestBody Cliente cliente,@PathVariable Long id) {
		
		Cliente clienteActual= servicio.findById(id);
		Map<String,Object> response = new HashMap<>();
		
		try {
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setTelefono(cliente.getTelefono());
			
			servicio.save(clienteActual);
			
			
		} catch (DataAccessException e) {
			response.put("mensaje","Error al realizar actualizar a la base de datos");
			response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","El cliente ha sido actualizado con éxito!");
		response.put("cliente", clienteActual);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		 
	}
	
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
		Cliente clienteBorrado= null;
		Map<String,Object> response = new HashMap<>();
		clienteBorrado=servicio.findById(id);
		
		if(clienteBorrado == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}
		else {
			
			try {
				
			
				
				servicio.delete(id);
				
			} catch (DataAccessException e) {
				response.put("mensaje","Error al eliminar registro de base de datos");
				response.put("error", e.getMessage().concat("_ ").concat(e.getMostSpecificCause().getMessage()));
				
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
		response.put("mensaje","El cliente ha sido eliminado con éxito!");
		response.put("cliente", clienteBorrado);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
		
	}
	
	
	
	
	

}
