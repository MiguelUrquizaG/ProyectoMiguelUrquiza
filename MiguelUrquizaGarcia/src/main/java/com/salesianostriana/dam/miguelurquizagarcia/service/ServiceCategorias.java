package com.salesianostriana.dam.miguelurquizagarcia.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.miguelurquizagarcia.model.Categoria;
import com.salesianostriana.dam.miguelurquizagarcia.repository.CategoriaRepository;

@Service
public class ServiceCategorias extends BaseService<Categoria, Long, CategoriaRepository>{

	public void delete(Long id) {
		Categoria c =  findById(id);
		Categoria porDefecto = findById(1L);
		if(c!=null) {
			c.getListaPrendas().stream().forEach(prenda -> prenda.setCategoria(porDefecto));
			delete(c);
			
		}
	}
	
}
