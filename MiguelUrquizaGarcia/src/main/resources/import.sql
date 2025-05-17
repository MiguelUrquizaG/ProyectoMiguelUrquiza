-- Insertar categorías (servicios de tintorería)
INSERT INTO categoria (id, nombre, descripcion, precio_servicio, top_venta, descuento, numero_prendas_descuento) VALUES (1, 'Lavado en seco', 'Limpieza especializada sin agua para prendas delicadas', 8.99, true, 15.0, 3);
INSERT INTO categoria (id, nombre, descripcion, precio_servicio, top_venta, descuento, numero_prendas_descuento) VALUES (2, 'Planchado', 'Servicio de planchado profesional', 5.50, true, 10.0, 5);
INSERT INTO categoria (id, nombre, descripcion, precio_servicio, top_venta, descuento, numero_prendas_descuento) VALUES (3, 'Quitamanchas', 'Tratamiento especializado para eliminar manchas difíciles', 7.25, false, 0.0, 0);
INSERT INTO categoria (id, nombre, descripcion, precio_servicio, top_venta, descuento, numero_prendas_descuento) VALUES (4, 'Teñido', 'Servicio de teñido personalizado para renovar prendas', 12.99, false, 20.0, 2);
INSERT INTO categoria (id, nombre, descripcion, precio_servicio, top_venta, descuento, numero_prendas_descuento) VALUES (5, 'Lavado estándar', 'Lavado con agua y detergentes suaves', 6.50, true, 12.0, 4);

-- Insertar prendas
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (1, 'Traje formal', 'Traje de dos piezas para ocasiones especiales', 'img/traje-formal.jpg', true, 1);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (2, 'Vestido de seda', 'Vestido elegante de seda natural', 'img/vestido-seda.jpg', false, 1);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (3, 'Camisa de algodón', 'Camisa para uso diario', 'img/camisa-algodon.jpg', true, 2);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (4, 'Pantalón de lino', 'Pantalón ligero para verano', 'img/pantalon-lino.jpg', false, 2);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (5, 'Abrigo de lana', 'Abrigo grueso para invierno con mancha de café', 'img/abrigo-lana.jpg', true, 3);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (6, 'Chaqueta de ante', 'Chaqueta con manchas de grasa', 'img/chaqueta-ante.jpg', false, 3);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (7, 'Vaqueros desgastados', 'Vaqueros para renovar con tinte', 'img/vaqueros-desgastados.jpg', true, 4);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (8, 'Blusa descolorida', 'Blusa para recuperar color original', 'img/blusa-descolorida.jpg', false, 4);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (9, 'Camiseta deportiva', 'Camiseta para ejercicio', 'img/camiseta-deportiva.jpg', true, 5);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (10, 'Falda plisada', 'Falda con pliegues elegantes', 'img/falda-plisada.jpg', false, 5);