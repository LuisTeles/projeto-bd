package Models;

public class ROTA {
    private int idRota;
    private String destino;
    private Double distanciaKm;

    public int getIdRota() {
        return idRota;
    }

    public String getDestino() {
        return destino;
    }

    public Double getDistanciaKm() {
        return distanciaKm;
    }

    public ROTA(int idRota, String destino, Double distanciaKm) {
        this.idRota = idRota;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
    }
}
