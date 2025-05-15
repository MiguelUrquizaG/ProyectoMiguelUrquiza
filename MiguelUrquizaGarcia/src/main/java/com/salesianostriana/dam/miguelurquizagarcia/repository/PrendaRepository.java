package com.salesianostriana.dam.miguelurquizagarcia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.miguelurquizagarcia.model.Prenda;

public interface PrendaRepository extends JpaRepository<Prenda,Long>{
	
	public List<Prenda>findByNombreContainingIgnoreCase(String nombre);

}
