package com.salesianostriana.dam.miguelurquizagarcia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "venta")	
public class LineaVenta {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_venta"))
	private Venta venta;
	
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_categoria"))
	@JsonIgnore
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_prenda"))
	@JsonBackReference
	private Prenda prenda;
	
	private double cantidad;
	
	private double subtotal;
	
	private double subTotalDescuento;
	
	private double subtotalDescontado;

	
	@Override
	public String toString() {
	    return "LineaVenta{" +
	           "id=" + id +
	           ", cantidad=" + cantidad +
	           ", subtotal=" + subtotal +
	           ", prenda=" + (prenda != null ? prenda.getNombre() : "null") + // ✅ Solo el nombre, no el objeto completo
	           '}';
	}
	
	public LineaVenta(long id, Venta venta, Categoria categoria, Prenda prenda, double cantidad, double subtotal) {
		super();
		this.id = id;
		this.venta = venta;
		this.categoria = categoria;
		this.prenda = prenda;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}
	
	
	
	
}
