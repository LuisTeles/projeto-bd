package DAO;

import Models.DOCUMENTO;
import Models.MOTORISTA;
import Models.ROTA;
import DTOs.VeiculoDocumentoDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentoDAO extends ConnectionDAO{
    public boolean DocumentoInsert(DOCUMENTO documento)
    {
        connectToDB();
        String SQL = "INSERT INTO Documento (id_doc, num_licenca, vencimento, idVeiculo) VALUES (?, ?, ?, ?)";
        try{
            pst = connection.prepareStatement(SQL);
            pst.setInt(1, documento.getIdDoc());
            pst.setString(2, documento.getNumLicenca());
            pst.setString(3, documento.getVencimento());
            pst.setInt(4, documento.getIdVeiculo());
            pst.execute();
            return true;

        }catch (SQLException e) {
            System.out.println("Erro ao inserir documento: " + e.getMessage());
            return false;

        } finally {
            try{
                if (pst != null) pst.close();
                if (connection != null) connection.close();

            }catch (SQLException e)
            {
                System.out.println("Erro ao fechar recurso: "+ e.getMessage());
            }

        }

    }

    public boolean DocumentoUpdate(DOCUMENTO documento)
    {
        connectToDB();
        String SQL = "UPDATE Documento SET num_licenca = ?, vencimento = ?, idVeiculo = ? WHERE id_doc = ?";
        try{
            pst = connection.prepareStatement(SQL);
            pst.setString(1, documento.getNumLicenca());
            pst.setString(2, documento.getVencimento());
            pst.setInt(3, documento.getIdVeiculo());
            pst.setInt(3, documento.getIdDoc());
            pst.execute();
            return true;

        }catch (SQLException e) {
            System.out.println("Erro ao atualizar documento: " + e.getMessage());
            return false;

        } finally {
            try{
                if (pst != null) pst.close();
                if (connection != null) connection.close();

            }catch (SQLException e)
            {
                System.out.println("Erro ao fechar recurso: "+ e.getMessage());
            }

        }

    }

    public boolean DocumentoDelete(int id)
    {
        connectToDB();
        String SQL = "DELETE FROM Documento WHERE id_doc = ?";

        try{
            pst = connection.prepareStatement(SQL);
            pst.setInt(1, id);
            pst.execute();
            return true;

        }catch (SQLException e) {
            System.out.println("Erro ao deletar documento: " + e.getMessage());
            return false;

        } finally {
            try{
                if (pst != null) pst.close();
                if (connection != null) connection.close();

            }catch (SQLException e)
            {
                System.out.println("Erro ao fechar recurso: "+ e.getMessage());
            }

        }
    }

    public List<VeiculoDocumentoDTO> SelectDocumentacaoVencimento(String vencimento)
    {
        List<VeiculoDocumentoDTO> listaDocumentos = new ArrayList<>();

        connectToDB();
        String SQL = "SELECT veiculo.placa, Veiculo.modelo, doc.num_licenca from documento as doc " +
                "join veiculo on doc.idVeiculo = veiculo.id_veiculo where vencimento > ?";

        try{
            pst = connection.prepareStatement(SQL);
            pst.setString(1, vencimento);
            rs = pst.executeQuery();

            while(rs.next())
            {
                String placa = rs.getString("placa");
                String modelo = rs.getString("modelo");
                String numLicenca = rs.getString("num_licenca");

                VeiculoDocumentoDTO dto = new VeiculoDocumentoDTO(placa, modelo, numLicenca);
                listaDocumentos.add(dto);
            }

        }catch (SQLException e) {
            System.out.println("Erro ao selecionar documentos: " + e.getMessage());

        } finally {
            try{
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (connection != null) connection.close();

            }catch (SQLException e)
            {
                System.out.println("Erro ao fechar recurso: "+ e.getMessage());
            }
        }

        return listaDocumentos;
    }

}
