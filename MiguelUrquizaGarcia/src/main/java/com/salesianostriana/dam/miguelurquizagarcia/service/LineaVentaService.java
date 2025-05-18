package com.salesianostriana.dam.miguelurquizagarcia.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.miguelurquizagarcia.model.LineaVenta;
import com.salesianostriana.dam.miguelurquizagarcia.repository.LineaVentaRepository;

@Service
public class LineaVentaService extends BaseService<LineaVenta, Long, LineaVentaRepository>{

	
	public double calcularSubtotal(LineaVenta linea) {
		double total;
		
		total = linea.getCantidad()*linea.getCategoria().getPrecioServicio();
		
		return total;
		
	}
	
	public double calcularSubtotalDescuento(LineaVenta linea) {
		return calcularSubtotal(linea) - calcularSubtotal(linea)*linea.getCategoria().getDescuento()/100;
	}
	
	public double calcularDiferenciaSubtotal(LineaVenta linea) {
		return calcularSubtotal(linea) - calcularSubtotalDescuento(linea);
	}
	
}
