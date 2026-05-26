export interface Veiculo {
    id_veiculo: number;
    placa: string;
    modelo: string;
    km_atual: number;
}

export interface Motorista {
    id_motorista: number;
    nome: string;
    cpf: string;
    cnh_categoria: string;
}

export interface RelatorioViagem {
    id_motorista: number;
    nome_motorista: string;
    id_veiculo: number;
    placa: string;
    modelo: string;
    id_rota: number;
    destino: string;
    distancia_km: number;
}
