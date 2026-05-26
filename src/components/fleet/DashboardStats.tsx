import styles from "./DashboardStats.module.css";

export type DashboardStatsProps = {
    totalVeiculos: number;
    totalMotoristas: number;
    totalViagens: number;
    mediaDistancia: number;
};

export function DashboardStats({
    totalVeiculos,
    totalMotoristas,
    totalViagens,
    mediaDistancia,
}: DashboardStatsProps) {
    const items = [
        { label: "Veiculos", value: totalVeiculos },
        { label: "Motoristas", value: totalMotoristas },
        { label: "Viagens", value: totalViagens },
        { label: "Media km", value: mediaDistancia.toFixed(1) },
    ] as const;

    return (
        <section className={styles.grid} aria-label="Resumo da base de dados">
            {items.map((item) => (
                <article key={item.label} className={styles.card}>
                    <span className={styles.label}>{item.label}</span>
                    <strong className={styles.value}>{item.value}</strong>
                </article>
            ))}
        </section>
    );
}
