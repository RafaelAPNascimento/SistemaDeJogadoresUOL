package com.uol.rest;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uol.exceptions.CodinomeIndisponivelException;
import com.uol.repository.JogadorRepository;
import com.uol.service.domain.Jogador;

@RestController
@RequestMapping("/jogador")
public class JogadorController {
	
	//como as regras de negócio são simples, optei por utilizar o repository diretamente aqui
	//se as regras fossem mais complexas, poderia ter criado uma camada de serviço adicional
	@Autowired
	private JogadorRepository jogadorRepository;
	
	//salva o jogador
	@PostMapping(value = "/save", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
					consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> save(@RequestBody Jogador jogador) {
						
		try {
			jogador = jogadorRepository.save(jogador);
			ResponseEntity<Jogador> response = new ResponseEntity<>(jogador, HttpStatus.OK);			
			return response;			
		} 
		catch (DataIntegrityViolationException e) {			
			ResponseEntity<String> response = new ResponseEntity<>("Codinome já utilizado", HttpStatus.BAD_REQUEST);			
			return response;
		}
		catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<>(e.getMessage(), 
													HttpStatus.INTERNAL_SERVER_ERROR);			
			return response;
		}
	}
	
	//atualiza o jopgador
	@PutMapping(value = "/update", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
							consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> update(@RequestBody Jogador jogador) {
		
		try {
			jogador = jogadorRepository.save(jogador);
			ResponseEntity<Jogador> response = new ResponseEntity<>(jogador, HttpStatus.OK);			
			return response;
		}
		catch (DataIntegrityViolationException e) {			
			ResponseEntity<String> response = new ResponseEntity<>("Codinome já utilizado", HttpStatus.BAD_REQUEST);			
			return response;
		}
		catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<>(e.getMessage(), 
													HttpStatus.INTERNAL_SERVER_ERROR);			
			return response;
		}				
	}
	
	@GetMapping(value = "/all")
	public List<Jogador> findAll() {
		
		return jogadorRepository.findAll();
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable final long id) {
		
		jogadorRepository.deleteById(id);
	}
	
	
}
