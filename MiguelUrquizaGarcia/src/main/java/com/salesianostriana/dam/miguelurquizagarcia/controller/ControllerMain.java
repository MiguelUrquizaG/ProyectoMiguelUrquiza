package com.salesianostriana.dam.miguelurquizagarcia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerMain {

	@GetMapping("/")
	public String inicio() {
		return "inicio";
	}
	
	@GetMapping("/quienesSomos")
	public String quienesSomos() {
		return "quienesSomos";
	}
	
}
