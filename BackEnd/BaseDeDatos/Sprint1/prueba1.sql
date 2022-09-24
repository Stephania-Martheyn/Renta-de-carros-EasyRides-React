CREATE DATABASE `digital_booking`;
USE `digital_booking`;

CREATE TABLE `categorias` (
	id LONG AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    descripcion VARCHAR(255),
    url_img VARCHAR(500)
)