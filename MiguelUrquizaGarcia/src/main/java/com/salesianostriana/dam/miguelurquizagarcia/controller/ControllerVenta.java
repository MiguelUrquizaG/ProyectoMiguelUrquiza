package com.salesianostriana.dam.miguelurquizagarcia.controller;


import java.util.ArrayList;
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
import com.salesianostriana.dam.miguelurquizagarcia.service.LineaVentaService;
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
	
	@Autowired
	private LineaVentaService serviceLineaVenta;

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
	    // Primero, guardar cada LineaVenta
	    List<LineaVenta> lineasGuardadas = new ArrayList<>();
	    for (LineaVenta linea : v.getLineasVenta()) {
	        // Asignar prendas y categorías
	        Prenda prendaCompleta = servicePrenda.buscarPrenda(linea.getPrenda().getId());
	        linea.setPrenda(prendaCompleta);
	        
	        Categoria categoriaCompleta = serviceCategoria.buscar(linea.getCategoria().getId());
	        linea.setCategoria(categoriaCompleta);
	        
	        // Guardar la línea de venta primero
	        LineaVenta lineaGuardada = serviceLineaVenta.save(linea); // Asumiendo que tienes un servicio para LineaVenta
	        lineasGuardadas.add(lineaGuardada);
	    }
	    
	    // Reemplazar las líneas no persistidas con las guardadas
	    v.setLineasVenta(lineasGuardadas);
	    
	    // Ahora que todas las líneas están guardadas, podemos establecer las relaciones bidireccionales
	    for (LineaVenta lineaGuardada : lineasGuardadas) {
	        Prenda prenda = lineaGuardada.getPrenda();
	        if (prenda.getLineasVenta() == null) {
	            prenda.setLineasVenta(new ArrayList<>());
	        }
	        if (!prenda.getLineasVenta().contains(lineaGuardada)) {
	            prenda.getLineasVenta().add(lineaGuardada);
	            servicePrenda.save(prenda);
	          
	        }
	        
	        Categoria categoria = lineaGuardada.getCategoria();
	        if (categoria.getLineasVenta() == null) {
	            categoria.setLineasVenta(new ArrayList<>());
	        }
	        if (!categoria.getLineasVenta().contains(lineaGuardada)) {
	            categoria.getLineasVenta().add(lineaGuardada);
	            serviceCategoria.save(categoria);
	        }
	        
	       
	    }
	    
	    v.setPrecioTotal(servicioVentas.calcularPrecioDescuento(v));
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
