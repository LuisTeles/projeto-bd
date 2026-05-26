import styles from "./DataTable.module.css";

type CellValue = string | number | boolean | null | undefined;
type ColumnKey<T> = Extract<keyof T, string>;

export type DataColumn<T extends object> = {
    key: ColumnKey<T>;
    label: string;
    format?: (value: CellValue, row: T) => string;
};

interface DataTableProps<T extends object> {
    title?: string;
    columns: Array<DataColumn<T>>;
    rows: T[];
    emptyMessage: string;
}

export function DataTable<T extends object>({
    title,
    columns,
    rows,
    emptyMessage,
}: DataTableProps<T>) {
    return (
        <section className={styles.wrapper}>
            {title ? <h2 className={styles.title}>{title}</h2> : null}
            <div className={styles.tableContainer}>
                <table className={styles.table}>
                    <thead>
                        <tr>
                            {columns.map((column) => (
                                <th key={String(column.key)}>{column.label}</th>
                            ))}
                        </tr>
                    </thead>
                    <tbody>
                        {rows.length === 0 ? (
                            <tr>
                                <td colSpan={columns.length} className={styles.emptyRow}>
                                    {emptyMessage}
                                </td>
                            </tr>
                        ) : (
                            rows.map((row, rowIndex) => (
                                <tr key={rowIndex}>
                                    {columns.map((column) => {
                                        const rawValue = row[column.key] as CellValue;
                                        const value = column.format
                                            ? column.format(rawValue, row)
                                            : String(rawValue ?? "");

                                        return <td key={`${rowIndex}-${String(column.key)}`}>{value}</td>;
                                    })}
                                </tr>
                            ))
                        )}
                    </tbody>
                </table>
            </div>
        </section>
    );
}
