package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.controller.validator.PersonaValidator;
import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;



@Controller
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private PersonaValidator personaValidator;

	//prendiamo un oggetto persona dalla form
	@PostMapping("/persona")
	public String addPersona(@Valid @ModelAttribute("persona") Persona persona, BindingResult bindingResult, Model model) {
		this.personaValidator.validate(persona, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.personaService.save(persona);
			model.addAttribute("persona", persona);
			return "persona.html";
		}
		return "personaForm.html";
	}

	//intento di cancellare una persona
	@GetMapping("/wantToDeletePersona/{id}")
	public String wantDeletePersona(@PathVariable("id") Long id, Model model) {
		model.addAttribute("persona", this.personaService.findById(id));
		return "cancellaPersona.html";
	}

	//cancellare una persona
	@GetMapping("/toDeletePersona/{id}")
	public String toDeletePersona(@PathVariable("id") Long id, Model model) {
		this.personaService.delete(id);
		List<Persona> persone = this.personaService.findAll();
		model.addAttribute("persone", persone);
		return "persone.html";
	}

	//richiede tutte le persone
	@GetMapping("/persone")
	public String getPersone(Model model) {
		List<Persona> persone = this.personaService.findAll();
		model.addAttribute("persone", persone);
		return "persone.html";

	}

	@GetMapping("/persona/{id}")
	public String getPersona(@PathVariable("id") Long id, Model model) {
		Persona persona = this.personaService.findById(id);
		model.addAttribute("persona", persona);
		return "persona.html";
	}

	@GetMapping("/personaForm")
	public String getPersona(Model model) {
		model.addAttribute("persona", new Persona());
		return "personaForm.html";
	}

}
