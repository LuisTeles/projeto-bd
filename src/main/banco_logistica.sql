-- Sistema de Gestao de Frota Logistica
-- Script manual MySQL 8.0+

CREATE DATABASE IF NOT EXISTS frota_logistica
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE frota_logistica;

-- Limpeza idempotente de objetos programaveis
DROP TRIGGER IF EXISTS trg_viagem_atualiza_km;
DROP VIEW IF EXISTS vw_relatorio_viagens;
DROP PROCEDURE IF EXISTS sp_registrar_manutencao;

-- Limpeza idempotente de tabelas
DROP TABLE IF EXISTS Viagem;
DROP TABLE IF EXISTS Manutencao;
DROP TABLE IF EXISTS Documento;
DROP TABLE IF EXISTS Rota;
DROP TABLE IF EXISTS Motorista;
DROP TABLE IF EXISTS Veiculo;

-- Tabelas e relacionamentos
CREATE TABLE Veiculo (
  id_veiculo INT NOT NULL AUTO_INCREMENT,
  placa CHAR(7) NOT NULL,
  modelo VARCHAR(45) NOT NULL,
  km_atual DOUBLE NOT NULL,
  PRIMARY KEY (id_veiculo),
  UNIQUE KEY uk_veiculo_placa (placa),
  CONSTRAINT chk_veiculo_km_atual CHECK (km_atual >= 0)
);

CREATE TABLE Motorista (
  id_motorista INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  cpf CHAR(11) NOT NULL,
  cnh_categoria VARCHAR(2) NOT NULL,
  PRIMARY KEY (id_motorista),
  UNIQUE KEY uk_motorista_cpf (cpf)
);

CREATE TABLE Rota (
  id_rota INT NOT NULL AUTO_INCREMENT,
  destino VARCHAR(45) NOT NULL,
  distancia_km FLOAT NOT NULL,
  PRIMARY KEY (id_rota),
  CONSTRAINT chk_rota_distancia CHECK (distancia_km > 0)
);

CREATE TABLE Documento (
  id_doc INT NOT NULL AUTO_INCREMENT,
  num_licenca VARCHAR(20) NOT NULL,
  vencimento DATE NOT NULL,
  idVeiculo INT NOT NULL,
  PRIMARY KEY (id_doc),
  UNIQUE KEY uk_documento_licenca (num_licenca),
  UNIQUE KEY uk_documento_veiculo (idVeiculo),
  CONSTRAINT fk_documento_veiculo
    FOREIGN KEY (idVeiculo)
    REFERENCES Veiculo (id_veiculo)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE Manutencao (
  id_manutencao INT NOT NULL AUTO_INCREMENT,
  descricao TEXT NOT NULL,
  valor_total DECIMAL(10,2) NOT NULL,
  idVeiculo INT NOT NULL,
  PRIMARY KEY (id_manutencao),
  CONSTRAINT chk_manutencao_valor CHECK (valor_total >= 0),
  CONSTRAINT fk_manutencao_veiculo
    FOREIGN KEY (idVeiculo)
    REFERENCES Veiculo (id_veiculo)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE Viagem (
  idMotorista INT NOT NULL,
  idVeiculo INT NOT NULL,
  idRota INT NOT NULL,
  PRIMARY KEY (idMotorista, idVeiculo, idRota),
  CONSTRAINT fk_viagem_motorista
    FOREIGN KEY (idMotorista)
    REFERENCES Motorista (id_motorista)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  CONSTRAINT fk_viagem_veiculo
    FOREIGN KEY (idVeiculo)
    REFERENCES Veiculo (id_veiculo)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  CONSTRAINT fk_viagem_rota
    FOREIGN KEY (idRota)
    REFERENCES Rota (id_rota)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);


CREATE VIEW vw_relatorio_viagens AS
SELECT
  v.idMotorista AS id_motorista,
  m.nome AS nome_motorista,
  v.idVeiculo AS id_veiculo,
  ve.placa,
  ve.modelo,
  v.idRota AS id_rota,
  r.destino,
  r.distancia_km
FROM Viagem v
JOIN Motorista m ON m.id_motorista = v.idMotorista
JOIN Veiculo ve ON ve.id_veiculo = v.idVeiculo
JOIN Rota r ON r.id_rota = v.idRota;

SELECT * FROM Veiculo;
SELECT * FROM Motorista;
SELECT * FROM documento;
SELECT * FROM rota;
SELECT * FROM Manutencao;
SELECT * FROM viagem;

-- Consultas uteis de validacao rapida:
SELECT * FROM vw_relatorio_viagens;


