package DTOs;

public class VeiculoDocumentoDTO {

        private String placa;
        private String modelo;
        private String numLicenca;

        public VeiculoDocumentoDTO(String placa, String modelo, String numLicenca) {
            this.placa = placa;
            this.modelo = modelo;
            this.numLicenca = numLicenca;
        }

        public String getPlaca() { return placa; }
        public void setPlaca(String placa) { this.placa = placa; }

        public String getModelo() { return modelo; }
        public void setModelo(String modelo) { this.modelo = modelo; }

        public String getNumLicenca() { return numLicenca; }
        public void setNumLicenca(String numLicenca) { this.numLicenca = numLicenca; }
}
