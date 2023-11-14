package com.example.demo.controlador;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.interfazService.IPersonaService;
import com.example.demo.model.Persona;

@Controller
@RequestMapping
public class Controlador {
	
	@Autowired
	private IPersonaService service;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Persona> personas = service.listar();
		model.addAttribute("personas", personas);
		return "index";//hace referencia a la pagina que recibe la solicitud, en este caso "index.html"
	}
	
	@GetMapping("/new")
	public String agregar(Model model){
		model.addAttribute("persona", new Persona());
		return "form";
	}
	@PostMapping("/save")
	public String save(@Valid Persona persona, Model model){
		service.save(persona);
		return "redirect:/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model model){
		Optional<Persona> persona = service.listarId(id);
		model.addAttribute("persona", persona);
		return "form";
	}
	
	@GetMapping("/eliminar/{id}")
	public String delete(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/listar";
	}
}
