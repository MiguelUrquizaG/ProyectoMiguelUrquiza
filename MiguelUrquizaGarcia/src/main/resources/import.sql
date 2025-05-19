-- Insertar categorías (servicios de tintorería)
INSERT INTO categoria (id, nombre, descripcion, precio_servicio, top_venta, descuento, numero_prendas_descuento) VALUES (1, 'Lavado en seco', 'Limpieza especializada sin agua para prendas delicadas', 8.99, true, 15.0, 3);
INSERT INTO categoria (id, nombre, descripcion, precio_servicio, top_venta, descuento, numero_prendas_descuento) VALUES (2, 'Planchado', 'Servicio de planchado profesional', 5.50, true, 10.0, 5);
INSERT INTO categoria (id, nombre, descripcion, precio_servicio, top_venta, descuento, numero_prendas_descuento) VALUES (3, 'Quitamanchas', 'Tratamiento especializado para eliminar manchas difíciles', 7.25, false, 0.0, 0);
INSERT INTO categoria (id, nombre, descripcion, precio_servicio, top_venta, descuento, numero_prendas_descuento) VALUES (4, 'Teñido', 'Servicio de teñido personalizado para renovar prendas', 12.99, false, 20.0, 2);
INSERT INTO categoria (id, nombre, descripcion, precio_servicio, top_venta, descuento, numero_prendas_descuento) VALUES (5, 'Lavado estándar', 'Lavado con agua y detergentes suaves', 6.50, true, 12.0, 4);
ALTER TABLE categoria ALTER COLUMN id RESTART WITH 6;


-- Insertar prendas
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (1, 'Traje formal', 'Traje de dos piezas para ocasiones especiales', 'https://img.joomcdn.net/edd88594fc8b91ee8f308f6ada845f403db4bba7_original.jpeg', true, 1);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (2, 'Vestido de seda', 'Vestido elegante de seda natural', 'https://lagatavoladora.com/wp-content/uploads/2024/07/vestido-de-seda-celeste-Oshun-1.jpg', false, 1);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (3, 'Camisa de algodón', 'Camisa para uso diario', 'https://www.vestuariolaboral.com/82032-large_default/camisa-algodon-manga-larga-velilla-533.jpg', true, 2);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (4, 'Pantalón de lino', 'Pantalón ligero para verano', 'https://www.lidl.es/media/product/0/0/5/6/6/5/9/pantalon-de-lino-para-mujer-zoom--5.jpg', false, 2);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (5, 'Abrigo de lana', 'Abrigo grueso para invierno con mancha de café', 'https://static.zara.net/assets/public/6fe9/4859/29994a44ace6/a7a8d61ffa9d/01008020442-e1/01008020442-e1.jpg?ts=1733328153443&w=1024', true, 3);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (6, 'Chaqueta de ante', 'Chaqueta con manchas de grasa', 'https://e00-telva.uecdn.es/assets/multimedia/imagenes/2024/10/07/17283010323767.jpg', false, 3);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (7, 'Vaqueros desgastados', 'Vaqueros para renovar con tinte', 'https://www.dhresource.com/webp/m/0x0/f2/albu/g10/M00/EC/A3/rBVaWV6YGBCAFBjhAAIKRf8URv4764.jpg', true, 4);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (8, 'Camiseta descolorida', 'Blusa para recuperar color original', 'https://image.made-in-china.com/155f0j00ZbkqHWSnMEcs/Custom-Oversize-T-Shirt-Manufacturer-Casual-Vintage-Heavyweight-Cotton-Acid-Wash-Sun-Faded-T-Shirts.webp', false, 4);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (9, 'Camiseta deportiva', 'Camiseta para ejercicio', 'https://printalba.com/wp-content/uploads/2021/06/Delantero-Milan.jpg', true, 5);
INSERT INTO prenda (id, nombre, descripcion, url_imagen, top_venta, categoria_id) VALUES (10, 'Falda plisada', 'Falda con pliegues elegantes', 'https://img.ltwebstatic.com/images3_spmp/2025/02/13/37/1739431020c1c604e9d2d6d6a47d339554fe198826_thumbnail_405x.webp', false, 5);
ALTER TABLE prenda ALTER COLUMN id RESTART WITH 11;