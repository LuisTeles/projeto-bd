import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SistemaService SS = new SistemaService();

        // Popular Banco de Dados
        System.out.println("Populando banco de dados.");
        SS.PopulandoVeiculos();
        SS.PopulandoMotoristas();
        SS.PopulandoRotas();
        SS.PopulandoDoc();
        SS.PopulandoManutencao();
        SS.PopulandoViagem();
        System.out.println("Banco de dados populado com sucesso!\n");

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("**** SISTEMA DE FROTA LOGISTICA ****");
            System.out.println(" ");
            System.out.println("--- MOTORISTA ---");
            System.out.println("1  - Inserir Motorista");
            System.out.println("2  - Atualizar Motorista");
            System.out.println("3  - Deletar Motorista");
            System.out.println("4  - Buscar Motorista por Nome (SELECT)");
            System.out.println("--- VEICULO ---");
            System.out.println("5  - Inserir Veiculo");
            System.out.println("6  - Atualizar Veiculo");
            System.out.println("7  - Deletar Veiculo");
            System.out.println("8  - Listar Veiculos por KM (SELECT)");
            System.out.println("--- ROTA ---");
            System.out.println("9  - Inserir Rota");
            System.out.println("10 - Atualizar Rota");
            System.out.println("11 - Deletar Rota");
            System.out.println("12 - Buscar Rota por Destino (SELECT)");
            System.out.println("--- DOCUMENTO ---");
            System.out.println("13 - Inserir Documento");
            System.out.println("14 - Atualizar Documento");
            System.out.println("15 - Deletar Documento");
            System.out.println("16 - Buscar Documentação por Vencimento (JOIN)");
            System.out.println("--- MANUTENCAO ---");
            System.out.println("17 - Inserir Manutenção");
            System.out.println("18 - Atualizar Manutenção");
            System.out.println("19 - Deletar Manutenção");
            System.out.println("20 - Buscar Manutencao por Valor (JOIN)");
            System.out.println("--- VIAGEM ---");
            System.out.println("21 - Inserir Viagem");
            System.out.println("22 - Atualizar Viagem");
            System.out.println("23 - Deletar Viagem");
            System.out.println("24 - Relatorio de Viagens (JOIN triplo)");
            System.out.println("--- SAIR ---");
            System.out.println("0  - Sair");
            System.out.println(" ");
            System.out.print("Escolha uma opcao: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                // MOTORISTA
                case 1:
                    System.out.print("CPF: "); String cpfIns = scanner.nextLine();
                    System.out.print("Nome: "); String nomeIns = scanner.nextLine();
                    System.out.print("Categoria CNH: "); String cnhIns = scanner.nextLine();
                    SS.InserirMotorista(cpfIns, nomeIns, cnhIns);
                    break;
                case 2:
                    System.out.print("CPF do Motorista a atualizar: "); String cpfUpd = scanner.nextLine();
                    System.out.print("Novo Nome: "); String nomeUpd = scanner.nextLine();
                    System.out.print("Nova Categoria CNH: "); String cnhUpd = scanner.nextLine();
                    SS.AtualizarMotorista(cpfUpd, nomeUpd, cnhUpd);
                    break;
                case 3:
                    System.out.print("CPF do Motorista a deletar: "); String cpfDel = scanner.nextLine();
                    SS.DeletarMotorista(cpfDel);
                    break;
                case 4:
                    System.out.print("Digite o nome ou parte dele: "); String nomeBusca = scanner.nextLine();
                    SS.PesquisaMotoristaNome(nomeBusca);
                    break;

                //  VEICULO
                case 5:
                    System.out.print("Placa (ex: ABC1D23): "); String placaIns = scanner.nextLine();
                    System.out.print("Modelo: "); String modIns = scanner.nextLine();
                    System.out.print("KM Atual: "); double kmIns = scanner.nextDouble();
                    scanner.nextLine();
                    SS.InserirVeiculo(placaIns, modIns, kmIns);
                    break;
                case 6:
                    System.out.print("Placa do Veiculo a atualizar: "); String placaUpd = scanner.nextLine();
                    System.out.print("Novo Modelo: "); String modUpd = scanner.nextLine();
                    System.out.print("Novo KM: "); double kmUpd = scanner.nextDouble();
                    scanner.nextLine();
                    SS.AtualizarVeiculo(placaUpd, modUpd, kmUpd);
                    break;
                case 7:
                    System.out.print("Placa do Veiculo a deletar: "); String placaDel = scanner.nextLine();
                    SS.DeletarVeiculo(placaDel);
                    break;
                case 8:
                    System.out.println("Listando veiculos...");
                    SS.PesquisaVeiculoKM();
                    break;

                //  ROTA
                case 9:
                    System.out.print("Destino: "); String destIns = scanner.nextLine();
                    System.out.print("Distância em KM: "); double distIns = scanner.nextDouble();
                    scanner.nextLine();
                    SS.InserirRota(destIns, distIns);
                    break;
                case 10:
                    System.out.print("ID da Rota a atualizar: "); int idRotaUpd = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo Destino: "); String destUpd = scanner.nextLine();
                    System.out.print("Nova Distância: "); double distUpd = scanner.nextDouble();
                    scanner.nextLine();
                    SS.AtualizarRota(idRotaUpd, destUpd, distUpd);
                    break;
                case 11:
                    System.out.print("ID da Rota a deletar: "); int idRotaDel = scanner.nextInt();
                    scanner.nextLine();
                    SS.DeletarRota(idRotaDel);
                    break;
                case 12:
                    System.out.print("Destino a buscar: "); String destBusca = scanner.nextLine();
                    SS.PesquisaRotaDestino(destBusca);
                    break;

                //  DOCUMENTO
                case 13:
                    System.out.print("Número da Licença: "); String numLicIns = scanner.nextLine();
                    System.out.print("Vencimento (YYYY-MM-DD): "); String vencIns = scanner.nextLine();
                    System.out.print("ID do Veiculo: "); int idVeicDocIns = scanner.nextInt();
                    scanner.nextLine();
                    SS.InserirDocumento(numLicIns, vencIns, idVeicDocIns);
                    break;
                case 14:
                    System.out.print("ID do Documento a atualizar: "); int idDocUpd = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo Número de Licença: "); String numLicUpd = scanner.nextLine();
                    System.out.print("Novo Vencimento (YYYY-MM-DD): "); String vencUpd = scanner.nextLine();
                    System.out.print("Novo ID do Veiculo: "); int idVeicDocUpd = scanner.nextInt();
                    scanner.nextLine();
                    SS.AtualizarDocumento(idDocUpd, numLicUpd, vencUpd, idVeicDocUpd);
                    break;
                case 15:
                    System.out.print("ID do Documento a deletar: "); int idDocDel = scanner.nextInt();
                    scanner.nextLine();
                    SS.DeletarDocumento(idDocDel);
                    break;
                case 16:
                    System.out.print("Vencimento (YYYY-MM-DD): "); String vencBusca = scanner.nextLine();
                    SS.PesquisaDocVencimento(vencBusca);
                    break;

                //  MANUTENCAO
                case 17:
                    System.out.print("Descrição: "); String descManuIns = scanner.nextLine();
                    System.out.print("Valor Total: "); double valorManuIns = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("ID do Veiculo: "); int idVeicManuIns = scanner.nextInt();
                    scanner.nextLine();
                    SS.InserirManutencao(descManuIns, valorManuIns, idVeicManuIns);
                    break;
                case 18:
                    System.out.print("ID da Manutenção a atualizar: "); int idManuUpd = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nova Descrição: "); String descManuUpd = scanner.nextLine();
                    System.out.print("Novo Valor Total: "); double valorManuUpd = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Novo ID do Veiculo: "); int idVeicManuUpd = scanner.nextInt();
                    scanner.nextLine();
                    SS.AtualizarManutencao(idManuUpd, descManuUpd, valorManuUpd, idVeicManuUpd);
                    break;
                case 19:
                    System.out.print("ID da Manutenção a deletar: "); int idManuDel = scanner.nextInt();
                    scanner.nextLine();
                    SS.DeletarManutencao(idManuDel);
                    break;
                case 20:
                    System.out.print("Buscar manutencoes acima de qual valor? "); double valorManuBusca = scanner.nextDouble();
                    scanner.nextLine();
                    SS.PesquisaManuPreco(valorManuBusca);
                    break;

                //  VIAGEM
                case 21:
                    System.out.print("ID do Motorista: "); int idMotViaIns = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID do Veiculo: "); int idVeicViaIns = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID da Rota: "); int idRotViaIns = scanner.nextInt();
                    scanner.nextLine();
                    SS.InserirViagem(idMotViaIns, idVeicViaIns, idRotViaIns);
                    break;
                case 22:
                    System.out.print("ID do Motorista a atualizar: "); int idMotViaUpd = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID do Veiculo: "); int idVeicViaUpd = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo ID da Rota: "); int idRotViaUpd = scanner.nextInt();
                    scanner.nextLine();
                    SS.AtualizarViagem(idMotViaUpd, idVeicViaUpd, idRotViaUpd);
                    break;
                case 23:
                    System.out.print("ID da Rota a deletar (viagem): "); int idRotViaDel = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID do Veiculo: "); int idVeicViaDel = scanner.nextInt();
                    scanner.nextLine();
                    SS.DeletarViagem(idRotViaDel, idVeicViaDel);
                    break;
                case 24:
                    System.out.println("Gerando relatorio de viagens...");
                    SS.RelatorioViagem();
                    break;

                case 0:
                    System.out.println("\nEncerrando o sistema...");
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }
        }
        scanner.close();
    }
}