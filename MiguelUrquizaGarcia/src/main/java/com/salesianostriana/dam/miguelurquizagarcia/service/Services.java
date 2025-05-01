package com.salesianostriana.dam.miguelurquizagarcia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.miguelurquizagarcia.model.Prenda;
import com.salesianostriana.dam.miguelurquizagarcia.repository.PrendaRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service

public class Services extends BaseService<Prenda, Long, PrendaRepository>{
	


//	private List<Prenda>list = new ArrayList<Prenda>();
//	private long id = 0;
//	public void addPrenda(Prenda p) {
//		repo.save(p);
//	}
//	
//	public  List<Prenda> getAllPrenda(){
//		return repo.findAll();
//	}
//	
//	public Prenda findById(long id) {
//		return repo.findById(id).orElse(null);
//	}
//	public Prenda modify(long id, Prenda prenda) {
//		Prenda p =findById(id);
//		p.setDescripcion(prenda.getDescripcion());
//		p.setNombre(prenda.getNombre());
//		p.setPrecio(prenda.getPrecio());
//		p.setUrlImagen(prenda.getUrlImagen());
//	
//		repo.save(p);
//		
//		return p;
//	}
//	
//	public Prenda deletePrenda(Prenda p) {
//		Prenda prenda = repo.findById(p.getId()).orElse(null);
//		repo.delete(prenda);
//		return p;
//	}
}
