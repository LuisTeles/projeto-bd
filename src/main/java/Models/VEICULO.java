package Models;

public class VEICULO {
    private String placa;
    private String modelo;
    private double km_atual;



    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public double getKm_atual() {
        return km_atual;
    }

    public VEICULO(String placa, String modelo, double km_atual) {
        this.placa = placa;
        this.modelo = modelo;
        this.km_atual = km_atual;
    }
}
