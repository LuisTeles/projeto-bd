import { NextResponse } from "next/server";

import { listRelatorioViagens } from "@/services/fleet-service";

export async function GET() {
    try {
        const viagens = await listRelatorioViagens();
        return NextResponse.json(viagens, { status: 200 });
    } catch (error) {
        const message =
            error instanceof Error ? error.message : "Falha desconhecida no servidor.";

        return NextResponse.json(
            { message: "Erro ao carregar relatorio de viagens.", details: message },
            { status: 500 },
        );
    }
}
