package dat;

import db.Conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelecaoOP {
    private Connection connection;

    public SelecaoOP(Conn conn) {
        try {
            this.connection = conn.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirSelecao(Selecao selecao) {
        String query = "INSERT INTO Selecao (nome, pais_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, selecao.getNome());
            stmt.setInt(2, selecao.getPaisId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Selecao buscarSelecaoPorId(int selecaoId) {
        Selecao selecao = null;
        String query = "SELECT * FROM selecao WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, selecaoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                selecao = new Selecao();
                selecao.setId(rs.getInt("id"));
                selecao.setNome(rs.getString("nome"));
                selecao.setPaisId(rs.getInt("pais_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selecao;
    }
    public Selecao buscarSelecaoPorNome(String nome) {
        Selecao selecao = null;
        String query = "SELECT * FROM Selecao WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                selecao = new Selecao();
                selecao.setId(rs.getInt("id"));
                selecao.setNome(rs.getString("nome"));
                selecao.setPaisId(rs.getInt("pais_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selecao;
    }



    public List<Selecao> listarSelecoes() {
        List<Selecao> selecoes = new ArrayList<>();
        String query = "SELECT * FROM Selecao";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Selecao selecao = new Selecao();
                selecao.setId(rs.getInt("id"));
                selecao.setNome(rs.getString("nome"));
                selecao.setPaisId(rs.getInt("pais_id"));
                selecoes.add(selecao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selecoes;
    }


    public List<Integer> listarSelecoesID() {
        List<Integer> selecoesId = new ArrayList<>();
        String query = "SELECT id FROM Selecao";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Selecao selecao = new Selecao();
                selecao.setId(rs.getInt("id"));
                selecoesId.add(selecao.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selecoesId;
    }
    public void atualizarSelecao(Selecao selecao) {
        String query = "UPDATE Selecao SET nome = ?, pais_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, selecao.getNome());
            stmt.setInt(2, selecao.getPaisId());
            stmt.setInt(3, selecao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletarSelecao(int id) {
        String query = "DELETE FROM Selecao WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

