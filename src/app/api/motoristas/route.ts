import { NextResponse } from "next/server";

import { listMotoristas } from "@/services/fleet-service";

export async function GET() {
    try {
        const motoristas = await listMotoristas();
        return NextResponse.json(motoristas, { status: 200 });
    } catch (error) {
        const message =
            error instanceof Error ? error.message : "Falha desconhecida no servidor.";

        return NextResponse.json(
            { message: "Erro ao carregar lista de motoristas.", details: message },
            { status: 500 },
        );
    }
}
