INSERT INTO Cliente (nombre, apellido, numeroDNI) VALUES
('Juan', 'Pérez', '12345678'),
('María', 'Gómez', '87654321'),
('Carlos', 'López', '11223344');

INSERT INTO Producto (codigo, descripcion, stock, precio) VALUES
('P001', 'Laptop', 10, 1200.00),
('P002', 'Mouse', 50, 25.50),
('P003', 'Teclado', 30, 85.75);

INSERT INTO Factura (id_cliente, total) VALUES
(1, 1225.50),
(2, 1200.00),
(3, 171.50);

INSERT INTO DETALLE_FACTURA  (id_factura, producto_id, cantidad, precio) VALUES
(1, 1, 1, 1200.00),
(1, 2, 1, 25.50),
(2, 1, 1, 1200.00),
(3, 2, 2, 25.50),
(3, 3, 1, 85.75);
