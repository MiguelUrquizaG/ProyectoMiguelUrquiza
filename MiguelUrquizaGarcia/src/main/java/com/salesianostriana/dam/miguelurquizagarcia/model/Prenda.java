package com.salesianostriana.dam.miguelurquizagarcia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prenda {

	@Id
	@GeneratedValue
	private long id;
	
	private String nombre;
	private String descripcion;
	private String urlImagen;
	private boolean topVenta;
	
	@ManyToOne
	@JsonIgnoreProperties("listaPrendas")
	@JoinColumn(foreignKey = @ForeignKey(name="fk_prenda_categoria"))	
	private Categoria categoria;
	
	
	public void addToCategoria(Categoria categoria) {
		this.categoria = categoria;
		categoria.getListaPrendas().add(this);
	}
	
	public void removeFromCategoria(Categoria categoria) {
		categoria.getListaPrendas().remove(this);
		this.categoria = null;		
	}
}
