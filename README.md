## 🗄️ Base de Datos - lab07_bueno

A continuación se muestra el script SQL que fue utilizado para crear y poblar la base de datos del proyecto:

```sql
CREATE DATABASE lab07_bueno;
USE lab07_bueno;

CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL
);

CREATE TABLE venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (producto_id) REFERENCES producto(id)
);

INSERT INTO producto (nombre, precio) VALUES
('Laptop', 1200.00),
('Mouse', 25.50),
('Teclado', 45.00),
('Monitor', 250.00);

INSERT INTO venta (producto_id, cantidad, total)
VALUES
(1, 2, 2400.00),
(2, 1, 25.50),
(3, 3, 135.00);
