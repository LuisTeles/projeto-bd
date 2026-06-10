package DAO;
import Models.VEICULO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO extends ConnectionDAO{

    public boolean VeiculoInsert(VEICULO veiculo)
    {
        connectToDB();
        String SQL = "INSERT INTO Veiculo (placa, modelo, km_atual) VALUES (?, ?, ?)";
        try{
           pst = connection.prepareStatement(SQL);
           pst.setString(1, veiculo.getPlaca());
           pst.setString(2, veiculo.getModelo());
           pst.setDouble(3, veiculo.getKm_atual());
           pst.execute();
           return true;

        }catch (SQLException e) {
            System.out.println("Erro ao inserir veiculo: " + e.getMessage());
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

    public boolean VeiculoUpdate(VEICULO veiculo)
    {
        connectToDB();
        String SQL = "UPDATE Veiculo SET modelo = ?, km_atual = ? WHERE placa = ?";
        try{
            pst = connection.prepareStatement(SQL);
            pst.setString(1, veiculo.getPlaca());
            pst.setString(2, veiculo.getModelo());
            pst.setDouble(3, veiculo.getKm_atual());
            pst.execute();
            return true;

        }catch (SQLException e) {
            System.out.println("Erro ao atualizar veiculo: " + e.getMessage());
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

    public boolean VeiculoDelete(VEICULO veiculo)
    {
        connectToDB();
        String SQL = "DELETE FROM Veiculo WHERE placa = ?";

        try{
            pst = connection.prepareStatement(SQL);
            pst.setString(1, veiculo.getPlaca());
            pst.execute();
            return true;

        }catch (SQLException e) {
            System.out.println("Erro ao deletar veiculo: " + e.getMessage());
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

    public List<VEICULO> SelectVeiculoKm()
    {
        List<VEICULO> veiculos = new ArrayList<>();
        connectToDB();
        String SQL = "SELECT * FROM Veiculo order by km_atual desc";

        try{
            st = connection.createStatement();
            rs = st.executeQuery(SQL);
            while(rs.next())
            {
                VEICULO veiculo = new VEICULO(
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getDouble("km_atual")
                );
                veiculos.add(veiculo);
            }

        }catch (SQLException e) {
            System.out.println("Erro ao selecionar veiculos: " + e.getMessage());

        } finally {
            try{
                if (pst != null) pst.close();
                if (connection != null) connection.close();

            }catch (SQLException e)
            {
                System.out.println("Erro ao fechar recurso: "+ e.getMessage());
            }

        }
        return veiculos;
    }




}
