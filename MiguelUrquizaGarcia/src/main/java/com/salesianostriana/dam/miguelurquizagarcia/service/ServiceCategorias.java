package com.salesianostriana.dam.miguelurquizagarcia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.miguelurquizagarcia.model.Categoria;
import com.salesianostriana.dam.miguelurquizagarcia.repository.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiceCategorias extends BaseService<Categoria, Long, CategoriaRepository> {

	@Autowired
	private CategoriaRepository repoCategoria;

	public boolean delete(Long id) {
		Categoria c =  buscar(id);
		boolean isEliminado =false;
		//Categoria porDefecto = buscar(0L);
		
		if(!c.getListaPrendas().isEmpty() && c!=null) {
			
		}else {
			delete(c);
			isEliminado=true;
			//c.getListaPrendas().stream().forEach(prenda -> prenda.setCategoria(porDefecto));
		}
		
		return isEliminado;
	}

	public Categoria buscar(Long id) {
		return findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Categoría con id " + id + " no encontrada"));
	}

	public void agregar(Categoria c) {
		repoCategoria.save(c);
	}

	public void editar(Categoria c) {

		repoCategoria.save(c);
	}

	public List<Categoria> ordenarTopVentas(List<Categoria> lista) {
		return repoCategoria.findAllByOrderByTopVentaDesc();
	}

	public List<Categoria> buscarPorNombre(String nombre) {

		List<Categoria> lista = repoCategoria.findByNombreContainingIgnoreCase(nombre);
		return lista;
	}

	public List<Categoria> buscarCategoriasEconomicasNoTopVenta(Double precioMax) {
		return repoCategoria.buscarCategoriasNoTopVentaPorPrecioMenor(precioMax);
	}

}
