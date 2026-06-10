package DAO;
import Models.ROTA;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RotaDAO extends ConnectionDAO {

    public boolean rotaInsert(ROTA rota) {
        connectToDB();
        String SQL = "INSERT INTO rota (id_rota, destino, distancia_km) VALUES (?, ?, ?)";
        try {
            pst = connection.prepareStatement(SQL);
            pst.setInt(1, rota.getIdRota());
            pst.setString(2, rota.getDestino());
            pst.setDouble(3, rota.getDistanciaKm());
            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao inserir Rota: " + e.getMessage());
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

    public boolean rotaUpdate(ROTA rota) {
        connectToDB();
        String SQL = "UPDATE rota SET destino = ?, distancia_km = ? WHERE id_rota = ?";
        try {
            pst = connection.prepareStatement(SQL);
            pst.setString(1, rota.getDestino());
            pst.setDouble(2, rota.getDistanciaKm());
            pst.setInt(3, rota.getIdRota());
            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar rota: " + e.getMessage());
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

    public boolean rotaDelete(ROTA rota) {
        connectToDB();
        String SQL = "DELETE FROM rota WHERE id_rota = ?";

        try {
            pst = connection.prepareStatement(SQL);
            pst.setInt(1, rota.getIdRota());
            pst.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar rota: " + e.getMessage());
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

    public List<ROTA> SelectRotaDestino(String Nome)
    {
        List<ROTA> rotas = new ArrayList<>();
        connectToDB();
        String SQL = "SELECT * FROM rota where destino like(?) order by destino asc";

        try{
            pst = connection.prepareStatement(SQL);
            pst.setString(1,"%" + Nome + "%");
            rs = pst.executeQuery();
            while(rs.next())
            {
                ROTA rota = new ROTA(
                        rs.getInt("id_rota"),
                        rs.getString("destino"),
                        rs.getDouble("distancia_km")
                );
                rotas.add(rota);
            }

        }catch (SQLException e) {
            System.out.println("Erro ao selecionar rota: " + e.getMessage());

        } finally {
            try{
                if (pst != null) pst.close();
                if (connection != null) connection.close();

            }catch (SQLException e)
            {
                System.out.println("Erro ao fechar recurso: "+ e.getMessage());
            }

        }
        return rotas;
    }

}
