package com.salesianostriana.dam.miguelurquizagarcia.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String nombre;
	private String descripcion;
	
	public Categoria (Long id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion=descripcion;
	}
	
	@OneToMany(mappedBy = "categoria")
	private List<Prenda> listaPrendas;
	

}
