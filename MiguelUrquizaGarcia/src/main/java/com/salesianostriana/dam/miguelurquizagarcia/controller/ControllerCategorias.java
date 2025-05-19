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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesianostriana.dam.miguelurquizagarcia.model.Categoria;
import com.salesianostriana.dam.miguelurquizagarcia.service.ServiceCategorias;

@Controller	
public class ControllerCategorias {

	@Autowired
	private ServiceCategorias categoriaServicios;
	
	@GetMapping("/categorias")
	public String showCategorias(Model model ) {
		
		List<Categoria> lista = categoriaServicios.findAll();
		lista = categoriaServicios.ordenarTopVentas(lista);
		
		model.addAttribute("categoria",lista);
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
		Categoria c =categoriaServicios.buscar(id);
		model.addAttribute("categoria", c);
		return "form-modificar-categoria";
	}
	
	@PostMapping("/editarCategoria/submit")
	public String procesarCategorias(@ModelAttribute Categoria categoria) {
		categoriaServicios.save(categoria);
		return "redirect:/categorias";
	}
	
	@DeleteMapping("/eliminarCategoria/submit")
	public String eliminarCategoria(@RequestParam Long id, RedirectAttributes redirectAttributes	) {
		System.out.println(id);
		boolean eliminado = categoriaServicios.delete(id);
		redirectAttributes.addFlashAttribute("eliminado", eliminado);
		return "redirect:/categorias";
	}
	
	
	
	@GetMapping("/mostrarCategoria/{id}")
	public String mostrarCategoria(@PathVariable Long id, Model model) {
		Categoria c = categoriaServicios.buscar(id);
		model.addAttribute("prendasCategoria", c.getListaPrendas());
		return "mostrar-categoria";
	}
	
	@GetMapping("/buscarCategoria")
	public String buscarCategoria(@RequestParam String query, Model model) {
		List<Categoria>lista = categoriaServicios.findAll();
		System.out.println(query);
		lista =  categoriaServicios.buscarPorNombre(query);

		model.addAttribute("categoria", lista);
		model.addAttribute("nuevacategoria", new Categoria());
		return "listaCategorias";
	}
	
	  @GetMapping("/filtrarPrecioMax")
	    public String filtrarCategoriasEconomicas(@RequestParam("precioMax") Double precioMax, Model model) {
	        List<Categoria> categoriasFiltradas = categoriaServicios.buscarCategoriasEconomicasNoTopVenta(precioMax);
	        model.addAttribute("categoria", categoriasFiltradas);
	        model.addAttribute("nuevacategoria", new Categoria());
	        return "listaCategorias"; 
	    }
	  
	  @GetMapping("/administrarDescuentos")
	  public String administrarDescuento(Model model,Categoria c) {
		  model.addAttribute("categorias", categoriaServicios.findAll());
		  System.out.println(categoriaServicios.findAll());
		  model.addAttribute("categoria", c);
		  return "administrarDescuentos";
	  }
	  
	  @PostMapping("/editarDescuentos/submit/{id}")
	  public String editarDescuentos(@PathVariable Long id, @RequestParam(required=false) Double descuento, @RequestParam(required=false) Double cantidadPrendas) {
		  System.out.println("Edito los descuentos");
		  System.out.println(id);
		  System.out.println(descuento);
		  System.out.println(cantidadPrendas);
		  Categoria c = categoriaServicios.buscar(id);
		  c.setDescuento(descuento);
		  c.setNumeroPrendasDescuento(cantidadPrendas);
		  categoriaServicios.save(c);	
		  
		  return "redirect:/administrarDescuentos";
	  }
	
}
