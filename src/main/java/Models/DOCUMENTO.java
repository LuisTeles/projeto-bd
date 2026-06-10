package Models;

import java.sql.Date;

public class DOCUMENTO {
    private int idDoc;
    private String numLicenca;
    private String vencimento;
    private int idVeiculo;

    public int getIdDoc() {
        return idDoc;
    }

    public String getNumLicenca() {
        return numLicenca;
    }

    public String getVencimento() {
        return vencimento;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public DOCUMENTO(int idDoc, String numLicenca, String vencimento, int idVeiculo) {
        this.idDoc = idDoc;
        this.numLicenca = numLicenca;
        this.vencimento = vencimento;
        this.idVeiculo = idVeiculo;
    }
}
