package com.salesianostriana.dam.miguelurquizagarcia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.miguelurquizagarcia.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public List<Categoria> findByNombreContainingIgnoreCase(String nombre);

	public List<Categoria> findAllByOrderByTopVentaDesc();

	@Query("SELECT c FROM Categoria c WHERE c.topVenta = false AND c.precioServicio < :precioMax")
	List<Categoria> buscarCategoriasNoTopVentaPorPrecioMenor(@Param("precioMax") Double precioMax);

}
