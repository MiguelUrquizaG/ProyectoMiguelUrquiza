package com.salesianostriana.dam.miguelurquizagarcia.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prenda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nombre;
	private String descripcion;
	private String urlImagen;
	private boolean topVenta;

	@ManyToOne
	@JsonIgnoreProperties("listaPrendas")
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_prenda_categoria"))
	private Categoria categoria;

	@OneToMany(mappedBy = "prenda")
	@JsonIgnore
	private List<LineaVenta> lineasVenta;

	public void addToCategoria(Categoria categoria) {
		this.categoria = categoria;
		categoria.getListaPrendas().add(this);
	}

	public void removeFromCategoria(Categoria categoria) {
		categoria.getListaPrendas().remove(this);
		this.categoria = null;
	}

	@Override
	public String toString() {
		return "Prenda{" + "id=" + id + ", nombre='" + nombre + '\'' + '}';
	}
}
