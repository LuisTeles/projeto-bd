package Models;

public class MANUTENCAO {
    private int idManutencao;
    private String descricao;
    private double valorTotal;
    private int idVeiculo;

    public int getIdManutencao() {
        return idManutencao;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public MANUTENCAO(int idManutencao, String descricao, double valorTotal, int idVeiculo) {
        this.idManutencao = idManutencao;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.idVeiculo = idVeiculo;
    }
}
