package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;


@Service
public class PersonaService {

	@Autowired
	PersonaRepository personaRepository;

	@Transactional	//è un'operazione di scrittura
	public void  save (Persona persona) {
		this.personaRepository.save(persona);
	}
	
	@Transactional
	public void delete (Long id) {
		this.personaRepository.deleteById(id);
	}

	public Persona findById (Long id) {
		return personaRepository.findById(id).get();
	}

	public List<Persona> findAll(){
		List<Persona> persone = new ArrayList<Persona>();

		for(Persona p: this.personaRepository.findAll())
			persone.add(p);

		return persone;
	}
	
	public boolean alreadyExists(Persona persona) {
		return personaRepository.existsByNomeAndCognomeAndEta(persona.getNome(), persona.getCognome(), persona.getEta());
	}
}