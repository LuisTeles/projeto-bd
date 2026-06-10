package DTOs;

public class VeiculoManutencaoDTO {
    private String idManutencao;
    private String descricao;
    private String placa;
    private String Modelo;

    public String getIdManutencao() {
        return idManutencao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return Modelo;
    }

    public VeiculoManutencaoDTO(String idManutencao, String descricao, String placa, String modelo) {
        this.idManutencao = idManutencao;
        this.descricao = descricao;
        this.placa = placa;
        Modelo = modelo;
    }
}
