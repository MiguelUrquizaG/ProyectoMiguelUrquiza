package com.salesianostriana.dam.miguelurquizagarcia.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.miguelurquizagarcia.model.LineaVenta;
import com.salesianostriana.dam.miguelurquizagarcia.model.Venta;
import com.salesianostriana.dam.miguelurquizagarcia.service.ServiceCategorias;
import com.salesianostriana.dam.miguelurquizagarcia.service.ServiceVenta;
import com.salesianostriana.dam.miguelurquizagarcia.service.Services;

@Controller
public class ControllerVenta {

    private final ControllerCategorias controllerCategorias;

	@Autowired
	private ServiceVenta servicioVentas;
	@Autowired
	private ServiceCategorias serviceCategoria;
	
	@Autowired
	private Services servicePrenda;

    ControllerVenta(ControllerCategorias controllerCategorias) {
        this.controllerCategorias = controllerCategorias;
    }
	
	@GetMapping("/ventas")
	public String ventas(Model model) {
		
		model.addAttribute("ventas", servicioVentas.findAll());
		
		return "ventas";
	}
	
	@GetMapping("/anadirVenta")
	public String anadirVenta( Model model) {
		model.addAttribute("nuevaVenta", new Venta());
		model.addAttribute("categoria", serviceCategoria.findAll());
		model.addAttribute("prenda", servicePrenda.findAll());
		System.out.println(model.getAttribute("prenda"));
		
		return "anadirVenta";
	}
	@PostMapping("/anadirVenta/submit")
	public String confirmarVenta(@ModelAttribute Venta v) {
		
		servicioVentas.save(v);
		
		return "redirect:/ventas";
	}
	
	@GetMapping("/modificarVenta/{id}")
	public String modificarVenta(@PathVariable long id, Model model) {
		Venta v = servicioVentas.findById(id);
		model.addAttribute("venta", v);
		
		System.out.println(v);
		return "modificar-venta";
	}
	@PostMapping("/modificarVenta/submit")
	public String confirmarModificar(@ModelAttribute Venta v) {
		servicioVentas.save(v);
		System.out.println(v);
		return "redirect:/ventas";
	}
}
