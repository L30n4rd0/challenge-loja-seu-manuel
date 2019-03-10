-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bd_loja_seu_manuel
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bd_loja_seu_manuel` ;

-- -----------------------------------------------------
-- Schema bd_loja_seu_manuel
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bd_loja_seu_manuel` ;
USE `bd_loja_seu_manuel` ;

-- -----------------------------------------------------
-- Table `bd_loja_seu_manuel`.`produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd_loja_seu_manuel`.`produto` ;

CREATE TABLE IF NOT EXISTS `bd_loja_seu_manuel`.`produto` (
  `id_produto` INT(11) NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(20) NULL,
  `nome` VARCHAR(45) NULL,
  `descricao` VARCHAR(100) NULL,
  `estoque` INT NULL,
  `preco` DOUBLE NULL,
  PRIMARY KEY (`id_produto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_loja_seu_manuel`.`pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd_loja_seu_manuel`.`pedido` ;

CREATE TABLE IF NOT EXISTS `bd_loja_seu_manuel`.`pedido` (
  `id_pedido` INT(11) NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(20) NULL,
  `data_compra` DATE NULL,
  `nome_comprador` VARCHAR(45) NULL,
  `estado` VARCHAR(15) NULL,
  `valor_frete` DOUBLE NULL,
  `valor_total` DOUBLE NULL,
  PRIMARY KEY (`id_pedido`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_loja_seu_manuel`.`item_pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd_loja_seu_manuel`.`item_pedido` ;

CREATE TABLE IF NOT EXISTS `bd_loja_seu_manuel`.`item_pedido` (
  `id_item_pedido` INT(11) NOT NULL AUTO_INCREMENT,
  `quantidade` INT NULL,
  `preco_produto_venda` DOUBLE NULL,
  `valor_parcial` DOUBLE NULL,
  `fk_id_produto` INT(11) NOT NULL,
  `fk_id_pedido` INT(11) NOT NULL,
  PRIMARY KEY (`id_item_pedido`),
  CONSTRAINT `fk_item_pedido_produto`
    FOREIGN KEY (`fk_id_produto`)
    REFERENCES `bd_loja_seu_manuel`.`produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_pedido_pedido`
    FOREIGN KEY (`fk_id_pedido`)
    REFERENCES `bd_loja_seu_manuel`.`pedido` (`id_pedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bd_loja_seu_manuel`.`atributo_customizavel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bd_loja_seu_manuel`.`atributo_customizavel` ;

CREATE TABLE IF NOT EXISTS `bd_loja_seu_manuel`.`atributo_customizavel` (
  `id_atributo` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(20) NULL,
  `valor` VARCHAR(20) NULL,
  `fk_id_produto` INT(11) NOT NULL,
  PRIMARY KEY (`id_atributo`),
  CONSTRAINT `fk_atributo_produto`
    FOREIGN KEY (`fk_id_produto`)
    REFERENCES `bd_loja_seu_manuel`.`produto` (`id_produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
