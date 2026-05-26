import { Prisma } from "@prisma/client";

import { DATABASE_CONSTANTS } from "@/constants/db";
import { prisma } from "@/lib/prisma";
import type { Motorista, RelatorioViagem, Veiculo } from "@/types/fleet";

export async function listVeiculos(): Promise<Veiculo[]> {
    return prisma.veiculo.findMany({
        orderBy: {
            id_veiculo: "asc",
        },
    });
}

export async function listMotoristas(): Promise<Motorista[]> {
    return prisma.motorista.findMany({
        orderBy: {
            id_motorista: "asc",
        },
    });
}

export async function listRelatorioViagens(): Promise<RelatorioViagem[]> {
    const sql = `
    SELECT
      id_motorista,
      nome_motorista,
      id_veiculo,
      placa,
      modelo,
      id_rota,
      destino,
      distancia_km
    FROM ${DATABASE_CONSTANTS.VIEW_TRIP_REPORT}
    ORDER BY id_motorista ${DATABASE_CONSTANTS.DEFAULT_ORDER}, id_veiculo ${DATABASE_CONSTANTS.DEFAULT_ORDER}, id_rota ${DATABASE_CONSTANTS.DEFAULT_ORDER}
  `;

    return prisma.$queryRawUnsafe<RelatorioViagem[]>(sql);
}

export async function registrarManutencao(
    veiculoId: number,
    descricao: string,
    valorTotal: number,
): Promise<void> {
    await prisma.$executeRaw`
    CALL sp_registrar_manutencao(${veiculoId}, ${descricao}, ${new Prisma.Decimal(valorTotal)})
  `;
}
