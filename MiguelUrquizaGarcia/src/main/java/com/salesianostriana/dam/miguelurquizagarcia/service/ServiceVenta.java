package com.salesianostriana.dam.miguelurquizagarcia.service;

import java.util.OptionalDouble;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.miguelurquizagarcia.model.LineaVenta;
import com.salesianostriana.dam.miguelurquizagarcia.model.Venta;
import com.salesianostriana.dam.miguelurquizagarcia.repository.VentaRepository;
@Service
public class ServiceVenta extends BaseService<Venta, Long, VentaRepository>{

	
	public void saveVenta(Venta nuevaVenta) {
		for(LineaVenta linea: nuevaVenta.getLineasVenta()) {
			linea.setVenta(nuevaVenta);
			System.out.println(linea.getSubtotal());
		}
		nuevaVenta.setPrecioTotal(calcularPrecioTotalVenta(nuevaVenta));
		save(nuevaVenta);
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
	
}
