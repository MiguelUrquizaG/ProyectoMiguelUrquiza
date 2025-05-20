package com.salesianostriana.dam.miguelurquizagarcia.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.miguelurquizagarcia.model.LineaVenta;
import com.salesianostriana.dam.miguelurquizagarcia.repository.LineaVentaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LineaVentaService extends BaseService<LineaVenta, Long, LineaVentaRepository>{

	
	public double calcularSubtotal(LineaVenta linea) {
		double total;
		
		total = linea.getCantidad()*linea.getCategoria().getPrecioServicio();
		
		return total;
		
	}
	
	public double calcularSubtotalDescuento(LineaVenta linea) {
		if(linea.getCantidad()>linea.getCategoria().getNumeroPrendasDescuento()) {
			return calcularSubtotal(linea) - calcularSubtotal(linea)*linea.getCategoria().getDescuento()/100;
		}
		return calcularSubtotal(linea);
	}
	
	public double calcularDiferenciaSubtotal(LineaVenta linea) {
		return calcularSubtotal(linea) - calcularSubtotalDescuento(linea);
	}
	
	public LineaVenta buscarPorId(Long id) {
		return findById(id).orElseThrow(() -> new EntityNotFoundException("Venta con id " + id + " no encontrada"));
	}
	
}
