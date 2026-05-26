import type { Metadata } from "next";
import { Merriweather, Space_Grotesk } from "next/font/google";

import { TopNav } from "@/components/fleet/TopNav";
import { UI_TEXT } from "@/constants/ui";

import "./globals.css";

const merriweather = Merriweather({
  variable: "--font-merriweather",
  subsets: ["latin"],
  weight: ["400", "700"],
});

const spaceGrotesk = Space_Grotesk({
  variable: "--font-space-grotesk",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: UI_TEXT.APP_TITLE,
  description: "Aplicacao de consulta para o banco frota_logistica",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html
      lang="pt-BR"
      className={`${merriweather.variable} ${spaceGrotesk.variable}`}
    >
      <body>
        <div className="appShell">
          <TopNav />
          {children}
        </div>
      </body>
    </html>
  );
}
