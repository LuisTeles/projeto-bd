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


    // MOTORISTAS
    public void InserirMotorista(String cpf, String nome, String cnh) {
        MOTORISTA m = new MOTORISTA(nome, cpf, cnh);
        if(motoristaDAO.MotoristaInsert(m)) System.out.println("✓ Motorista inserido com sucesso!");
        else System.out.println("✗ Erro ao inserir motorista!");
    }

    public void AtualizarMotorista(String cpf, String nome, String cnh) {
        MOTORISTA m = new MOTORISTA(nome, cpf, cnh);
        if(motoristaDAO.MotoristaUpdate(m)) System.out.println("✓ Motorista atualizado com sucesso!");
        else System.out.println("✗ Erro ao atualizar motorista!");
    }

    public void DeletarMotorista(String cpf) {
        MOTORISTA m = new MOTORISTA("", cpf, "");
        if(motoristaDAO.MotoristaDelete(m)) System.out.println("✓ Motorista deletado com sucesso!");
        else System.out.println("✗ Erro ao deletar motorista!");
    }

    public static void PesquisaMotoristaNome(String Nome) {
        List<MOTORISTA> motoristas = motoristaDAO.SelectMotoristaNome(Nome);
        if(motoristas.isEmpty()) {
            System.out.println("Nenhum motorista encontrado.");
            return;
        }
        for(MOTORISTA motorista: motoristas) {
            System.out.println("CPF: " + motorista.getCPF() + " | Nome: "+ motorista.getNome() + " | CNH: " + motorista.getCNHCategoria());
        }
    }


    //VEÍCULOS
    public void InserirVeiculo(String placa, String modelo, double km) {
        VEICULO v = new VEICULO(placa, modelo, km);
        if(veiculoDAO.VeiculoInsert(v)) System.out.println("✓ Veiculo inserido com sucesso!");
        else System.out.println("✗ Erro ao inserir veiculo!");
    }

    public void AtualizarVeiculo(String placa, String modelo, double km) {
        VEICULO v = new VEICULO(placa, modelo, km);
        if(veiculoDAO.VeiculoUpdate(v)) System.out.println("✓ Veiculo atualizado com sucesso!");
        else System.out.println("✗ Erro ao atualizar veiculo!");
    }

    public void DeletarVeiculo(String placa) {
        VEICULO v = new VEICULO(placa, "", 0);
        if(veiculoDAO.VeiculoDelete(v)) System.out.println("✓ Veiculo deletado com sucesso!");
        else System.out.println("✗ Erro ao deletar veiculo!");
    }

    public static void PesquisaVeiculoKM() {
        List<VEICULO> veiculos = veiculoDAO.SelectVeiculoKm();
        if(veiculos.isEmpty()) {
            System.out.println("Nenhum veiculo encontrado.");
            return;
        }
        for (VEICULO veiculo : veiculos) {
            System.out.println("Placa: " + veiculo.getPlaca() + " | Modelo: " + veiculo.getModelo() + " | KM: " + veiculo.getKm_atual());
        }
    }


    //ROTAS
    public void InserirRota(String destino, double distancia) {
        ROTA r = new ROTA(0, destino, distancia); // ID será gerado pelo banco
        if(rotaDAO.rotaInsert(r)) System.out.println("✓ Rota inserida com sucesso!");
        else System.out.println("✗ Erro ao inserir rota!");
    }

    public void AtualizarRota(int idRota, String destino, double distancia) {
        ROTA r = new ROTA(idRota, destino, distancia);
        if(rotaDAO.rotaUpdate(r)) System.out.println("✓ Rota atualizada com sucesso!");
        else System.out.println("✗ Erro ao atualizar rota!");
    }

    public void DeletarRota(int idRota) {
        ROTA r = new ROTA(idRota, "", 0.0);
        if(rotaDAO.rotaDelete(r)) System.out.println("✓ Rota deletada com sucesso!");
        else System.out.println("✗ Erro ao deletar rota!");
    }

    //Pesquisas nas tabelas
    public static void PesquisaRotaDestino(String destino) {
        List<ROTA> rotas = rotaDAO.SelectRotaDestino(destino);
        if(rotas.isEmpty()) {
            System.out.println("Nenhuma rota encontrada.");
            return;
        }
        for(ROTA rota: rotas) {
            System.out.println("ID: " + rota.getIdRota() + " | Destino: " + rota.getDestino() + " | Distância: " + rota.getDistanciaKm() + " km");
        }
    }


    //DOCUMENTOS
    public void InserirDocumento(String numLicenca, String vencimento, int idVeiculo) {
        DOCUMENTO d = new DOCUMENTO(0, numLicenca, vencimento, idVeiculo); // ID será gerado pelo banco
        if(docDAO.DocumentoInsert(d)) System.out.println("✓ Documento inserido com sucesso!");
        else System.out.println("✗ Erro ao inserir documento!");
    }

    public void AtualizarDocumento(int idDoc, String numLicenca, String vencimento, int idVeiculo) {
        DOCUMENTO d = new DOCUMENTO(idDoc, numLicenca, vencimento, idVeiculo);
        if(docDAO.DocumentoUpdate(d)) System.out.println("✓ Documento atualizado com sucesso!");
        else System.out.println("✗ Erro ao atualizar documento!");
    }

    public void DeletarDocumento(int idDoc) {
        if(docDAO.DocumentoDelete(idDoc)) System.out.println("✓ Documento deletado com sucesso!");
        else System.out.println("✗ Erro ao deletar documento!");
    }

    //Ele pega os valores que ainda não venceram na data entregue
    public void PesquisaDocVencimento(String Vencimento) {
        List<VeiculoDocumentoDTO> documentos = docDAO.SelectDocumentacaoVencimento(Vencimento);
        if(documentos.isEmpty()) {
            System.out.println("Nenhum documento encontrado.");
            return;
        }
        for (VeiculoDocumentoDTO doc : documentos) {
            System.out.println("Placa: " + doc.getPlaca() + " | Modelo: " + doc.getModelo() + " | Licença: " + doc.getNumLicenca());
        }
    }

    //MANUTENÇÕES
    public void InserirManutencao(String descricao, double valorTotal, int idVeiculo) {
        MANUTENCAO m = new MANUTENCAO(0, descricao, valorTotal, idVeiculo); // ID será gerado pelo banco
        if(manuDAO.manutencaoInsert(m)) System.out.println("✓ Manutenção inserida com sucesso!");
        else System.out.println("✗ Erro ao inserir manutenção!");
    }

    public void AtualizarManutencao(int idManutencao, String descricao, double valorTotal, int idVeiculo) {
        MANUTENCAO m = new MANUTENCAO(idManutencao, descricao, valorTotal, idVeiculo);
        if(manuDAO.manutencaoUpdate(m)) System.out.println("✓ Manutenção atualizada com sucesso!");
        else System.out.println("✗ Erro ao atualizar manutenção!");
    }

    public void DeletarManutencao(int idManutencao) {
        MANUTENCAO m = new MANUTENCAO(idManutencao, "", 0, 0);
        if(manuDAO.manutencaoDelete(m)) System.out.println("✓ Manutenção deletada com sucesso!");
        else System.out.println("✗ Erro ao deletar manutenção!");
    }

    public void PesquisaManuPreco(double valor) {
        List<VeiculoManutencaoDTO> manutencoes = manuDAO.SelectManutencaoValor(valor);
        if(manutencoes.isEmpty()) {
            System.out.println("Nenhuma manutenção encontrada.");
            return;
        }
        for (VeiculoManutencaoDTO manu : manutencoes) {
            System.out.println("Placa: " + manu.getPlaca() + " | Modelo: " + manu.getModelo() + " | Descrição: " + manu.getDescricao());
        }
    }


    //VIAGENS
    public void InserirViagem(int idMotorista, int idVeiculo, int idRota) {
        VIAGEM v = new VIAGEM(idMotorista, idVeiculo, idRota);
        if(viagemDAO.ViagemInsert(v)) System.out.println("✓ Viagem inserida com sucesso!");
        else System.out.println("✗ Erro ao inserir viagem!");
    }

    public void AtualizarViagem(int idMotorista, int idVeiculo, int idRota) {
        VIAGEM v = new VIAGEM(idMotorista, idVeiculo, idRota);
        if(viagemDAO.ViagemUpdateRota(v)) System.out.println("✓ Viagem atualizada com sucesso!");
        else System.out.println("✗ Erro ao atualizar viagem!");
    }

    public void DeletarViagem(int idRota, int idVeiculo) {
        VIAGEM v = new VIAGEM(0, idVeiculo, idRota);
        if(viagemDAO.ViagemDeleteRotaVeiculo(v)) System.out.println("✓ Viagem deletada com sucesso!");
        else System.out.println("✗ Erro ao deletar viagem!");
    }

    public void RelatorioViagem() {
        List<RelatorioViagemDTO> relatorios = viagemDAO.RelatorioViagem();
        if(relatorios.isEmpty()) {
            System.out.println("Nenhuma viagem encontrada.");
            return;
        }
        System.out.println("\n========== RELATORIO DE VIAGENS ==========");
        for (RelatorioViagemDTO relatorio : relatorios) {
            System.out.println("Motorista: " + relatorio.getNome() + " | Veiculo: " + relatorio.getPlaca() +
                    " (" + relatorio.getModelo() + ") | Destino: " + relatorio.getDestino() +
                    " (" + relatorio.getDistancia() + " km)");
        }
        System.out.println("=========================================\n");
    }
}