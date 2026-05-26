import { DataTable, type DataColumn } from "@/components/fleet/DataTable";
import { UI_TEXT } from "@/constants/ui";
import { listVeiculos } from "@/services/fleet-service";
import type { Veiculo } from "@/types/fleet";

import styles from "../fleet-pages.module.css";

export const dynamic = "force-dynamic";

type PageProps = {
    searchParams: Promise<{
        q?: string;
    }>;
};

const VEICULO_COLUMNS: Array<DataColumn<Veiculo>> = [
    { key: "id_veiculo", label: "ID" },
    { key: "placa", label: "Placa" },
    { key: "modelo", label: "Modelo" },
    {
        key: "km_atual",
        label: "KM Atual",
        format: (value) => Number(value ?? 0).toFixed(1),
    },
];

export default async function VeiculosPage({ searchParams }: PageProps) {
    const { q = "" } = await searchParams;
    const termo = q.trim().toLowerCase();

    const veiculos = await listVeiculos();
    const veiculosFiltrados =
        termo.length > 0
            ? veiculos.filter((veiculo) => {
                return (
                    veiculo.placa.toLowerCase().includes(termo) ||
                    veiculo.modelo.toLowerCase().includes(termo)
                );
            })
            : veiculos;

    return (
        <main className={styles.page}>
            <section className={styles.hero}>
                <h2>{UI_TEXT.VEICULOS_TITLE}</h2>
                <p>Busque por placa ou modelo.</p>
            </section>

            <form method="GET" className={styles.form}>
                <input
                    className={styles.input}
                    type="text"
                    name="q"
                    placeholder="Ex.: ABC1D23 ou Scania"
                    defaultValue={q}
                />
                <button className={styles.button} type="submit">
                    Filtrar
                </button>
            </form>

            <p className={styles.hint}>
                Total exibido: {veiculosFiltrados.length} de {veiculos.length}
            </p>

            <DataTable
                columns={VEICULO_COLUMNS}
                rows={veiculosFiltrados}
                emptyMessage={UI_TEXT.EMPTY_STATE}
            />
        </main>
    );
}
