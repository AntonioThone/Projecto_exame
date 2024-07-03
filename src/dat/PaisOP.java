package dat;
import db.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisOP {
    private static Connection connection;
    public PaisOP(Conn conn) {
        try {
            connection = conn.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirPais(Pais pais) {
        String query = "INSERT INTO Pais (nome) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pais.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pais> listarPaises() {
        List<Pais> paises = new ArrayList<>();
        String query = "SELECT * FROM Pais";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pais pais = new Pais();
                pais.setId(rs.getInt("id"));
                pais.setNome(rs.getString("nome"));
                paises.add(pais);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paises;
    }

    public void atualizarPais(Pais pais) {
        String query = "UPDATE Pais SET nome = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pais.getNome());
            stmt.setInt(2, pais.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pais buscarCidadePorId(int pais_id) {
        Pais pais = null;
        String query = "SELECT * FROM Pais WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, pais_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pais = new Pais();
                pais.setId(rs.getInt("id"));
                pais.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pais;
    }

    public Pais buscarCidadePorNome(String nome) {
        Pais pais = null;
        String query = "SELECT * FROM Pais WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pais = new Pais();
                pais.setId(rs.getInt("id"));
                pais.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pais;
    }
    public void deletarPais(int id) {
        String query = "DELETE FROM Pais WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
