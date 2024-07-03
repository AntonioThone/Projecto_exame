package dat;
import db.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupoOP {
    private Connection connection;

    public GrupoOP(Conn conn) {
        try {
            this.connection = conn.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirGrupo(Grupo grupo) {
        String query = "INSERT INTO Grupo (nome) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, grupo.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Grupo> listarGrupos() {
        List<Grupo> grupos = new ArrayList<>();
        String query = "SELECT * FROM Grupo";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setId(rs.getInt("id"));
                grupo.setNome(rs.getString("nome"));
                grupos.add(grupo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupos;
    }

    public void atualizarGrupo(Grupo grupo) {
        String query = "UPDATE Grupo SET nome = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, grupo.getNome());
            stmt.setInt(2, grupo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Grupo buscarGrupoPorId(int grupo_id) {
        Grupo grupo = null;
        String query = "SELECT * FROM Grupo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, grupo_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                grupo = new Grupo();
                grupo.setId(rs.getInt("id"));
                grupo.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupo;
    }

    public Grupo buscarGrupoPorNome(String nome) {
        Grupo grupo = null;
        String query = "SELECT * FROM Grupo WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                grupo = new Grupo();
                grupo.setId(rs.getInt("id"));
                grupo.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupo;
    }

    // Método para contar o número de grupos na tabela Grupo
    public int contarNumeroGrupos() {
        int numeroGrupos = 0;
        String query = "SELECT COUNT(*) AS total FROM Grupo";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                numeroGrupos = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numeroGrupos;
    }

    public void deletarGrupo(int id) {
        String query = "DELETE FROM Grupo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
