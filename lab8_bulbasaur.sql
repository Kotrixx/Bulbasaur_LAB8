-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema lab8_bulbasaur
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lab8_bulbasaur
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lab8_bulbasaur` DEFAULT CHARACTER SET utf8 ;
USE `lab8_bulbasaur` ;

-- -----------------------------------------------------
-- Table `lab8_bulbasaur`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab8_bulbasaur`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `correo` VARCHAR(150) NOT NULL,
  `nombre` VARCHAR(80) NOT NULL,
  `apellido` VARCHAR(80) NOT NULL,
  `contrasenha` VARCHAR(64) NOT NULL,
  `contrasenha_hashed` VARCHAR(64) NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `edad` INT NOT NULL,
  `codigo` INT NOT NULL,
  `especialidad` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lab8_bulbasaur`.`Seguro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab8_bulbasaur`.`Seguro` (
  `idSeguro` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idSeguro`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lab8_bulbasaur`.`Viaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lab8_bulbasaur`.`Viaje` (
  `idViaje` INT NOT NULL AUTO_INCREMENT,
  `fechaViaje` DATE NOT NULL,
  `fechaReserva` DATE NOT NULL,
  `costoTotal` DECIMAL(10,2) NOT NULL,
  `precioBoletO` DECIMAL(10,2) NULL,
  `cantBoletos` INT NOT NULL,
  `Seguro_idSeguro` INT NOT NULL,
  `ciudadOrigen` VARCHAR(100) NULL,
  `ciudadDestino` VARCHAR(100) NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idViaje`),
  INDEX `fk_Viaje_Seguro_idx` (`Seguro_idSeguro` ASC) VISIBLE,
  INDEX `fk_Viaje_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Viaje_Seguro`
    FOREIGN KEY (`Seguro_idSeguro`)
    REFERENCES `lab8_bulbasaur`.`Seguro` (`idSeguro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Viaje_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `lab8_bulbasaur`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


USE `lab8_bulbasaur` ;
INSERT INTO `lab8_bulbasaur`.`Usuario` (`correo`, `nombre`, `apellido`,`contrasenha`, `contrasenha_hashed`, `status`, `edad`, `codigo`, `especialidad`)
VALUES ('usuario1@pucp.edu.pe', 'Usuario', 'Uno','password1', SHA2('password1', 256), 'Normal', 20, 12345678, 'Ingeniería de Telecomunicaciones'),
 ('usuario3@pucp.edu.pe', 'Usuario3', 'Tres','password3', SHA2('password3', 256), 'Silver', 28, 34567890, 'Ingeniería de Telecomunicaciones'),
('usuario2@pucp.edu.pe', 'Usuario2', 'Dos','password2', SHA2('password2', 256), 'Gold', 25, 23456789, 'Ingeniería de Telecomunicaciones'),
 ('usuario4@pucp.edu.pe', 'Usuario4', 'Tres','password4', SHA2('password4', 256), 'Platinum', 28, 12312456, 'Ingeniería de Telecomunicaciones');

INSERT INTO `lab8_bulbasaur`.`Seguro` (`nombre`) VALUES 
('Rimac'),
('Pacifico'),
('La Positiva'),
('Seguro Internacional');

INSERT INTO `lab8_bulbasaur`.`Viaje` (`idViaje`, `fechaViaje`, `fechaReserva`, `costoTotal`, `precioBoletO`, `cantBoletos`, `Seguro_idSeguro`, `ciudadOrigen`, `ciudadDestino`, `Usuario_idUsuario`) VALUES
( 12114478, '2024-06-15', '2024-05-30', 70.00, 0.00, 1, 1, 'Lima', 'Cusco', 1),
( 87654321, '2024-07-10', '2024-06-01', 374.50, 0.00, 1, 2, 'Lima', 'Arequipa', 2),
( 13656244, '2024-09-25', '2024-08-10', 1278.90, 0.00, 3, 3, 'Lima', 'Piura', 3),
( 46781457, '2024-06-25', '2024-08-26', 17842.90, 0.00, 15, 4, 'Lima', 'Trujillo', 4);
