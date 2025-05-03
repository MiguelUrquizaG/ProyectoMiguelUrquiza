package com.salesianostriana.dam.miguelurquizagarcia.model;





import jakarta.persistence.Entity;
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
	private double precio;
	private String descripcion;
	private String urlImagen;
	
	@ManyToOne
	private Categoria categoria;
	
	
}
