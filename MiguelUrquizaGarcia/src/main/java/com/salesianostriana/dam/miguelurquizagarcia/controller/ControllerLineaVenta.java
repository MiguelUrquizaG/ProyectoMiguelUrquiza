package com.salesianostriana.dam.miguelurquizagarcia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.miguelurquizagarcia.model.LineaVenta;
import com.salesianostriana.dam.miguelurquizagarcia.service.LineaVentaService;
import com.salesianostriana.dam.miguelurquizagarcia.service.ServiceCategorias;
import com.salesianostriana.dam.miguelurquizagarcia.service.Services;

@Controller
public class ControllerLineaVenta {

	@Autowired
	private LineaVentaService lineaVentaService;
	
	@Autowired
	private ServiceCategorias categoriaService;
	
	@Autowired
	private Services services;
	
	@PostMapping("/anadirLineaVenta/submit")
	public String anadirLineaVenta(@ModelAttribute LineaVenta lineaVenta,@RequestParam long prendaId,
            @RequestParam long categoriaId,
            @RequestParam double cantidad, Model model) {
		
	    LineaVenta linea = new LineaVenta();
	    linea.setCantidad(cantidad);
	    linea.setCategoria(categoriaService.findById(categoriaId));
	    linea.setPrenda(services.findById(prendaId));

	    // ⚠️ FALTA asociar VENTA_ID si aún no lo estás haciendo
	    // linea.setVenta(venta);  ← esto es importante

	    lineaVentaService.save(linea);
		return "anadirVenta";
		
	}
}
