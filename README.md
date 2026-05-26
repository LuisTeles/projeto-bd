# Sistema de Gestao de Frota Logistica

Projeto com duas entregas principais:

1. Script SQL manual completo em MySQL: `banco_logistica.sql`
2. Frontend Next.js (App Router) para consultar viagens, veiculos e motoristas

## Stack

- Next.js 16 + React 19 + TypeScript
- Prisma Client (conexao MySQL via `DATABASE_URL`)
- MySQL 8.0+

## Estrutura Essencial

- `banco_logistica.sql`: schema, inserts, role, usuarios, trigger, view e procedure
- `prisma/schema.prisma`: mapeamento das tabelas existentes no banco
- `src/lib/prisma.ts`: singleton do Prisma Client
- `src/services/fleet-service.ts`: camada de acesso aos dados
- `src/app/api/*`: rotas para viagens, veiculos e motoristas
- `src/app/page.tsx`: dashboard (`vw_relatorio_viagens`)
- `src/app/veiculos/page.tsx`: listagem de veiculos
- `src/app/motoristas/page.tsx`: listagem de motoristas

## Como Executar

1. Configure o banco MySQL e rode o script SQL completo:

```sql
SOURCE banco_logistica.sql;
```

2. Crie o arquivo `.env.local` usando `.env.example` como base:

```env
DATABASE_URL="mysql://root:root@localhost:3306/frota_logistica"
APP_BASE_URL="http://localhost:3000"
```

3. Instale dependencias e gere o client do Prisma:

```bash
npm install
npm run prisma:generate
```

4. Suba a aplicacao:

```bash
npm run dev
```

5. Abra no navegador:

`http://localhost:3000`

## Nota de Entrega

O arquivo SQL exigido pelo trabalho esta na raiz do repositorio com o nome `banco_logistica.sql`.
