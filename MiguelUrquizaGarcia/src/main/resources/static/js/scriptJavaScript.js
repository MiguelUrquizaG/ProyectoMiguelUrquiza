
	let categorias = [[${categoria}]];
	let prendas = [[${prenda}]];
	let indiceLineaVenta = 0;
	console.log("Categorías cargadas:", categorias);
	console.log("Prendas:", prendas)

	document.addEventListener('DOMContentLoaded', function () {
		generarLineasVenta();
		console.log("DOM completamente cargado, evento añadido");
		document.getElementById('btnAnadirLinea').addEventListener('click', function () {
			console.log("Boton Pulsado")
			generarLineasVenta();
		});
	})

	function generarLineasVenta() {
		console.log("Generando línea de venta...");
		const container = document.getElementById('lineasVenta')
		const fila = document.createElement('div');
		fila.classList.add('row', 'mb-3')
		fila.setAttribute('data-linea-id', indiceLineaVenta);


		let selectCategoria = document.createElement('select');
		selectCategoria.classList.add('form-select', 'form-select-sm', 'rounded-3', 'shadow-sm');
		selectCategoria.name = `lineasVenta[${indiceLineaVenta}].categoria.id`;
		selectCategoria.required = true;

		let defaultOption = document.createElement('option');
		defaultOption.text = 'Seleccione Categoria'
		defaultOption.value = "";
		selectCategoria.appendChild(defaultOption);


		let labelCategoria = document.createElement('label')
		labelCategoria.setAttribute('for', selectCategoria.id)
		labelCategoria.textContent = 'Categoria'
		labelCategoria.classList.add('form-label')

		let selectPrenda = document.createElement('select');
		selectPrenda.classList.add('form-select', 'form-select-sm', 'rounded-3', 'shadow-sm');
		selectPrenda.name = `lineasVenta[${indiceLineaVenta}].prenda.id`
		selectPrenda.required = true;

		let defaultPrenda = document.createElement('option')
		defaultPrenda.text = 'Seleccione Prenda'
		defaultPrenda.value = ""
		selectPrenda.appendChild(defaultPrenda);

		let labelPrenda = document.createElement('label')
		labelPrenda.setAttribute('for', selectPrenda.id)
		labelPrenda.textContent = 'Prenda'
		labelPrenda.classList.add('form-label')

		let inputNumero = document.createElement('input')
		inputNumero.classList.add('form-control', 'form-control-sm', 'rounded-3', 'shadow-sm')
		inputNumero.type = 'number';
		inputNumero.min = '1';
		inputNumero.required = true;
		inputNumero.name = `lineasVenta[${indiceLineaVenta}].cantidad`;
		inputNumero.style.maxWidth = '70px';


		let labelCantidad = document.createElement('label')
		labelCantidad.setAttribute('for', inputNumero.id)
		labelCantidad.textContent = 'Servicio'
		labelCantidad.classList.add('form-label')


		categorias.forEach(function (cat) {
			let option = document.createElement('option')
			option.value = cat.id;
			option.text = cat.nombre;
			selectCategoria.appendChild(option);
		})


		let precio = document.createElement('input')
		precio.classList.add('form-control', 'form-control-sm', 'rounded-3', 'shadow-sm');
		precio.value = ' ';
		precio.name = `lineasVenta[${indiceLineaVenta}].subtotal`
		precio.readOnly = true;
		precio.type = 'number'


		let labelSubtotal = document.createElement('label')
		labelSubtotal.setAttribute('for', precio.id)
		labelSubtotal.textContent = 'Subtotal'
		labelSubtotal.classList.add('form-label')

		let precioDescuento = document.createElement('input')
		precioDescuento.classList.add('form-control', 'form-control-sm', 'rounded-3', 'shadow-sm', 'text-success');
		precioDescuento.value = '';
		precioDescuento.type = 'number'
		precioDescuento.step = '0.01';
		precioDescuento.readOnly = true;


		let labelPrecioDescuento = document.createElement('label')
		labelPrecioDescuento.setAttribute('for', precioDescuento.id)
		labelPrecioDescuento.textContent = 'Subtotal Con Descuento'
		labelPrecioDescuento.classList.add('form-label')


		let diferenciaDescuento = document.createElement('input')
		diferenciaDescuento.classList.add('form-control', 'form-control-sm', 'rounded-3', 'shadow-sm', 'text-danger');
		diferenciaDescuento.value = '';
		diferenciaDescuento.type = 'number';
		diferenciaDescuento.readOnly = true;



		let labelDiferencia = document.createElement('label')
		labelDiferencia.setAttribute('for', diferenciaDescuento.id)
		labelDiferencia.textContent = 'Diferencia Descuento'
		labelDiferencia.classList.add('form-label')

		let btnEliminar = document.createElement('button');
		btnEliminar.classList.add('btn', 'btn-danger', 'btn-sm')
		btnEliminar.textContent = 'Eliminar'

		if (indiceLineaVenta === 0) {
			btnEliminar.disabled = true;
			btnEliminar.classList.add('disabled');
		}

		btnEliminar.addEventListener('click', function () {
			container.removeChild(fila);
			calcularTotal();
			calcularPrecioTotalConDescuento();
			calcularCantidadDescuento();
		})

		selectCategoria.addEventListener('change', function () {
			const categoriaSeleccionadaId = this.value;
			console.log(categoriaSeleccionadaId);

			selectPrenda.innerHTML = '';
			selectPrenda.appendChild(defaultPrenda);
			prendas.forEach(function (prenda) {
				console.log(prenda.categoria);
				if (prenda.categoria.id == categoriaSeleccionadaId) {

					let option = document.createElement('option');
					option.value = prenda.id;
					option.text = prenda.nombre;
					selectPrenda.appendChild(option);



				}

			});
			const categoriaSeleccionada = categorias.find(cat => cat.id == categoriaSeleccionadaId);
			if (categoriaSeleccionada) {
				precio.setAttribute('data-precio-unitario', categoriaSeleccionada.precioServicio);
				actualizarSubtotal();
				precioDescuento.setAttribute('data-descuento', categoriaSeleccionada.descuento)
				precioDescuento.setAttribute('data-cantidadPrendas', categoriaSeleccionada.numeroPrendasDescuento)
				calcularPrecioDescontado();

			}

			console.log("Cat seleccionada: ", categoriaSeleccionada);

		});

		inputNumero.addEventListener('input', actualizarSubtotal);







		function actualizarSubtotal() {
			const cantidad = parseInt(inputNumero.value) || 0;
			const precioUnitario = parseFloat(precio.getAttribute('data-precio-unitario')) || 0;
			const subtotal = cantidad * precioUnitario;
			precio.value = subtotal.toFixed(2);
			calcularTotal();
			calcularPrecioTotalConDescuento();
			calcularCantidadDescuento();
			calcularPrecioDescontado();


		}
		function calcularPrecioDescontado() {

			const precioT = parseFloat(precio.value) || 0;
			const precioDescontado = parseFloat(precioDescuento.getAttribute('data-descuento')) || 0
			const cantidadPrendas = parseFloat(precioDescuento.getAttribute('data-cantidadPrendas')) || 0

			if (inputNumero.value > cantidadPrendas) {
				precioDescuento.value = (precioT - (precioT * precioDescontado / 100)).toFixed(2);
				calcularDiferenciaDescuento();

			}
		}

		function calcularDiferenciaDescuento() {

			diferenciaDescuento.value = (parseFloat(precio.value) - parseFloat(precioDescuento.value)).toFixed(2);


		}

		function calcularPrecioTotalConDescuento() {
			let totalConDescuento = 0;

			document.querySelectorAll('div[data-linea-id]').forEach(fila => {
				const selectCategoria = fila.querySelector('select[name^="lineasVenta["][name$=".categoria.id"]');
				const categoriaSeleccionadaId = selectCategoria.value;
				const cantidad = parseInt(fila.querySelector('input[name^="lineasVenta["][name$=".cantidad"]').value) || 0;
				const precioUnitario = parseFloat(fila.querySelector('input[name^="lineasVenta["][name$=".subtotal"]').getAttribute('data-precio-unitario')) || 0;


				const categoriaSeleccionada = categorias.find(cat => cat.id == categoriaSeleccionadaId);
				const descuento = categoriaSeleccionada ? categoriaSeleccionada.descuento : 0;
				const numPrendasDescuento = categoriaSeleccionada ? categoriaSeleccionada.numeroPrendasDescuento : 0;

				let subtotal = cantidad * precioUnitario;


				if (cantidad > numPrendasDescuento) {
					subtotal -= subtotal * (descuento / 100);
				}

				totalConDescuento += subtotal;
			});

			document.getElementById('precioTotalDescuento').value = totalConDescuento.toFixed(2);
		}


		function calcularCantidadDescuento() {
			let totalDescuento = 0;

			document.querySelectorAll('div[data-linea-id]').forEach(fila => {
				const selectCategoria = fila.querySelector('select[name^="lineasVenta["][name$=".categoria.id"]');
				const categoriaSeleccionadaId = selectCategoria.value;
				const cantidad = parseInt(fila.querySelector('input[name^="lineasVenta["][name$=".cantidad"]').value) || 0;
				const precioUnitario = parseFloat(fila.querySelector('input[name^="lineasVenta["][name$=".subtotal"]').getAttribute('data-precio-unitario')) || 0;


				const categoriaSeleccionada = categorias.find(cat => cat.id == categoriaSeleccionadaId);
				const descuentoPorcentaje = categoriaSeleccionada ? categoriaSeleccionada.descuento : 0;
				const numPrendasDescuento = categoriaSeleccionada ? categoriaSeleccionada.numeroPrendasDescuento : 0;

				let descuentoAplicado = 0;


				if (cantidad > numPrendasDescuento) {
					let subtotalSinDescuento = cantidad * precioUnitario;
					descuentoAplicado = subtotalSinDescuento * (descuentoPorcentaje / 100);
					totalDescuento += descuentoAplicado;
				}
			});

			document.getElementById('cantidadDescuento').value = totalDescuento.toFixed(2);
		}

		function calcularTotal() {
			let total = 0;
			const subtotales = document.querySelectorAll('input[name^="lineasVenta"][name$=".subtotal"]')

			subtotales.forEach(el => {
				total += parseFloat(el.value) || 0;

				precioTotalFinal.value = total.value;
			})

			document.getElementById('precioTotalFinal').value = total.toFixed(2);
		}









		const colCategoria = document.createElement('div');
		colCategoria.classList.add('col-auto');
		colCategoria.appendChild(labelCategoria);
		colCategoria.appendChild(selectCategoria);

		const colPrenda = document.createElement('div');
		colPrenda.classList.add('col-auto');
		colPrenda.appendChild(labelPrenda)
		colPrenda.appendChild(selectPrenda);

		const colCantidad = document.createElement('div');
		colCantidad.classList.add('col-auto');
		colCantidad.appendChild(labelCantidad)
		colCantidad.appendChild(inputNumero);

		const colSubtotal = document.createElement('div');
		colSubtotal.classList.add('col-auto');
		colSubtotal.appendChild(labelSubtotal);
		colSubtotal.appendChild(precio);

		const colDescontado = document.createElement('div');
		colDescontado.classList.add('col-auto');
		colDescontado.appendChild(labelPrecioDescuento)
		colDescontado.appendChild(precioDescuento)

		const colDiferencia = document.createElement('div');
		colDiferencia.classList.add('col-auto');
		colDiferencia.appendChild(labelDiferencia)
		colDiferencia.appendChild(diferenciaDescuento)

		const colEliminar = document.createElement('div')
		colEliminar.classList.add('col-auto');
		colEliminar.appendChild(btnEliminar);


		fila.appendChild(colCategoria);
		fila.appendChild(colPrenda);
		fila.appendChild(colCantidad);
		fila.appendChild(colSubtotal);
		fila.appendChild(colDiferencia);
		fila.appendChild(colDescontado);
		fila.appendChild(colEliminar);




		container.appendChild(fila);


		indiceLineaVenta++;
		console.log("Total con descuento actualizado:", precioTotalDescuento.value);
	}
