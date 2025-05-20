package com.salesianostriana.dam.miguelurquizagarcia.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesianostriana.dam.miguelurquizagarcia.model.Venta;

import com.salesianostriana.dam.miguelurquizagarcia.service.CategoriaService;
import com.salesianostriana.dam.miguelurquizagarcia.service.VentaService;
import com.salesianostriana.dam.miguelurquizagarcia.service.PrendaService;

@Controller
public class ControllerVenta {



	@Autowired
	private VentaService servicioVentas;
	@Autowired
	private CategoriaService serviceCategoria;
	
	@Autowired
	private PrendaService servicePrenda;
	



	
	@GetMapping("/ventas")
	public String ventas(Model model) {
		
		model.addAttribute("ventas", servicioVentas.findAll());
		
		return "ventas";
	}
	
	@GetMapping("/anadirVenta")
	public String anadirVenta( Model model) {
		model.addAttribute("nuevaVenta", new Venta());
		model.addAttribute("categoria", serviceCategoria.findAll());
		model.addAttribute("prenda", servicePrenda.findAll());;
		
		return "anadirVenta";
	}
	@PostMapping("/anadirVenta/submit")
	public String confirmarVenta(@ModelAttribute Venta v) {
		servicioVentas.saveVenta(v);
		return "ticket";
	}
	
	@DeleteMapping("/eliminarVenta/submit")
	public String eliminarVenta(@RequestParam Long id ) {
		servicioVentas.delete(servicioVentas.buscarVenta(id));
		return "redirect:/ventas";
	}
	@GetMapping("/ticket/{id}")
	public String verVenta(@PathVariable long id, Model model) {
		Venta v = servicioVentas.buscarVenta(id);
		model.addAttribute("venta", v);
		return "ticket";
	}
	
	@PostMapping("/calcular-total")
	@ResponseBody
	public double calcularTotalDesdeFront(@RequestBody Venta v) {
		return servicioVentas.calcularPrecioDescuento(v);
	}
}
