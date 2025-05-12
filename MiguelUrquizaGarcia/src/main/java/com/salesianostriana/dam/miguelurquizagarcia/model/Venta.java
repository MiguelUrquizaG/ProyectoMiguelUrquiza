package com.salesianostriana.dam.miguelurquizagarcia.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

public class Venta {
	@Id
	@GeneratedValue
	private long id;
	
	private String nombreCliente;
	private LocalDate fechaEntrega;
	private LocalDate fechaRecogida;
	private double precioTotal;
	
	@OneToMany(mappedBy="venta", fetch= FetchType.EAGER)
	private List<LineaVenta>lineasVenta;
	
	
}
