# Sistema de Gestao de Frota Logistica

Aplicacao para consulta e apresentacao de dados de uma frota logistica, com banco MySQL modelado manualmente em SQL e interface web em Next.js.

## Entregas do Projeto

1. Script SQL principal em MySQL: `banco_logistica.sql`
2. Script SQL alternativo para ambiente Aiven: `banco_logistica_aiven.sql`
3. Frontend em Next.js para visualizar dashboard, diagrama, veiculos e motoristas

## Stack

- Next.js 16.2.6 + React 19.2.4 + TypeScript 5
- Prisma Client 6.19.0
- MySQL 8.0+
- ESLint 9

## Funcionalidades

- Dashboard com resumo das viagens a partir da view `vw_relatorio_viagens`
- Tabela com historico de viagens
- Secoes de apresentacao do SQL do projeto
- Pagina de diagrama da modelagem
- Listagem de veiculos
- Listagem de motoristas
- Rotas de API para viagens, veiculos e motoristas

## Estrutura Essencial

- `banco_logistica.sql`: schema, inserts, role, usuarios, trigger, view e procedure
- `banco_logistica_aiven.sql`: variante do script para execucao em ambiente Aiven
- `prisma/schema.prisma`: mapeamento das tabelas existentes no banco
- `src/lib/prisma.ts`: instancia compartilhada do Prisma Client
- `src/services/fleet-service.ts`: camada de acesso aos dados
- `src/app/page.tsx`: dashboard principal
- `src/app/diagrama/page.tsx`: pagina do diagrama
- `src/app/veiculos/page.tsx`: listagem de veiculos
- `src/app/motoristas/page.tsx`: listagem de motoristas
- `src/app/api/*`: endpoints para viagens, veiculos e motoristas
- `src/components/fleet/*`: componentes visuais do dashboard e navegacao

## Como Executar

1. Configure um banco MySQL e execute o script SQL adequado ao seu ambiente:

```sql
SOURCE banco_logistica.sql;
```

Se estiver usando o ambiente configurado para Aiven, utilize:

```sql
SOURCE banco_logistica_aiven.sql;
```

2. Crie o arquivo `.env.local` com base em `.env.example`:

```env
DATABASE_URL="mysql://root:root@localhost:3306/frota_logistica"
APP_BASE_URL="http://localhost:3000"
```

3. Instale as dependencias:

```bash
npm install
```

4. Gere o Prisma Client:

```bash
npm run prisma:generate
```

5. Inicie o servidor de desenvolvimento:

```bash
npm run dev
```

6. Abra a aplicacao em:

`http://localhost:3000`

## Scripts Disponiveis

- `npm run dev`: inicia a aplicacao em modo de desenvolvimento
- `npm run build`: gera a build de producao
- `npm run start`: inicia a aplicacao em modo de producao
- `npm run lint`: executa o lint do projeto
- `npm run prisma:generate`: gera o Prisma Client

## Rotas da Aplicacao

- `/`: dashboard principal
- `/diagrama`: visualizacao do diagrama
- `/veiculos`: listagem de veiculos
- `/motoristas`: listagem de motoristas

## Nota de Entrega

O arquivo SQL exigido pelo trabalho permanece na raiz do repositorio com o nome `banco_logistica.sql`.
