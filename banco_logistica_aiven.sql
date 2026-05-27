-- Sistema de Gestao de Frota Logistica
-- Script para ambiente gerenciado (Aiven MySQL)
-- Execute conectado ao banco alvo (ex.: frota_logistica)

DROP TRIGGER IF EXISTS trg_viagem_atualiza_km;
DROP VIEW IF EXISTS vw_relatorio_viagens;
DROP PROCEDURE IF EXISTS sp_registrar_manutencao;

DROP TABLE IF EXISTS Viagem;
DROP TABLE IF EXISTS Manutencao;
DROP TABLE IF EXISTS Documento;
DROP TABLE IF EXISTS Rota;
DROP TABLE IF EXISTS Motorista;
DROP TABLE IF EXISTS Veiculo;

-- 1) Tabelas e relacionamentos
CREATE TABLE Veiculo (
  id_veiculo INT NOT NULL AUTO_INCREMENT,
  placa CHAR(7) NOT NULL,
  modelo VARCHAR(45) NOT NULL,
  km_atual FLOAT NOT NULL,
  PRIMARY KEY (id_veiculo),
  UNIQUE KEY uk_veiculo_placa (placa),
  CONSTRAINT chk_veiculo_km_atual CHECK (km_atual >= 0)
) ENGINE=InnoDB;

CREATE TABLE Motorista (
  id_motorista INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  cpf CHAR(11) NOT NULL,
  cnh_categoria VARCHAR(2) NOT NULL,
  PRIMARY KEY (id_motorista),
  UNIQUE KEY uk_motorista_cpf (cpf)
) ENGINE=InnoDB;

CREATE TABLE Rota (
  id_rota INT NOT NULL AUTO_INCREMENT,
  destino VARCHAR(45) NOT NULL,
  distancia_km FLOAT NOT NULL,
  PRIMARY KEY (id_rota),
  CONSTRAINT chk_rota_distancia CHECK (distancia_km > 0)
) ENGINE=InnoDB;

CREATE TABLE Documento (
  id_doc INT NOT NULL AUTO_INCREMENT,
  num_licenca VARCHAR(20) NOT NULL,
  vencimento DATE NOT NULL,
  Veiculo_idVeiculo INT NOT NULL,
  PRIMARY KEY (id_doc),
  UNIQUE KEY uk_documento_licenca (num_licenca),
  UNIQUE KEY uk_documento_veiculo (Veiculo_idVeiculo),
  CONSTRAINT fk_documento_veiculo
    FOREIGN KEY (Veiculo_idVeiculo)
    REFERENCES Veiculo (id_veiculo)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE Manutencao (
  id_manutencao INT NOT NULL AUTO_INCREMENT,
  descricao TEXT NOT NULL,
  valor_total DECIMAL(10,2) NOT NULL,
  Veiculo_idVeiculo INT NOT NULL,
  PRIMARY KEY (id_manutencao),
  CONSTRAINT chk_manutencao_valor CHECK (valor_total >= 0),
  CONSTRAINT fk_manutencao_veiculo
    FOREIGN KEY (Veiculo_idVeiculo)
    REFERENCES Veiculo (id_veiculo)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
) ENGINE=InnoDB;

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
) ENGINE=InnoDB;

-- 2) Objetos programaveis
DELIMITER $$

CREATE TRIGGER trg_viagem_atualiza_km
AFTER INSERT ON Viagem
FOR EACH ROW
BEGIN
  UPDATE Veiculo v
     SET v.km_atual = v.km_atual + (SELECT distancia_km FROM Rota WHERE id_rota = NEW.idRota)
   WHERE v.id_veiculo = NEW.idVeiculo;
END$$

CREATE PROCEDURE sp_registrar_manutencao (
  IN p_veiculo_id INT,
  IN p_descricao TEXT,
  IN p_valor_total DECIMAL(10,2)
)
BEGIN
  INSERT INTO Manutencao (descricao, valor_total, Veiculo_idVeiculo)
  VALUES (p_descricao, p_valor_total, p_veiculo_id);
END$$

DELIMITER ;

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

-- 3) Carga inicial (minimo 5 por tabela)
INSERT INTO Veiculo (id_veiculo, placa, modelo, km_atual) VALUES
  (1, 'ABC1D23', 'Mercedes Atego 1719', 120500.0),
  (2, 'EFG4H56', 'Volkswagen Delivery 11.180', 89340.5),
  (3, 'IJK7L89', 'Iveco Daily 35S14', 64210.2),
  (4, 'MNO1P23', 'Scania P 310', 210900.8),
  (5, 'QRS4T56', 'Volvo VM 270', 154770.4);

INSERT INTO Motorista (id_motorista, nome, cpf, cnh_categoria) VALUES
  (1, 'Carlos Eduardo Silva', '12345678901', 'E'),
  (2, 'Fernanda Araujo Lima', '23456789012', 'D'),
  (3, 'Joao Pedro Martins', '34567890123', 'E'),
  (4, 'Mariana Costa Souza', '45678901234', 'D'),
  (5, 'Ricardo Alves Neto', '56789012345', 'E');

INSERT INTO Rota (id_rota, destino, distancia_km) VALUES
  (1, 'Campinas', 98.5),
  (2, 'Ribeirao Preto', 312.7),
  (3, 'Santos', 83.4),
  (4, 'Sao Jose dos Campos', 108.9),
  (5, 'Sorocaba', 101.2);

INSERT INTO Documento (id_doc, num_licenca, vencimento, Veiculo_idVeiculo) VALUES
  (1, 'LIC-ATEGO-2026', '2026-12-15', 1),
  (2, 'LIC-DELIV-2026', '2026-10-10', 2),
  (3, 'LIC-IVECO-2027', '2027-01-20', 3),
  (4, 'LIC-SCANI-2026', '2026-09-01', 4),
  (5, 'LIC-VOLVO-2027', '2027-03-30', 5);

INSERT INTO Manutencao (id_manutencao, descricao, valor_total, Veiculo_idVeiculo) VALUES
  (1, 'Troca de oleo e filtros', 850.00, 1),
  (2, 'Alinhamento e balanceamento', 620.00, 2),
  (3, 'Revisao do sistema de freios', 1340.50, 3),
  (4, 'Substituicao de pneus traseiros', 4280.90, 4),
  (5, 'Reparo no sistema eletrico', 990.75, 5);

-- As viagens abaixo disparam o trigger e atualizam km_atual automaticamente.
INSERT INTO Viagem (idMotorista, idVeiculo, idRota) VALUES
  (1, 1, 1),
  (2, 2, 2),
  (3, 3, 3),
  (4, 4, 4),
  (5, 5, 5);

-- Consultas uteis de validacao rapida:
-- SELECT * FROM vw_relatorio_viagens;
-- CALL sp_registrar_manutencao(1, 'Troca preventiva de correia', 430.00);
