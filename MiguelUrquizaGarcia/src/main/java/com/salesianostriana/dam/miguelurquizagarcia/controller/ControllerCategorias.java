package com.salesianostriana.dam.miguelurquizagarcia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.miguelurquizagarcia.model.Categoria;
import com.salesianostriana.dam.miguelurquizagarcia.service.ServiceCategorias;

@Controller	
public class ControllerCategorias {

	@Autowired
	private ServiceCategorias categoriaServicios;
	
	@GetMapping("/categorias")
	public String showCategorias(Model model) {
		
		model.addAttribute("categoria", categoriaServicios.findAll());
		model.addAttribute("nuevacategoria", new Categoria());
		
		return "listaCategorias";
	}
	
	@GetMapping("/anadirCategoria")
	public String anadirCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "form-categoria";
	}
	
	@PostMapping("anadirCategoria/submit")
	public String procesoAnadirCategoria(@ModelAttribute Categoria categoria) {
		
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
	public String procesarCategorias(@ModelAttribute Categoria categoria) {
		categoriaServicios.save(categoria);
		return "redirect:/categorias";
	}
	
	@DeleteMapping("/eliminarCategoria/submit")
	public String eliminarCategoria(@RequestParam Long id) {
		System.out.println(id);
		categoriaServicios.delete(id);
		return "redirect:/categorias";
	}
	
	
	@GetMapping("/mostrarCategoria/{id}")
	public String mostrarCategoria(@PathVariable Long id, Model model) {
		Categoria c = categoriaServicios.findById(id);
		model.addAttribute("prendasCategoria", c.getListaPrendas());
		return "mostrar-categoria";
	}
	
	@GetMapping("/buscarCategoria")
	public String buscarCategoria(@RequestParam String query, Model model) {
		List<Categoria>lista = categoriaServicios.findAll();
		lista =  categoriaServicios.buscarPorNombre(query);

		model.addAttribute("categoria", lista);
		model.addAttribute("nuevacategoria", new Categoria());
		return "listaCategorias";
	}
	
}
