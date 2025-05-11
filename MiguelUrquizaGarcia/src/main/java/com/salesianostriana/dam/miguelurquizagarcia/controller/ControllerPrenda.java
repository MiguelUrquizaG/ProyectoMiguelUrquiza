package com.salesianostriana.dam.miguelurquizagarcia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.miguelurquizagarcia.model.Prenda;
import com.salesianostriana.dam.miguelurquizagarcia.repository.CategoriaRepository;
import com.salesianostriana.dam.miguelurquizagarcia.repository.PrendaRepository;
import com.salesianostriana.dam.miguelurquizagarcia.service.ServiceCategorias;
import com.salesianostriana.dam.miguelurquizagarcia.service.Services;

@Controller
public class ControllerPrenda {

    private final CategoriaRepository categoriaRepository;

    private final PrendaRepository prendaRepository;
	
   
	private Services service;
	 @Autowired
	private ServiceCategorias categoriaService;
	
	public ControllerPrenda (Services service, PrendaRepository prendaRepository, CategoriaRepository categoriaRepository) {
		this.service = service;
		this.prendaRepository = prendaRepository;
		this.categoriaRepository = categoriaRepository;
	}
	
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("prenda",service.findAll());
		model.addAttribute("nuevaprenda", new Prenda());
		model.addAttribute("categoria", categoriaService.findAll());
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
		System.out.println("Entro");
		model.addAttribute("categoria", categoriaService.findAll());
		return "form-modificar";
	}
	
	
	@PostMapping("/editarPrenda/modificar")
	public String procesarModificado(@ModelAttribute Prenda prenda) {
		service.edit(prenda);
		System.out.println("Holaaa");
		return "redirect:/";
	}
	
//	@DeleteMapping("/eliminarPrenda/{id}")
//	public String procesarEliminar(@PathVariable Long id) {
//			System.out.println(id);
//			service.deleteById(id);
//		
//		return "redirect:/";
//	}
	
	@DeleteMapping("/eliminarPrenda/submit")
	public String procesarEliminar(@RequestParam Long id) {
			Prenda p = service.findById(id);
			p.removeFromCategoria(p.getCategoria());
			service.deleteById(id);
		
		return "redirect:/";
	}
	
	@PostMapping("/anadirPrenda/submit")
	public String procesarPrenda(@ModelAttribute Prenda prenda) {
		prenda.addToCategoria(prenda.getCategoria());
		service.save(prenda);
		return "redirect:/";
	}
}
