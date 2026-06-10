

public class Main {


    public static void main(String[] args) {
        SistemaService SS = new SistemaService();

        //SS.PopulandoVeiculos();
        //SS.PopulandoMotoristas();
        //SS.PopulandoRotas();
        //SS.PopulandoDoc();
        //SS.PopulandoManutencao();
        //SS.PopulandoViagem();

        //SS.PesquisaRotaDestino("Santos");
        //SS.PesquisaMotoristaNome("Carlos");
        //SS.PesquisaVeiculoKM();
        //SS.PesquisaDocVencimento("2027-1-1");
        SS.PesquisaManuPreco(1000);
        SS.RelatorioViagem();

    }
}