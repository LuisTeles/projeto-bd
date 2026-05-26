import Link from "next/link";

import { APP_ROUTES } from "@/constants/db";
import { UI_TEXT } from "@/constants/ui";

import styles from "./TopNav.module.css";

const NAV_ITEMS = [
    { href: APP_ROUTES.DASHBOARD, label: "Dashboard" },
    { href: APP_ROUTES.VEICULOS, label: "Veiculos" },
    { href: APP_ROUTES.MOTORISTAS, label: "Motoristas" },
] as const;

export function TopNav() {
    return (
        <header className={styles.header}>
            <div className={styles.brandBlock}>
                <p className={styles.label}>Frota</p>
                <h1>{UI_TEXT.APP_TITLE}</h1>
            </div>
            <nav className={styles.nav}>
                {NAV_ITEMS.map((item) => (
                    <Link key={item.href} href={item.href} className={styles.link}>
                        {item.label}
                    </Link>
                ))}
            </nav>
        </header>
    );
}
