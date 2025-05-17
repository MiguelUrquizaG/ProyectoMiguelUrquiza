package com.salesianostriana.dam.miguelurquizagarcia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.miguelurquizagarcia.model.Prenda;

public interface PrendaRepository extends JpaRepository<Prenda,Long>{
	
	public List<Prenda>findByNombreContainingIgnoreCase(String nombre);
	@Query("SELECT p FROM Prenda p ORDER BY LOWER(p.nombre) ASC")
	public List<Prenda> findAllByOrderByNombreAsc();
	@Query("SELECT p FROM Prenda p  ORDER BY LOWER(p.nombre) DESC")
	public List<Prenda> findAllByOrderByNombreDesc();
}
