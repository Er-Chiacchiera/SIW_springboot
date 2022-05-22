package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Persona;

public interface PersonaRepository extends CrudRepository<Persona,Long> {
	
	public boolean existsByNomeAndCognomeAndEta(String nome, String cognome, Integer eta);

}