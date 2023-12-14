CREATE DATABASE IF NOT EXISTS EquiposInformaticos;
USE EquiposInformaticos;
CREATE TABLE IF NOT EXISTS Equipos (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    tipoEquipo VARCHAR(70),
    Marca VARCHAR(50) NOT NULL,
    Modelo VARCHAR(50) NOT NULL,
    NumeroSerie VARCHAR(50) NOT NULL CHECK (NumeroSerie REGEXP '^NS[0-9]{3}$'),
    EspecificacionesTecnicas TEXT,
    EstadoMantenimiento VARCHAR(50) NOT NULL
);
INSERT INTO Equipos (tipoEquipo, Marca, Modelo, NumeroSerie, EspecificacionesTecnicas, EstadoMantenimiento)
VALUES ('laptopm', 'Xiaomi', 'gama alta', 'NS123', 'fhhfh fhhf', 'en reparacion'),
       ('NS456', 'Apple', 'gama baja', 'NS456','gagagga gafaf', 'En reparación'),
       ('NS789', 'Linux', 'gama media','NS789', 'omar !codeFxRoma', 'Malo');
UPDATE Equipos
SET Marca = 'Amazon', EstadoMantenimiento = 'Regular'
WHERE ID = 2;
DELETE FROM Equipos
WHERE ID = 2;
SELECT * FROM Equipos;
-- DROP DATABASE EquiposInformaticos;
