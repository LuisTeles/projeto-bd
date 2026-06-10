package DAO;
import Models.MOTORISTA;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotoristaDAO extends ConnectionDAO {
    public boolean MotoristaInsert(MOTORISTA motorista)
    {
        connectToDB();
        String SQL = "INSERT INTO Motorista (cpf, nome, cnh_categoria) VALUES (?, ?, ?)";
        try{
            pst = connection.prepareStatement(SQL);
            pst.setString(1, motorista.getCPF());
            pst.setString(2, motorista.getNome());
            pst.setString(3, motorista.getCNHCategoria());
            pst.execute();
            return true;

        }catch (SQLException e) {
            System.out.println("Erro ao inserir motorista: " + e.getMessage());
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

    public boolean MotoristaUpdate(MOTORISTA motorista)
    {
        connectToDB();
        String SQL = "UPDATE Motorista SET nome = ?, cnh_categoria = ? WHERE cpf = ?";
        try{
            pst = connection.prepareStatement(SQL);
            pst.setString(1, motorista.getCPF());
            pst.setString(2, motorista.getNome());
            pst.setString(3, motorista.getCNHCategoria());
            pst.execute();
            return true;

        }catch (SQLException e) {
            System.out.println("Erro ao atualizar motorista: " + e.getMessage());
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

    public boolean MotoristaDelete(MOTORISTA motorista)
    {
        connectToDB();
        String SQL = "DELETE FROM Motorista WHERE cpf = ?";

        try{
            pst = connection.prepareStatement(SQL);
            pst.setString(1, motorista.getCPF());
            pst.execute();
            return true;

        }catch (SQLException e) {
            System.out.println("Erro ao deletar motorista: " + e.getMessage());
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

    public List<MOTORISTA> SelectMotoristaNome(String Nome)
    {
        List<MOTORISTA> motoristas = new ArrayList<>();
        connectToDB();
        String SQL = "SELECT * FROM Motorista where nome like(?) order by nome asc";

        try{
            pst = connection.prepareStatement(SQL);
            pst.setString(1,"%" + Nome + "%");
            rs = pst.executeQuery();
            while(rs.next())
            {
                MOTORISTA motorista = new MOTORISTA(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("cnh_categoria")
                );
                motoristas.add(motorista);
            }

        }catch (SQLException e) {
            System.out.println("Erro ao selecionar motoristas: " + e.getMessage());

        } finally {
            try{
                if (pst != null) pst.close();
                if (connection != null) connection.close();

            }catch (SQLException e)
            {
                System.out.println("Erro ao fechar recurso: "+ e.getMessage());
            }

        }
        return motoristas;
    }

}
