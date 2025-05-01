package com.salesianostriana.dam.miguelurquizagarcia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.miguelurquizagarcia.model.Prenda;
import com.salesianostriana.dam.miguelurquizagarcia.repository.PrendaRepository;
import com.salesianostriana.dam.miguelurquizagarcia.service.ServiceCategorias;
import com.salesianostriana.dam.miguelurquizagarcia.service.Services;

@Controller
public class ControllerPrenda {

    private final PrendaRepository prendaRepository;
	
   
	private Services service;
	 @Autowired
	private ServiceCategorias categoriaService;
	
	public ControllerPrenda (Services service, PrendaRepository prendaRepository) {
		this.service = service;
		this.prendaRepository = prendaRepository;
	}
	
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("prenda",service.findAll());
//		for(Prenda p : service.getList()) {
//			System.out.println(p.toString());
//		}
		
		return "main";
	}
	
	@GetMapping("/anadirPrenda")
	public String anadirPrenda(Model model) {
		model.addAttribute("prenda", new Prenda());
		model.addAttribute("categoria", categoriaService.findAll());
		return "form";
	}
	
	
	@GetMapping("/editarPrenda/{id}")
	public String editarPrenda(@PathVariable long id ,Model model) {
		Prenda p = service.findById(id);
		model.addAttribute("prenda", p);
		model.addAttribute("categoria", categoriaService.findAll());
		return "form-modificar";
	}
	
//	@GetMapping("/eliminarPrenda/{id}")
//	public String eliminarPrenda(@PathVariable long id, Model model) {
//		Prenda p = service.findById(id);
//		model.addAttribute("prenda", p);
//		return "delete";
//	}
	
	@PostMapping("/editarPrenda/modificar")
	public String procesarModificado(@ModelAttribute("prenda") Prenda prenda) {
		service.edit(prenda);
		System.out.println("Holaaa");
		return "redirect:/";
	}
	
	@DeleteMapping("/eliminarPrenda/{id}")
	public String procesarEliminar(@PathVariable long  id) {
		
		service.deleteById(id);
		
		return "main";
	}
	
	@PostMapping("/anadirPrenda/submit")
	public String procesarPrenda(@ModelAttribute("prenda")Prenda prenda) {
		service.save(prenda);
		return "redirect:/";
	}
}
