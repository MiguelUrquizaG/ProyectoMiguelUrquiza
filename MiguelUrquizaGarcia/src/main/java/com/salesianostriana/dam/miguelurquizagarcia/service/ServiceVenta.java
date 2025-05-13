package com.salesianostriana.dam.miguelurquizagarcia.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.miguelurquizagarcia.model.LineaVenta;
import com.salesianostriana.dam.miguelurquizagarcia.model.Venta;
import com.salesianostriana.dam.miguelurquizagarcia.repository.VentaRepository;
@Service
public class ServiceVenta extends BaseService<Venta, Long, VentaRepository>{

	
	public void saveVenta(Venta nuevaVenta) {
		for(LineaVenta linea: nuevaVenta.getLineasVenta()) {
			linea.setVenta(nuevaVenta);
		}
		save(nuevaVenta);
	}
	
}
