package com.salesianostriana.dam.miguelurquizagarcia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.miguelurquizagarcia.model.Categoria;
import com.salesianostriana.dam.miguelurquizagarcia.model.LineaVenta;
import com.salesianostriana.dam.miguelurquizagarcia.model.Prenda;
import com.salesianostriana.dam.miguelurquizagarcia.model.Venta;
import com.salesianostriana.dam.miguelurquizagarcia.repository.VentaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VentaService extends BaseService<Venta, Long, VentaRepository> {

	@Autowired
	CategoriaService categoriaService;

	@Autowired
	private PrendaService servicePrenda;

	@Autowired
	private LineaVentaService serviceLineaVenta;

	@Autowired
	private VentaRepository repo;

	public void saveVenta(Venta v) {

		List<LineaVenta> lineasGuardadas = new ArrayList<>();
		for (LineaVenta linea : v.getLineasVenta()) {

			Prenda prendaCompleta = servicePrenda.buscarPrenda(linea.getPrenda().getId());
			linea.setPrenda(prendaCompleta);

			Categoria categoriaCompleta = categoriaService.buscar(linea.getCategoria().getId());
			linea.setCategoria(categoriaCompleta);

			linea.setSubtotal(serviceLineaVenta.calcularSubtotal(linea));
			linea.setSubTotalDescuento(serviceLineaVenta.calcularSubtotalDescuento(linea));
			linea.setSubtotalDescontado(serviceLineaVenta.calcularDiferenciaSubtotal(linea));

			LineaVenta lineaGuardada = serviceLineaVenta.save(linea);
			lineasGuardadas.add(lineaGuardada);
		}

		v.setLineasVenta(lineasGuardadas);

		for (LineaVenta lineaGuardada : lineasGuardadas) {
			Prenda prenda = lineaGuardada.getPrenda();
			if (prenda.getLineasVenta() == null) {
				prenda.setLineasVenta(new ArrayList<>());
			}
			if (!prenda.getLineasVenta().contains(lineaGuardada)) {
				prenda.getLineasVenta().add(lineaGuardada);
				servicePrenda.save(prenda);
				if (prenda.getLineasVenta() == null) {
					System.out.println("Soy nulo");
				} else {
					System.out.println("LineasVenta: " + prenda.getDescripcion());
				}

			}

			Categoria categoria = lineaGuardada.getCategoria();
			if (categoria.getLineasVenta() == null) {
				categoria.setLineasVenta(new ArrayList<>());
			}
			if (!categoria.getLineasVenta().contains(lineaGuardada)) {
				categoria.getLineasVenta().add(lineaGuardada);
				categoriaService.save(categoria);
			}

		}

		for (LineaVenta linea : v.getLineasVenta()) {
			linea.setVenta(v);
			System.out.println(linea.getSubtotal());
		}
		v.setPrecioTotal(calcularPrecioTotalVenta(v));
		save(v);
		v.setPrecioDescontado(calcularPrecioDescuento(v));
		v.setPrecioTotal(calcularPrecioSinDescuento(v));
		v.setCantidadDescontada(calcularCantidadDescontada(v));
		System.out.println("Venta" + v);
		repo.save(v);
		System.out.println("Venta2:" + v);

	}

	public void editVenta(Venta v) {
		for (LineaVenta linea : v.getLineasVenta()) {
			linea.setVenta(v);
			System.out.println(linea.getSubtotal());
		}
		v.setPrecioTotal(calcularPrecioTotalVenta(v));
		edit(v);
	}

	public double calcularPrecioTotalVenta(Venta v) {
		double total = 0;
		for (LineaVenta l : v.getLineasVenta()) {

			total += l.getSubtotal();
		}

		return total;
	}

	public List<Prenda> buscarPorNombre(String nombre) {
		return buscarPorNombre(nombre);
	}

	public double calcularPrecioDescuento(Venta v) {

		List<LineaVenta> listaLineas = v.getLineasVenta();

		double total = 0;
		for (LineaVenta l : listaLineas) {

			Categoria c = l.getCategoria();
			double precioUnitario = c.getPrecioServicio();
			double cantidad = l.getCantidad();
			double subtotal;

			subtotal = cantidad * precioUnitario;
			if (cantidad > c.getNumeroPrendasDescuento()) {

				System.out.println("Cantidad: " + cantidad);
				System.out.println("PrecioUnitario: " + precioUnitario);
				System.out.println("Descuento: " + c.getDescuento());
				subtotal = subtotal - ((subtotal) * c.getDescuento() / 100);
				System.out.println("Subtotal: " + subtotal);
			}

			total += subtotal;
			System.out.println("Total: " + total);
		}

		return total;
	}

	public double calcularPrecioSinDescuento(Venta v) {
		return v.getLineasVenta().stream()
				.mapToDouble(linea -> linea.getCantidad() * linea.getCategoria().getPrecioServicio()).sum();

	}

	public double calcularCantidadDescontada(Venta v) {
		double precioSinDescuento = calcularPrecioSinDescuento(v);
		double precioDescontado = calcularPrecioDescuento(v);
		return precioSinDescuento - precioDescontado;
	}

	public Venta buscarVenta(Long id) {
		return findById(id).orElseThrow(() -> new EntityNotFoundException("Venta con id " + id + " no encontrada"));
	}

	public double calcularTotalGeneradoSinDescuento() {
		return findAll().stream().mapToDouble(venta -> venta.getPrecioTotal()).sum();
	}

	public double calcularTotalGeneradoConDescuento() {
		return findAll().stream().mapToDouble(venta -> venta.getPrecioDescontado()).sum();
	}

	public double calcularTotalDescontado() {
		return findAll().stream().mapToDouble(venta -> venta.getCantidadDescontada()).sum();
	}
}
