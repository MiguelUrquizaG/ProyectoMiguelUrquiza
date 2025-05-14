package com.salesianostriana.dam.miguelurquizagarcia.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "lineasVenta")	
public class Venta {
	@Id
	@GeneratedValue
	private long id;
	
	private String nombreCliente;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaEntrega;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaRecogida;
	private double precioTotal;
	
	@OneToMany(mappedBy="venta", fetch= FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
	private List<LineaVenta>lineasVenta;
	
	public void addLineaVenta(LineaVenta linea) {
		linea.setVenta(this);
		this.lineasVenta.add(linea);
	}
	
}
