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
  `precio` DECIMAL(10,2) NOT NULL,
  `fechaViaje` DATETIME NOT NULL,
  `fechaReserva` DATETIME NOT NULL,
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
