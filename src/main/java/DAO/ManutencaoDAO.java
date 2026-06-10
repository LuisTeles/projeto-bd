package DAO;

import DTOs.VeiculoManutencaoDTO;
import Models.MANUTENCAO;
import Models.MOTORISTA;
import Models.ROTA;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManutencaoDAO extends ConnectionDAO{
    public boolean manutencaoInsert(MANUTENCAO manutencao) {
        connectToDB();
        String SQL = "INSERT INTO manutencao (id_manutencao, descricao, valor_total, idVeiculo) VALUES (?, ?, ?, ?)";
        try {
            pst = connection.prepareStatement(SQL);
            pst.setInt(1, manutencao.getIdManutencao());
            pst.setString(2, manutencao.getDescricao());
            pst.setDouble(3, manutencao.getValorTotal());
            pst.setInt(4, manutencao.getIdVeiculo());
            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir Manutenção: " + e.getMessage());
            return false;

        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();

            } catch (SQLException e) {
                System.out.println("Erro ao fechar recurso: " + e.getMessage());
            }

        }

    }

    public boolean manutencaoUpdate(MANUTENCAO manutencao) {
        connectToDB();
        String SQL = "UPDATE manutencao SET descricao = ?, valor_total = ? , id_veiculo = ? WHERE id_manutencao = ?";
        try {
            pst = connection.prepareStatement(SQL);
            pst.setString(1, manutencao.getDescricao());
            pst.setDouble(2, manutencao.getValorTotal());
            pst.setInt(3, manutencao.getIdVeiculo());
            pst.setInt(4, manutencao.getIdManutencao());
            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar manutenção: " + e.getMessage());
            return false;

        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();

            } catch (SQLException e) {
                System.out.println("Erro ao fechar recurso: " + e.getMessage());
            }

        }

    }

    public boolean manutencaoDelete(MANUTENCAO manutencao) {
        connectToDB();
        String SQL = "DELETE FROM manutencao WHERE id_manutencao = ?";

        try {
            pst = connection.prepareStatement(SQL);
            pst.setInt(1, manutencao.getIdManutencao());
            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar manutenção: " + e.getMessage());
            return false;

        } finally {
            try {
                if (pst != null) pst.close();
                if (connection != null) connection.close();

            } catch (SQLException e) {
                System.out.println("Erro ao fechar recurso: " + e.getMessage());
            }

        }
    }

    public List<VeiculoManutencaoDTO> SelectManutencaoValor(double valor)
    {
        List<VeiculoManutencaoDTO> selects = new ArrayList<>();
        connectToDB();
        String SQL = "SELECT v.placa, v.modelo, m.id_manutencao, m.descricao FROM manutencao as m " +
                "join veiculo v on m.idVeiculo = v.id_veiculo where valor_total >= ?";


        try{
            pst = connection.prepareStatement(SQL);
            pst.setDouble(1,valor );
            rs = pst.executeQuery();
            while(rs.next())
            {
                VeiculoManutencaoDTO select = new VeiculoManutencaoDTO(
                        rs.getString("id_manutencao"),
                        rs.getString("descricao"),
                        rs.getString("placa"),
                        rs.getString("modelo")
                );
                selects.add(select);
            }

        }catch (SQLException e) {
            System.out.println("Erro ao selecionar manutençao: " + e.getMessage());

        } finally {
            try{
                if (pst != null) pst.close();
                if (connection != null) connection.close();

            }catch (SQLException e)
            {
                System.out.println("Erro ao fechar recurso: "+ e.getMessage());
            }

        }
        return selects;
    }
}
