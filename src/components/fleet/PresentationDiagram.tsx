import styles from "./PresentationDiagram.module.css";

const RELATION_LINKS = [
    { id: "motorista-viagem", label: "N:M", description: "Motorista participa de várias viagens" },
    { id: "rota-viagem", label: "N:M", description: "Rota pode aparecer em várias viagens" },
    { id: "documento-veiculo", label: "1:1", description: "Documento pertence a um único veículo" },
    { id: "veiculo-manutencao", label: "1:N", description: "Veículo pode ter várias manutenções" },
] as const;

type PresentationDiagramProps = {
    compact?: boolean;
};

export function PresentationDiagram({ compact = false }: PresentationDiagramProps) {
    return (
        <section className={`${styles.section} ${compact ? styles.compact : ""}`}>
            <div className={styles.header}>
                <p className={styles.kicker}>Modelo relacional</p>
                <h2>Diagrama das relações do banco</h2>
                <p className={styles.description}>
                    Viagem conecta motorista, veículo e rota; documento e manutenção ficam ligados ao veículo.
                </p>
            </div>

            <div className={styles.canvas}>
                <div className={styles.rowTop}>
                    <div className={`${styles.node} ${styles.soft}`}>Motorista</div>
                    <div className={`${styles.linkBubble} ${styles.leftLink}`}>
                        <strong>N:M</strong>
                        <span>vários motoristas em várias viagens</span>
                    </div>
                    <div className={`${styles.node} ${styles.strong}`}>Viagem</div>
                    <div className={`${styles.linkBubble} ${styles.rightLink}`}>
                        <strong>N:M</strong>
                        <span>várias rotas em várias viagens</span>
                    </div>
                    <div className={`${styles.node} ${styles.soft}`}>Rota</div>
                </div>

                <div className={styles.columnCenter}>
                    <div className={styles.verticalConnector} />
                    <div className={`${styles.node} ${styles.strong}`}>Veiculo</div>
                    <div className={styles.verticalConnector} />
                </div>

                <div className={styles.rowBottom}>
                    <div className={`${styles.node} ${styles.accent}`}>Documento</div>
                    <div className={`${styles.linkBubble} ${styles.topRightLink}`}>
                        <strong>1:1</strong>
                        <span>um documento para um veículo</span>
                    </div>
                    <div className={`${styles.node} ${styles.accent}`}>Manutencao</div>
                    <div className={`${styles.linkBubble} ${styles.bottomRightLink}`}>
                        <strong>1:N</strong>
                        <span>um veículo com várias manutenções</span>
                    </div>
                </div>

                <div className={styles.legend}>
                    {RELATION_LINKS.map((item) => (
                        <div key={item.id} className={styles.legendItem}>
                            <strong>{item.label}</strong>
                            <span>{item.description}</span>
                        </div>
                    ))}
                </div>
            </div>
        </section>
    );
}
