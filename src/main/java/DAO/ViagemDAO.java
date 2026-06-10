package DAO;

import DTOs.RelatorioViagemDTO;
import Models.VEICULO;
import Models.VIAGEM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ViagemDAO extends ConnectionDAO{
    public boolean ViagemInsert(VIAGEM viagem)
    {
        connectToDB();
        String SQL = "INSERT INTO viagem (idMotorista, idVeiculo, idRota) VALUES (?, ?, ?)";
        try{
            pst = connection.prepareStatement(SQL);
            pst.setInt(1, viagem.getIdMotorista());
            pst.setInt(2, viagem.getIdVeiculo());
            pst.setInt(3, viagem.getIdRota());
            pst.execute();
            return true;

        }catch (SQLException e) {
            System.out.println("Erro ao inserir Viagem: " + e.getMessage());
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

    public boolean ViagemUpdateRota(VIAGEM viagem)
    {
        connectToDB();
        String SQL = "UPDATE viagem SET idMotorista = ?, idVeiculo = ?, idRota = ? WHERE idRota = ?";
        try{
            pst = connection.prepareStatement(SQL);
            pst.setInt(1, viagem.getIdMotorista());
            pst.setInt(2, viagem.getIdVeiculo());
            pst.setInt(3, viagem.getIdRota());
            pst.execute();
            return true;

        }catch (SQLException e) {
            System.out.println("Erro ao atualizar viagem: " + e.getMessage());
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

    public boolean ViagemDeleteRotaVeiculo(VIAGEM viagem)
    {
        connectToDB();
        String SQL = "DELETE FROM viagem WHERE idRota = ? AND idVeiculo = ?";

        try{
            pst = connection.prepareStatement(SQL);
            pst.setInt(1, viagem.getIdRota());
            pst.setInt(2, viagem.getIdVeiculo());
            pst.execute();
            return true;

        }catch (SQLException e) {
            System.out.println("Erro ao deletar viagem: " + e.getMessage());
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

    public List<RelatorioViagemDTO> RelatorioViagem()
    {
        List<RelatorioViagemDTO> Relatorios = new ArrayList<>();
        connectToDB();
        String SQL = "SELECT * FROM vw_relatorio_viagens";

        try{
            st = connection.createStatement();
            rs = st.executeQuery(SQL);
            while(rs.next())
            {
                RelatorioViagemDTO relatorio = new RelatorioViagemDTO(
                        rs.getInt("id_motorista"),
                        rs.getString("nome_motorista"),
                        rs.getInt("id_veiculo"),
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getInt("id_rota"),
                        rs.getString("destino"),
                        rs.getDouble("distancia_km")
                );
                Relatorios.add(relatorio);
            }

        }catch (SQLException e) {
            System.out.println("Erro ao selecionar Relatorio de Viagem: " + e.getMessage());

        } finally {
            try{
                if (pst != null) pst.close();
                if (connection != null) connection.close();

            }catch (SQLException e)
            {
                System.out.println("Erro ao fechar recurso: "+ e.getMessage());
            }

        }
        return Relatorios;
    }

}
