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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesianostriana.dam.miguelurquizagarcia.model.Categoria;
import com.salesianostriana.dam.miguelurquizagarcia.model.LineaVenta;
import com.salesianostriana.dam.miguelurquizagarcia.model.Prenda;
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
		
	
		 
		 v.getLineasVenta().forEach(linea ->{
			 Prenda prendaCompleta = servicePrenda.findById(linea.getPrenda().getId());
			 linea.setPrenda(prendaCompleta);
		 });
		 	
		 v.getLineasVenta().forEach(linea ->{
			Categoria categoriaCompleta = serviceCategoria.findById(linea.getCategoria().getId());
			linea.setCategoria(categoriaCompleta);
		 });
		
		servicioVentas.saveVenta(v);
		
		return "ticket";
	}
	
	@GetMapping("/modificarVenta/{id}")
	public String modificarVenta(@PathVariable long id, Model model) {
		Venta v = servicioVentas.findById(id);
		model.addAttribute("venta", v);
		model.addAttribute("categoria", serviceCategoria.findAll());
		model.addAttribute("prenda", servicePrenda.findAll());
		System.out.println(v);
		System.out.println("Lineas de venta: " + v.getLineasVenta());
		return "modificar-venta";
	}
	@PostMapping("/modificarVenta/submit")
	public String confirmarModificar(@ModelAttribute Venta v) {
		List<LineaVenta> lineas = v.getLineasVenta();
		servicioVentas.editVenta(v);
		System.out.println("Resultado: "+v);
		System.out.println("Pso por el POST");
		return "redirect:/ventas";
	}
	
	@DeleteMapping("/eliminarVenta/submit")
	public String eliminarVenta(@RequestParam Long id ) {
		servicioVentas.delete(servicioVentas.findById(id));
		return "redirect:/ventas";
	}
	@GetMapping("/ticket/{id}")
	public String verVenta(@PathVariable long id, Model model) {
		Venta v = servicioVentas.findById(id);
		model.addAttribute("venta", v);
		return "ticket";
	}
	
	@PostMapping("/calcular-total")
	@ResponseBody
	public double calcularTotalDesdeFront(@RequestBody Venta v) {
		return servicioVentas.calcularPrecioDescuento(v);
	}
}
