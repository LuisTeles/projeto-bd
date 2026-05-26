import { DashboardStats } from "@/components/fleet/DashboardStats";
import { DataTable, type DataColumn } from "@/components/fleet/DataTable";
import { PresentationDiagram } from "@/components/fleet/PresentationDiagram";
import { SqlSections } from "@/components/fleet/SqlSections";
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
  const totalVeiculos = new Set(viagens.map((viagem) => viagem.id_veiculo)).size;
  const totalMotoristas = new Set(
    viagens.map((viagem) => viagem.id_motorista),
  ).size;
  const totalViagens = viagens.length;
  const mediaDistancia =
    totalViagens === 0
      ? 0
      : viagens.reduce((sum, viagem) => sum + Number(viagem.distancia_km), 0) /
      totalViagens;

  return (
    <main className={styles.page}>
      <section className={styles.hero}>
        <h2>{UI_TEXT.DASHBOARD_TITLE}</h2>
        <p>{UI_TEXT.DASHBOARD_SUBTITLE}</p>
      </section>

      <section className={styles.presentationGrid}>
        <div className={styles.presentationMain}>
          <DashboardStats
            totalVeiculos={totalVeiculos}
            totalMotoristas={totalMotoristas}
            totalViagens={totalViagens}
            mediaDistancia={mediaDistancia}
          />

          <SqlSections />

          <PresentationDiagram compact />
        </div>
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
