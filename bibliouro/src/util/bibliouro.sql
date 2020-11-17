-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bibliouro
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bibliouro
-- -----------------------------------------------------
DROP SCHEMA bibliouro;
CREATE SCHEMA IF NOT EXISTS bibliouro DEFAULT CHARACTER SET utf8 ;
USE bibliouro ;

-- -----------------------------------------------------
-- Table bibliouro.Autor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bibliouro.Autor (
  idAutor INT NOT NULL,
  nome VARCHAR(100) NOT NULL,
  Autoria_idAutoria INT NOT NULL,
  PRIMARY KEY (idAutor))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bibliouro.Estante
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bibliouro.Estante (
  idEstante INT NOT NULL,
  codigoestante VARCHAR(45) NULL,
  PRIMARY KEY (idEstante))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bibliouro.Unidade
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bibliouro.Unidade (
  idUnidade INT NOT NULL,
  nome VARCHAR(100) NOT NULL,
  escola VARCHAR(1) NOT NULL,
  PRIMARY KEY (idUnidade))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bibliouro.Pessoa
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bibliouro.Pessoa (
  idPessoa INT NOT NULL,
  nomepessoa VARCHAR(100) NOT NULL,
  telefone VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  cpf VARCHAR(45) NOT NULL,
  rua VARCHAR(45) NOT NULL,
  bairro VARCHAR(45) NOT NULL,
  numero VARCHAR(45) NOT NULL,
  matricula VARCHAR(45) NULL,
  status VARCHAR(1) NOT NULL,
  PRIMARY KEY (idPessoa))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bibliouro.TipoAcervo
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bibliouro.TipoAcervo (
  idTipoAcervo INT NOT NULL,
  Descricao VARCHAR(45) NOT NULL,
  PRIMARY KEY (idTipoAcervo))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bibliouro.Prateleira
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bibliouro.Prateleira (
  idPrateleira INT NOT NULL,
  idEstante INT NOT NULL,
  codigoPE VARCHAR(45) NOT NULL,
  PRIMARY KEY (idPrateleira),
  FOREIGN KEY (idEstante) REFERENCES bibliouro.Estante(idEstante))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bibliouro.Acervo
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bibliouro.Acervo (
  idAcervo INT NOT NULL,
  idTipoAcervo INT NOT NULL,
  idPrateleira INT NOT NULL,
  idUnidade INT NOT NULL,
  nome VARCHAR(100) NOT NULL,
  totalexemplares INT NOT NULL,
  codigochamada VARCHAR(50) NOT NULL,
  baixado VARCHAR(1) NOT NULL,
  TipoAcervo_idTipoAcervo INT NOT NULL,
  Prateleira_idPrateleira INT NOT NULL,
  Unidade_idUnidade INT NOT NULL,
  PRIMARY KEY (idAcervo),
  FOREIGN KEY (idTipoAcervo) REFERENCES TipoAcervo(idTipoAcervo),
  FOREIGN KEY (idPrateleira) REFERENCES Prateleira(idPrateleira),
  FOREIGN KEY (idUnidade) REFERENCES Unidade(idUnidade))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bibliouro.Gestor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bibliouro.Gestor (
  idGestor INT NOT NULL,
  idPessoa INT NOT NULL,
  login VARCHAR(45) NOT NULL,
  senha VARCHAR(45) NOT NULL,
  Pessoa_idPessoa INT NOT NULL,
  PRIMARY KEY (idGestor),
FOREIGN KEY (idPessoa) REFERENCES bibliouro.Pessoa (idPessoa))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bibliouro.Autor_has_Acervo
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bibliouro.Autor_has_Acervo (
  Autor_idAutor INT NOT NULL,
  Acervo_idAcervo INT NOT NULL,
  FOREIGN KEY (Autor_idAutor) REFERENCES bibliouro.Autor (idAutor),
  FOREIGN KEY (Acervo_idAcervo) REFERENCES bibliouro.Acervo (idAcervo))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table bibliouro.Emprestimo
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS bibliouro.Emprestimo (
  idEmprestimo INT NOT NULL,
  datainicio DATE NOT NULL,
  datafim DATE NOT NULL,
  datadevolucao VARCHAR(45) NULL,
  Acervo_idAcervo INT NOT NULL,
  Pessoa_idPessoa INT NOT NULL,
  PRIMARY KEY (idEmprestimo),
  INDEX fk_Emprestimo_Acervo1_idx (Acervo_idAcervo ASC) VISIBLE,
  INDEX fk_Emprestimo_Pessoa1_idx (Pessoa_idPessoa ASC) VISIBLE,
  CONSTRAINT fk_Emprestimo_Acervo1
    FOREIGN KEY (Acervo_idAcervo)
    REFERENCES bibliouro.Acervo (idAcervo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Emprestimo_Pessoa1
    FOREIGN KEY (Pessoa_idPessoa)
    REFERENCES bibliouro.Pessoa (idPessoa)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- TRIGGERS --

DELIMITER $ 
CREATE TRIGGER Tgr_Insert_Emprest_Exemp
AFTER INSERT ON emprestimo_exemplar FOR EACH ROW
BEGIN 
UPDATE exemplar
SET quantdisponivel = quantdisponivel - 1
WHERE exemplar.idExemplar = idExemplar; 
END$ 
DELIMITER;