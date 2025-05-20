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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesianostriana.dam.miguelurquizagarcia.model.Prenda;
import com.salesianostriana.dam.miguelurquizagarcia.service.ServiceCategorias;
import com.salesianostriana.dam.miguelurquizagarcia.service.Services;

@Controller
@RequestMapping("/prendas")
public class ControllerPrenda {


	@Autowired
	private Services service;
	@Autowired
	private ServiceCategorias categoriaService;


	@GetMapping("")
	public String main(Model model) {

		List<Prenda> lista = service.findAll();

		lista = service.ordenarTopVentas(lista);

		model.addAttribute("prenda", lista);
		model.addAttribute("nuevaprenda", new Prenda());
		model.addAttribute("categoria", categoriaService.findAll());


		return "prendas";
	}

	@GetMapping("/anadirPrenda")
	public String anadirPrenda(Model model) {
		model.addAttribute("prenda", new Prenda());
		model.addAttribute("categoria", categoriaService.findAll());
		return "form";
	}

	@GetMapping("/editarPrenda/{id}")
	public String editarPrenda(@PathVariable long id, Model model) {
		Prenda p = service.buscarPrenda(id);
		model.addAttribute("prenda", p);
		model.addAttribute("categoria", categoriaService.findAll());
		return "form-modificar";
	}

	@PostMapping("/editarPrenda/modificar")
	public String procesarModificado(@ModelAttribute Prenda prenda) {
		service.edit(prenda);
		return "redirect:/prendas";
	}

	@DeleteMapping("/eliminarPrenda/submit")
	public String procesarEliminar(@RequestParam Long id,RedirectAttributes redirectAttributes) {
		Prenda p = service.buscarPrenda(id);
		p.removeFromCategoria(p.getCategoria());
		boolean eliminado = service.eliminarPrenda(id);
		
		 redirectAttributes.addFlashAttribute("eliminado", eliminado);

		return "redirect:/prendas";
	}

	@PostMapping("/anadirPrenda/submit")
	public String procesarPrenda(@ModelAttribute Prenda prenda) {
		prenda.addToCategoria(prenda.getCategoria());
		service.save(prenda);
		return "redirect:/prendas";
	}

	@GetMapping("/buscar")
	public String buscarPorNombre(@RequestParam String query, Model model) {
		List<Prenda> lista = service.findAll();

		lista = service.buscarPorNombre(query);

		model.addAttribute("prenda", lista);
		model.addAttribute("nuevaprenda", new Prenda());
		model.addAttribute("categoria", categoriaService.findAll());
		return "prendas";
	}
	@GetMapping("/ordenarPrendas")
	public String ordenarPrendas(@RequestParam(defaultValue = "asc") String orden, Model model) {
	    List<Prenda> lista;

	    if ("asc".equalsIgnoreCase(orden)) {
	        lista = service.findAllOrderByNombreAsc();
	    } else if ("desc".equalsIgnoreCase(orden)) {
	        lista = service.findAllOrderByNombreDesc();
	    } else {
	        lista = service.findAll();
	    }

	    model.addAttribute("prenda", lista);
	    model.addAttribute("nuevaprenda", new Prenda());
	    model.addAttribute("categoria", categoriaService.findAll());
	    model.addAttribute("orden", orden);

	    return "prendas";
	}
}
