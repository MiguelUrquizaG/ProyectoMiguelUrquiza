package com.salesianostriana.dam.miguelurquizagarcia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.miguelurquizagarcia.model.Categoria;
import com.salesianostriana.dam.miguelurquizagarcia.repository.CategoriaRepository;

@Service
public class ServiceCategorias extends BaseService<Categoria, Long, CategoriaRepository>{

	@Autowired
	private CategoriaRepository repoCategoria;
	
	public void delete(Long id) {
		Categoria c =  findById(id);
		Categoria porDefecto = findById(0L);
		if(c!=null) {
			c.getListaPrendas().stream().forEach(prenda -> prenda.setCategoria(porDefecto));
			delete(c);
			
		}
	}
	
	public List<Categoria> ordenarTopVentas(List<Categoria> lista) {
		lista.sort((p1,p2)-> Boolean.compare(p2.isTopVenta(), p1.isTopVenta()));
		return lista;
	}
	
	public List<Categoria> buscarPorNombre(String nombre){
		return repoCategoria.findByNombreContainingIgnoreCase(nombre);
	}
	
}
