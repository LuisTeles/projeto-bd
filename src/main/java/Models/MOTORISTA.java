package Models;

public class MOTORISTA {
    private String nome;
    private String CPF;
    private String CNHCategoria;

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return CPF;
    }

    public String getCNHCategoria() {
        return CNHCategoria;
    }

    public MOTORISTA(String nome, String CPF, String CNHCategoria) {
        this.nome = nome;
        this.CPF = CPF;
        this.CNHCategoria = CNHCategoria;
    }
}
