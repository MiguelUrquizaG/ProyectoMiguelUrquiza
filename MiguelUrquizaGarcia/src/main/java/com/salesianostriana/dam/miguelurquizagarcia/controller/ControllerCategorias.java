package com.salesianostriana.dam.miguelurquizagarcia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.salesianostriana.dam.miguelurquizagarcia.model.Categoria;
import com.salesianostriana.dam.miguelurquizagarcia.service.ServiceCategorias;

@Controller	
public class ControllerCategorias {

	@Autowired
	private ServiceCategorias categoriaServicios;
	
	@GetMapping("/categorias")
	public String showCategorias(Model model) {
		
		model.addAttribute("categoria", categoriaServicios.findAll());
		
		return "listaCategorias";
	}
	
	@GetMapping("/anadirCategoria")
	public String anadirCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "form-categoria";
	}
	
	@PostMapping("anadirCategoria/submit")
	public String procesoAnadirCategoria(@ModelAttribute("categoria") Categoria categoria) {
		
		categoriaServicios.save(categoria);
		return "redirect:/categorias";
	}
	
	@GetMapping("/editarCategoria/{id}")
	public String editarCategoria(@PathVariable Long id,Model model) {
		Categoria c =categoriaServicios.findById(id);
		model.addAttribute("categoria", c);
		return "form-modificar-categoria";
	}
	
	@PostMapping("/editarCategoria/submit")
	public String procesarCategorias(@ModelAttribute("categoria") Categoria categoria) {
		categoriaServicios.save(categoria);
		return "redirect:/categorias";
	}
	
}
