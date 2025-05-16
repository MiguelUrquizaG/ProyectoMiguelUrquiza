package com.salesianostriana.dam.miguelurquizagarcia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.miguelurquizagarcia.model.Categoria;
import com.salesianostriana.dam.miguelurquizagarcia.repository.CategoriaRepository;

@Service
public class ServiceCategorias extends BaseService<Categoria, Long, CategoriaRepository>{

	@Autowired
	private CategoriaRepository repoCategoria;
	
	public void delete(Long id) {
		Categoria c =  findById(id);
		Categoria porDefecto = findById(0L);
		if(c!=null) {
			c.getListaPrendas().stream().forEach(prenda -> prenda.setCategoria(porDefecto));
			delete(c);
			
		}
	}
	
	public void agregar (Categoria c) {
		calcularDescuento(c);
		repoCategoria.save(c);
	}
	
	public void editar(Categoria c) {
		
		reestablecerPrecio(c);
		calcularDescuento(c);
		
		repoCategoria.save(c);	
	}
	
	public List<Categoria> ordenarTopVentas(List<Categoria> lista) {
		return repoCategoria.findAllByOrderByTopVentaDesc();
	}
	
	public List<Categoria> buscarPorNombre(String nombre){
		
		List<Categoria> lista = repoCategoria.findByNombreContainingIgnoreCase(nombre);
		return lista;
	}
	
	public double calcularDescuento(Categoria c) {
		
		if(c.getDescuento()>0) {
			c.setPrecioServicio(c.getPrecioServicio()*c.getDescuento()/100);
		}
		
		return c.getPrecioServicio();
		

	}
	
	public double reestablecerPrecio(Categoria c) {
		Categoria categoria = repoCategoria.findById(c.getId()).orElse(null);
		double precio = categoria.getPrecioServicio();
		if(c.getDescuento()!=categoria.getDescuento()&&categoria.getDescuento()!=0&&c.getPrecioServicio()==categoria.getPrecioServicio()) {
			precio=(c.getPrecioServicio()*100/categoria.getDescuento());
			
		}else if(c.getPrecioServicio()!=categoria.getPrecioServicio()) {
			precio = c.getPrecioServicio();
		}
		
		return precio;
	}
	
	
	
}
