package com.salesianostriana.dam.miguelurquizagarcia.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	private double precioServicio;
	private boolean topVenta;
	
	public Categoria (Long id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion=descripcion;
	}
	
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "categoria", fetch= FetchType.EAGER)
	private List<Prenda> listaPrendas;
	

}
