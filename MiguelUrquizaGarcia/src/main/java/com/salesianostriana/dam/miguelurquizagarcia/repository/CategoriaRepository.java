package com.salesianostriana.dam.miguelurquizagarcia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.miguelurquizagarcia.model.Categoria;
import com.salesianostriana.dam.miguelurquizagarcia.model.Prenda;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public List<Categoria> findByNombreContainingIgnoreCase(String nombre);
}
