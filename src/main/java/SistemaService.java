import DAO.*;
import Models.*;
import DTOs.*;

import java.util.ArrayList;
import java.util.List;

public class SistemaService {
    static VeiculoDAO veiculoDAO = new VeiculoDAO();
    static MotoristaDAO motoristaDAO = new MotoristaDAO();
    static RotaDAO rotaDAO = new RotaDAO();
    static DocumentoDAO docDAO = new DocumentoDAO();
    static ManutencaoDAO manuDAO = new ManutencaoDAO();
    static ViagemDAO viagemDAO = new ViagemDAO();

    //Populando o banco
    public static void PopulandoVeiculos(){
        List<VEICULO> veiculos = new ArrayList<>();
        veiculos.add(new VEICULO("ABC1D23", "Mercedes Atego 1719", 120500.0));
        veiculos.add(new VEICULO("EFG4H56", "Volkswagen Delivery 11.180", 89340.5));
        veiculos.add(new VEICULO("IJK7L89", "Iveco Daily 35S14", 64210.2));
        veiculos.add(new VEICULO("MNO1P23", "Scania P 310", 210900.8));
        veiculos.add(new VEICULO("QRS4T56", "Volvo VM 270", 154770.4));

        for (VEICULO veiculo: veiculos)
        {
            veiculoDAO.VeiculoInsert(veiculo);
        }
    }

    public static void PopulandoMotoristas(){
        List<MOTORISTA> motoristas = new ArrayList<>();
        motoristas.add(new MOTORISTA("Carlos Eduardo Silva", "12345678901", "E"));
        motoristas.add(new MOTORISTA("Fernanda Araujo Lima", "23456789012", "D"));
        motoristas.add(new MOTORISTA("Joao Pedro Martins", "34567890123", "E"));
        motoristas.add(new MOTORISTA("Mariana Costa Souza", "45678901234", "D"));
        motoristas.add(new MOTORISTA("Ricardo Alves Neto", "56789012345", "E"));

        for (MOTORISTA motorista: motoristas)
        {
            motoristaDAO.MotoristaInsert(motorista);
        }
    }

    public static void PopulandoRotas(){
        List<ROTA> rotas = new ArrayList<>();
        rotas.add(new ROTA(1, "Campinas", 98.5));
        rotas.add(new ROTA(2, "Ribeirao Preto", 312.7));
        rotas.add(new ROTA(3, "Santos", 83.4));
        rotas.add(new ROTA(4, "Sao Jose dos Campos", 108.9));
        rotas.add(new ROTA(5, "Sorocaba", 101.2));

        for (ROTA rota: rotas)
        {
            rotaDAO.rotaInsert(rota);
        }
    }

    public static void PopulandoDoc(){
        List<DOCUMENTO> docs = new ArrayList<>();
        docs.add(new DOCUMENTO(1, "LIC-ATEGO-2026", "2026-12-15", 1));
        docs.add(new DOCUMENTO(2, "LIC-DELIV-2026", "2026-10-10", 2));
        docs.add(new DOCUMENTO(3, "LIC-IVECO-2027", "2027-01-20", 3));
        docs.add(new DOCUMENTO(4, "LIC-SCANI-2026", "2026-09-01", 4));
        docs.add(new DOCUMENTO(5, "LIC-VOLVO-2027", "2027-03-30", 5));

        for (DOCUMENTO doc: docs)
        {
            docDAO.DocumentoInsert(doc);
        }
    }

    public static void PopulandoManutencao()
    {
        List<MANUTENCAO> manutencoes = new ArrayList<>();
        manutencoes.add(new MANUTENCAO(1, "Troca de oleo e filtros", 850.00, 1));
        manutencoes.add(new MANUTENCAO(2, "Alinhamento e balanceamento", 620.00, 2));
        manutencoes.add(new MANUTENCAO(3, "Revisao do sistema de freios", 1340.50, 3));
        manutencoes.add(new MANUTENCAO(4, "Substituicao de pneus traseiros", 4280.90, 4));
        manutencoes.add(new MANUTENCAO(5, "Reparo no sistema eletrico", 990.75, 5));

        for(MANUTENCAO manu : manutencoes)
        {
            manuDAO.manutencaoInsert(manu);
        }
    }

    public static void PopulandoViagem()
    {
        List<VIAGEM> viagens = new ArrayList<>();
        viagens.add(new VIAGEM(1, 1, 1));
        viagens.add(new VIAGEM(2, 2, 2));
        viagens.add(new VIAGEM(3, 3, 3));
        viagens.add(new VIAGEM(4, 4, 4));
        viagens.add(new VIAGEM(5, 5, 5));

        for(VIAGEM viagem : viagens)
        {
            viagemDAO.ViagemInsert(viagem);
        }
    }



    //Pesquisas nas tabelas

    public static void PesquisaRotaDestino(String destino)
    {
        List<ROTA> rotas;
        rotas = rotaDAO.SelectRotaDestino(destino);

        for(ROTA rota: rotas)
        {
            System.out.println("Id da Rota: " + rota.getIdRota() + " - Destino: "+rota.getDestino() + " - Distancia: "+rota.getDistanciaKm());
        }
    }

    public static void PesquisaMotoristaNome(String Nome)
    {
        List<MOTORISTA> motoristas;
        motoristas = motoristaDAO.SelectMotoristaNome(Nome);

        for(MOTORISTA motorista: motoristas)
        {
            System.out.println("CPF: " + motorista.getCPF() + " - Nome: "+motorista.getNome() + " - CNH Categoria: "+motorista.getCNHCategoria());
        }
    }

    public static void PesquisaVeiculoKM() {
        List<VEICULO> veiculos;
        veiculos = veiculoDAO.SelectVeiculoKm();

        for (VEICULO veiculo : veiculos) {
            System.out.println("Placa: " + veiculo.getPlaca() + " - Modelo: " + veiculo.getModelo() + " - Km atual: " + veiculo.getKm_atual());
        }
    }

    //Ele pega os valores que ainda não venceram na data entregue
    public void PesquisaDocVencimento(String Vencimento) {
        List<VeiculoDocumentoDTO> documentos = docDAO.SelectDocumentacaoVencimento(Vencimento);

        for (VeiculoDocumentoDTO doc : documentos) {
            System.out.println("Placa: " + doc.getPlaca()+" - Modelo: " + doc.getModelo() + " - Nº Licença: " + doc.getNumLicenca());
        }

    }

    //Pega valores maior ou igual
    public void PesquisaManuPreco(double valor) {
        List<VeiculoManutencaoDTO> manutencoes = manuDAO.SelectManutencaoValor(valor);

        for (VeiculoManutencaoDTO manu : manutencoes) {
            System.out.println("Placa: " + manu.getPlaca()+" - Modelo: " + manu.getModelo() + " - Nº manutenção: " + manu.getIdManutencao() + " - Descrição: "+ manu.getDescricao());
        }

    }

    public void RelatorioViagem()
    {
        List<RelatorioViagemDTO> relatorios = viagemDAO.RelatorioViagem();

        for (RelatorioViagemDTO relatorio : relatorios) {
            System.out.println("Id Motorista: " + relatorio.getIdMotorista()+" - Nome: " + relatorio.getNome() +
                    " - Id Veiculo: " + relatorio.getIdVeiculo() + " - Placa: "+ relatorio.getPlaca()
                    + " - Modelo: "+ relatorio.getModelo() + " - Id Rota: "+ relatorio.getIdRota()
                    + " - destino: "+ relatorio.getDestino() + " - distancia: "+ relatorio.getDistancia());
        }
    }


}
