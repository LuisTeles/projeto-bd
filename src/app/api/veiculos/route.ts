import { NextResponse } from "next/server";

import { listVeiculos } from "@/services/fleet-service";

export async function GET() {
    try {
        const veiculos = await listVeiculos();
        return NextResponse.json(veiculos, { status: 200 });
    } catch (error) {
        const message =
            error instanceof Error ? error.message : "Falha desconhecida no servidor.";

        return NextResponse.json(
            { message: "Erro ao carregar lista de veiculos.", details: message },
            { status: 500 },
        );
    }
}
