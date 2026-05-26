import { DataTable, type DataColumn } from "@/components/fleet/DataTable";
import { UI_TEXT } from "@/constants/ui";
import { listMotoristas } from "@/services/fleet-service";
import type { Motorista } from "@/types/fleet";

import styles from "../fleet-pages.module.css";

export const dynamic = "force-dynamic";

type PageProps = {
    searchParams: Promise<{
        q?: string;
    }>;
};

const MOTORISTA_COLUMNS: Array<DataColumn<Motorista>> = [
    { key: "id_motorista", label: "ID" },
    { key: "nome", label: "Nome" },
    { key: "cpf", label: "CPF" },
    { key: "cnh_categoria", label: "CNH" },
];

export default async function MotoristasPage({ searchParams }: PageProps) {
    const { q = "" } = await searchParams;
    const termo = q.trim().toLowerCase();

    const motoristas = await listMotoristas();
    const motoristasFiltrados =
        termo.length > 0
            ? motoristas.filter((motorista) => {
                return (
                    motorista.nome.toLowerCase().includes(termo) ||
                    motorista.cpf.includes(termo)
                );
            })
            : motoristas;

    return (
        <main className={styles.page}>
            <section className={styles.hero}>
                <h2>{UI_TEXT.MOTORISTAS_TITLE}</h2>
                <p>Busque por nome ou CPF.</p>
            </section>

            <form method="GET" className={styles.form}>
                <input
                    className={styles.input}
                    type="text"
                    name="q"
                    placeholder="Ex.: Carlos ou 12345678901"
                    defaultValue={q}
                />
                <button className={styles.button} type="submit">
                    Filtrar
                </button>
            </form>

            <p className={styles.hint}>
                Total exibido: {motoristasFiltrados.length} de {motoristas.length}
            </p>

            <DataTable
                columns={MOTORISTA_COLUMNS}
                rows={motoristasFiltrados}
                emptyMessage={UI_TEXT.EMPTY_STATE}
            />
        </main>
    );
}
