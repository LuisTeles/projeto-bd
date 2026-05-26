import { DataTable, type DataColumn } from "@/components/fleet/DataTable";
import { UI_TEXT } from "@/constants/ui";
import { listRelatorioViagens } from "@/services/fleet-service";
import type { RelatorioViagem } from "@/types/fleet";

import styles from "./fleet-pages.module.css";

export const dynamic = "force-dynamic";

const DASHBOARD_COLUMNS: Array<DataColumn<RelatorioViagem>> = [
  { key: "id_motorista", label: "ID Motorista" },
  { key: "nome_motorista", label: "Motorista" },
  { key: "placa", label: "Placa" },
  { key: "modelo", label: "Modelo" },
  { key: "destino", label: "Destino" },
  {
    key: "distancia_km",
    label: "Distancia (km)",
    format: (value) => Number(value ?? 0).toFixed(1),
  },
];

export default async function Home() {
  const viagens = await listRelatorioViagens();

  return (
    <main className={styles.page}>
      <section className={styles.hero}>
        <h2>{UI_TEXT.DASHBOARD_TITLE}</h2>
        <p>{UI_TEXT.DASHBOARD_SUBTITLE}</p>
      </section>

      <DataTable
        title="Historico de Viagens"
        columns={DASHBOARD_COLUMNS}
        rows={viagens}
        emptyMessage={UI_TEXT.EMPTY_STATE}
      />
    </main>
  );
}
