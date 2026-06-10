package DTOs;

public class RelatorioViagemDTO {
    private int idMotorista;
    private String Nome;
    private int IdVeiculo;
    private String Placa;
    private String modelo;
    private int idRota;
    private String destino;
    private double distancia;


    public int getIdMotorista() {
        return idMotorista;
    }

    public String getNome() {
        return Nome;
    }

    public int getIdVeiculo() {
        return IdVeiculo;
    }

    public String getPlaca() {
        return Placa;
    }

    public String getModelo() {
        return modelo;
    }

    public int getIdRota() {
        return idRota;
    }

    public String getDestino() {
        return destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public RelatorioViagemDTO(int idMotorista, String nome, int idVeiculo, String placa, String modelo, int idRota, String destino, double distancia) {
        this.idMotorista = idMotorista;
        Nome = nome;
        IdVeiculo = idVeiculo;
        Placa = placa;
        this.modelo = modelo;
        this.idRota = idRota;
        this.destino = destino;
        this.distancia = distancia;
    }
}
