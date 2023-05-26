-- Crear la base de datos
CREATE DATABASE almacen;

-- Usar la base de datos
USE almacen;

-- Creación de la tabla de Categoría
CREATE TABLE Categoria (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL
);

-- Insertar categorías
INSERT INTO Categoria (nombre) VALUES
  ('Bebidas'),
  ('Carne'),
  ('Pescado'),
  ('Limpieza'),
  ('Frutas'),
  ('Verduras'),
  ('Panadería'),
  ('Lácteos'),
  ('Cereales');

-- Creación de la tabla de Producto
CREATE TABLE Producto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  codigo VARCHAR(20) NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  cantidad INT NOT NULL,
  categoria_id INT,
  FOREIGN KEY (categoria_id) REFERENCES Categoria(id) ON DELETE CASCADE
);

-- Insertar productos
INSERT INTO Producto (codigo, nombre, precio, cantidad, categoria_id) VALUES
  ('001', 'Coca-Cola', 1.5, 50, 1), -- Bebidas
  ('002', 'Agua mineral', 0.8, 100, 1), -- Bebidas
  ('003', 'Pollo', 5.99, 20, 2), -- Carne
  ('004', 'Filete de ternera', 12.99, 10, 2), -- Carne
  ('005', 'Salmón', 9.99, 15, 3), -- Pescado
  ('006', 'Merluza', 7.99, 12, 3), -- Pescado
  ('007', 'Detergente', 3.5, 30, 4), -- Limpieza
  ('008', 'Limpiacristales', 2.99, 40, 4), -- Limpieza
  ('009', 'Leche', 1.2, 25, 1), -- Bebidas
  ('010', 'Cerveza', 1.8, 40, 1), -- Bebidas
  ('011', 'Chuleta de cerdo', 6.99, 15, 2), -- Carne
  ('012', 'Solomillo de cerdo', 9.99, 8, 2), -- Carne
  ('013', 'Atún', 8.99, 10, 3), -- Pescado
  ('014', 'Bacalao', 6.99, 8, 3), -- Pescado
  ('015', 'Lavavajillas', 2.99, 25, 4), -- Limpieza
  ('016', 'Limpiador multiusos', 2.5, 35, 4), -- Limpieza
  ('017', 'Zumo de naranja', 2.2, 30, 1), -- Bebidas
  ('018', 'Vino tinto', 6.5, 20, 1), -- Bebidas
  ('019', 'Ternera picada', 7.99, 15, 2), -- Carne
  ('020', 'Lomo de cerdo', 8.99, 12, 2), -- Carne
  ('021', 'Manzanas', 2.5, 30, 5), -- Frutas
  ('022', 'Naranjas', 3.0, 50, 5), -- Frutas
  ('023', 'Plátanos', 1.5, 40, 5), -- Frutas
  ('024', 'Fresas', 3.5, 20, 5), -- Frutas
  ('025', 'Zanahorias', 1.2, 60, 6), -- Verduras
  ('026', 'Tomates', 2.0, 50, 6), -- Verduras
  ('027', 'Patatas', 1.0, 80, 6), -- Verduras
  ('028', 'Brócoli', 2.5, 30, 6), -- Verduras
  ('029', 'Pan blanco', 1.2, 30, 7), -- Panadería
  ('030', 'Pan integral', 1.5, 25, 7), -- Panadería
  ('031', 'Baguette', 1.3, 20, 7), -- Panadería
  ('032', 'Croissants', 2.0, 15, 7), -- Panadería
  ('033', 'Leche entera', 1.2, 30, 8), -- Lácteos
  ('034', 'Yogur natural', 0.8, 40, 8), -- Lácteos
  ('035', 'Queso cheddar', 2.5, 15, 8), -- Lácteos
  ('036', 'Mantequilla', 1.8, 20, 8), -- Lácteos
  ('037', 'Cereales de chocolate', 3.5, 20, 9), -- Cereales
  ('038', 'Avena', 2.5, 30, 9), -- Cereales
  ('039', 'Muesli', 3.0, 25, 9), -- Cereales
  ('040', 'Cereales de miel', 3.2, 20, 9); -- Cereales

-- Creación de la tabla de Oferta
CREATE TABLE Oferta (
  id INT AUTO_INCREMENT PRIMARY KEY,
  producto_id INT,
  precio_oferta DECIMAL(10,2) NOT NULL,
  descuento INT NOT NULL,
  fecha_inicio DATE NOT NULL,
  fecha_fin DATE NOT NULL,
  FOREIGN KEY (producto_id) REFERENCES Producto(id) ON DELETE CASCADE
);
