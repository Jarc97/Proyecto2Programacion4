
DROP SCHEMA IF EXISTS `bd_votaciones` ;
CREATE SCHEMA IF NOT EXISTS `bd_votaciones` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci ;
USE `bd_votaciones` ;

DROP TABLE IF EXISTS `bd_votaciones`.`usuario` ;
CREATE TABLE IF NOT EXISTS `bd_votaciones`.`usuario` (
  `cedula` VARCHAR(12) NOT NULL,
  `apellido1` VARCHAR(45) NOT NULL,
  `apellido2` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `clave` VARCHAR(16) NOT NULL,
  `activo` TINYINT NOT NULL,
  PRIMARY KEY (`cedula`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `bd_votaciones`.`administrador` ;
CREATE TABLE IF NOT EXISTS `bd_votaciones`.`administrador` (
  `cedula` VARCHAR(12) NOT NULL,
  `apellido1` VARCHAR(45) NOT NULL,
  `apellido2` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(16) NOT NULL,
  `clave` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`cedula`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `bd_votaciones`.`partido` ;
CREATE TABLE IF NOT EXISTS `bd_votaciones`.`partido` (
  `siglas` VARCHAR(12) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `bandera` MEDIUMBLOB NULL,
  `tipo_imagen` VARCHAR(45) NULL,
  `observaciones` TEXT NULL,
  PRIMARY KEY (`siglas`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `bd_votaciones`.`votacion` ;
CREATE TABLE IF NOT EXISTS `bd_votaciones`.`votacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha_inicio` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_apertura` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_cierre` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_final` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

DROP TABLE IF EXISTS `bd_votaciones`.`votacion_partido` ;
CREATE TABLE IF NOT EXISTS `bd_votaciones`.`votacion_partido` (
  `votacion_id` INT NOT NULL,
  `partido_siglas` VARCHAR(12) NOT NULL,
  `cedula_candidato` VARCHAR(12) NOT NULL,
  `foto_candidato` MEDIUMBLOB NULL,
  `tipo_imagen` VARCHAR(45) NULL,
  `votos_obtenidos` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`votacion_id`, `partido_siglas`, `cedula_candidato`),
  INDEX `fk_votacion_partido_partido_idx` (`partido_siglas` ASC),
  INDEX `fk_votacion_partido_votacion_idx` (`votacion_id` ASC),
  INDEX `fk_votacion_partido_usuario_idx` (`cedula_candidato` ASC),
  CONSTRAINT `fk_votacion_partido_votacion`
    FOREIGN KEY (`votacion_id`)
    REFERENCES `bd_votaciones`.`votacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_votacion_partido_partido`
    FOREIGN KEY (`partido_siglas`)
    REFERENCES `bd_votaciones`.`partido` (`siglas`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_votacion_partido_usuario`
    FOREIGN KEY (`cedula_candidato`)
    REFERENCES `bd_votaciones`.`usuario` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `bd_votaciones`.`votacion_usuario` ;
CREATE TABLE IF NOT EXISTS `bd_votaciones`.`votacion_usuario` (
  `votacion_id` INT NOT NULL,
  `usuario_cedula` VARCHAR(12) NOT NULL,
  `voto_completado` TINYINT NOT NULL,
  PRIMARY KEY (`votacion_id`, `usuario_cedula`),
  INDEX `fk_votacion_usuario_usuario_idx` (`usuario_cedula` ASC),
  INDEX `fk_votacion_usuario_votacion_idx` (`votacion_id` ASC),
  CONSTRAINT `fk_votacion_usuario_votacion`
    FOREIGN KEY (`votacion_id`)
    REFERENCES `bd_votaciones`.`votacion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_votacion_usuario_usuario`
    FOREIGN KEY (`usuario_cedula`)
    REFERENCES `bd_votaciones`.`usuario` (`cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

