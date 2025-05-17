package com.salesianostriana.dam.miguelurquizagarcia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.miguelurquizagarcia.model.Categoria;
import com.salesianostriana.dam.miguelurquizagarcia.model.LineaVenta;
import com.salesianostriana.dam.miguelurquizagarcia.model.Prenda;
import com.salesianostriana.dam.miguelurquizagarcia.model.Venta;
import com.salesianostriana.dam.miguelurquizagarcia.repository.VentaRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class ServiceVenta extends BaseService<Venta, Long, VentaRepository>{

	@Autowired
	ServiceCategorias categoriaService;
	
	public void saveVenta(Venta nuevaVenta) {
		for(LineaVenta linea: nuevaVenta.getLineasVenta()) {
			linea.setVenta(nuevaVenta);
			System.out.println(linea.getSubtotal());
		}
		nuevaVenta.setPrecioTotal(calcularPrecioTotalVenta(nuevaVenta));
		save(nuevaVenta);
	}
	
	public void editVenta(Venta nuevaVenta) {
		for(LineaVenta linea: nuevaVenta.getLineasVenta()) {
			linea.setVenta(nuevaVenta);
			System.out.println(linea.getSubtotal());
		}
		nuevaVenta.setPrecioTotal(calcularPrecioTotalVenta(nuevaVenta));
		edit(nuevaVenta);
	}
	
	public double calcularPrecioTotalVenta(Venta v) {
//		OptionalDouble total = v.getLineasVenta().stream()
//		.mapToDouble(l -> l.getSubTotal())
//		.average();
//		System.out.println(total);
//		return total.getAsDouble();
		
		double total=0;
		for(LineaVenta l: v.getLineasVenta()) {
			
			total+=l.getSubtotal();
		}
		
		return total;
	}
	
	public List<Prenda>buscarPorNombre(String nombre){
		return buscarPorNombre(nombre);
	}
	
	
	public double calcularPrecioDescuento(Venta v) {
		
		List<LineaVenta>listaLineas = v.getLineasVenta();
		
		double total=0;
		for(LineaVenta l : listaLineas) {
			
			Categoria c = l.getCategoria();
			double precioUnitario = c.getPrecioServicio();
			double cantidad = l.getCantidad();
			double subtotal;
			subtotal = cantidad*precioUnitario;
			if(cantidad>c.getNumeroPrendasDescuento()) {
				
				
				subtotal = subtotal*(precioUnitario*c.getDescuento()/100);
			}
			
			
			total+=subtotal;
		}
		
		return total;
	}
	
	public Venta buscarVenta (Long id) {
		return repositorio.findById(id).orElseThrow(() -> new EntityNotFoundException("Venta con id " + id + " no encontrada"));
	}
	
	
}
