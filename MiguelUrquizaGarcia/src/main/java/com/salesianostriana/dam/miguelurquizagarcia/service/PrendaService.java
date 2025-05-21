package com.salesianostriana.dam.miguelurquizagarcia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.miguelurquizagarcia.model.Categoria;
import com.salesianostriana.dam.miguelurquizagarcia.model.Prenda;
import com.salesianostriana.dam.miguelurquizagarcia.repository.PrendaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service

public class PrendaService extends BaseService<Prenda, Long, PrendaRepository> {

	@Autowired
	private PrendaRepository repoPrenda;

	public List<Prenda> ordenarTopVentas(List<Prenda> lista) {
		lista.sort((p1, p2) -> Boolean.compare(p2.isTopVenta(), p1.isTopVenta()));
		return lista;
	}

	public List<Prenda> buscarPorNombre(String nombre) {
		return repoPrenda.findByNombreContainingIgnoreCase(nombre);
	}

	public Prenda buscarPrenda(Long id) {
		return findById(id).orElseThrow(() -> new EntityNotFoundException("Prenda con id " + id + " no encontrada"));
	}

	public List<Prenda> findAllOrderByNombreAsc() {
		return repoPrenda.findAllByOrderByNombreAsc();
	}

	public List<Prenda> findAllOrderByNombreDesc() {
		return repoPrenda.findAllByOrderByNombreDesc();
	}

	public boolean eliminarPrenda(Long id) {

		boolean isEliminado = false;

		Prenda p = buscarPrenda(id);

		if (!p.getLineasVenta().isEmpty()) {

		} else {
			delete(p);
			isEliminado = true;
		}

		return isEliminado;
	}

}
