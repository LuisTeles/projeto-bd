package Models;

public class VIAGEM {
    private int idMotorista;
    private int idVeiculo;
    private int idRota;

    public int getIdMotorista() {
        return idMotorista;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public int getIdRota() {
        return idRota;
    }

    public VIAGEM(int idMotorista, int idVeiculo, int idRota) {
        this.idMotorista = idMotorista;
        this.idVeiculo = idVeiculo;
        this.idRota = idRota;
    }
}
