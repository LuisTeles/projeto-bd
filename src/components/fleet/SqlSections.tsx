const SQL_SECTIONS = [
  {
    title: "1. Estrutura do Banco",
    description: "Criação do schema, seleção do banco e limpeza idempotente antes da carga.",
    code: [
      "CREATE DATABASE IF NOT EXISTS frota_logistica;",
      "USE frota_logistica;",
      "DROP TRIGGER IF EXISTS trg_viagem_atualiza_km;",
      "DROP VIEW IF EXISTS vw_relatorio_viagens;",
      "DROP PROCEDURE IF EXISTS sp_registrar_manutencao;",
    ],
  },
  {
    title: "2. Tabelas e Relacionamentos",
    description: "Modelo relacional com chaves primárias, únicas e estrangeiras.",
    code: [
      "CREATE TABLE Veiculo (...);",
      "CREATE TABLE Motorista (...);",
      "CREATE TABLE Rota (...);",
      "CREATE TABLE Documento (... FK -> Veiculo);",
      "CREATE TABLE Manutencao (... FK -> Veiculo);",
      "CREATE TABLE Viagem (... FK -> Motorista, Veiculo, Rota);",
    ],
  },
  {
    title: "3. Objetos Programáveis",
    description: "Trigger, view e procedure para demonstrar automação e consulta consolidada.",
    code: [
      "CREATE TRIGGER trg_viagem_atualiza_km ...",
      "CREATE PROCEDURE sp_registrar_manutencao ...",
      "CREATE VIEW vw_relatorio_viagens AS ...",
    ],
  },
  {
    title: "4. Carga Inicial",
    description: "Cinco registros reais para cada tabela exigida no trabalho.",
    code: [
      "INSERT INTO Veiculo ...",
      "INSERT INTO Motorista ...",
      "INSERT INTO Rota ...",
      "INSERT INTO Documento ...",
      "INSERT INTO Manutencao ...",
      "INSERT INTO Viagem ...",
    ],
  },
  {
    title: "5. Acesso e Perfis",
    description: "Role, privilégios e usuários configurados para o cenário da apresentação.",
    code: [
      "CREATE ROLE IF NOT EXISTS role_logistica;",
      "GRANT SELECT, INSERT ON frota_logistica.* TO role_logistica;",
      "CREATE USER IF NOT EXISTS 'operador_frota_1'@'%';",
      "SET DEFAULT ROLE role_logistica TO 'operador_frota_1'@'%';",
    ],
  },
] as const;

import styles from "./SqlSections.module.css";

export function SqlSections() {
  return (
    <section className={styles.section}>
      <div className={styles.header}>
        <p className={styles.kicker}>Código da apresentação</p>
        <h2>Seções principais do script SQL</h2>
        <p className={styles.description}>
          Use este painel para navegar rapidamente entre as partes do banco durante a apresentação.
        </p>
      </div>

      <div className={styles.accordion}>
        {SQL_SECTIONS.map((item) => (
          <details key={item.title} className={styles.item}>
            <summary className={styles.summary}>{item.title}</summary>
            <div className={styles.content}>
              <p>{item.description}</p>
              <pre className={styles.code}>
                <code>{item.code.join("\n")}</code>
              </pre>
            </div>
          </details>
        ))}
      </div>
    </section>
  );
}
