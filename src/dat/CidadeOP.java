package dat;
import db.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeOP {
    private Connection connection;

    public CidadeOP(Conn conn) {
        try {
            this.connection = conn.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void inserirCidade(Cidade cidade) {
        String query = "INSERT INTO Cidade (nome, pais_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cidade.getNome());
            stmt.setInt(2, cidade.getPaisId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cidade> listarCidades() {
        List<Cidade> cidades = new ArrayList<>();
        String query = "SELECT * FROM Cidade";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(rs.getInt("id"));
                cidade.setNome(rs.getString("nome"));
                cidade.setPaisId(rs.getInt("pais_id"));
                cidades.add(cidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cidades;
    }

    public void atualizarCidade(Cidade cidade) {
        String query = "UPDATE Cidade SET nome = ?, pais_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cidade.getNome());
            stmt.setInt(2, cidade.getPaisId());
            stmt.setInt(3, cidade.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cidade buscarCidadePorId(int cidade_id) {
        Cidade cidade = null;
        String query = "SELECT * FROM Cidade WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cidade_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cidade = new Cidade();
                cidade.setId(rs.getInt("id"));
                cidade.setNome(rs.getString("nome"));
                cidade.setPaisId(rs.getInt("pais_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cidade;
    }

    public Cidade buscarCidadePorNome(String nome) {
        Cidade cidade = null;
        String query = "SELECT * FROM Cidade WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cidade = new Cidade();
                cidade.setId(rs.getInt("id"));
                cidade.setNome(rs.getString("nome"));
                cidade.setPaisId(rs.getInt("pais_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cidade;
    }

    public void deletarCidade(int id) {
        String query = "DELETE FROM Cidade WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
