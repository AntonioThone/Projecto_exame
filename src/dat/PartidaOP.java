package dat;
import db.Conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartidaOP {
    private Connection connection;
    private Conn conn;

    public PartidaOP(Conn conn) {
        try {
            this.connection = conn.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void adicionarPartida(Partida partida) throws SQLException {
        String query = "INSERT INTO partida (data, estadio_id, selecao_casa_id, selecao_fora_id, gols_casa, gols_fora) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(partida.getData().getTime()));
            stmt.setInt(2, partida.getEstadio().getId());
            stmt.setInt(3, partida.getSelecaoCasa_id());
            stmt.setInt(4, partida.getSelecaoFora_id());
            stmt.setInt(5, partida.getGolsCasa());
            stmt.setInt(6, partida.getGolsFora());
            stmt.executeUpdate();
        }
    }

    public List<Partida> listarPartidas() throws SQLException {
        List<Partida> partidas = new ArrayList<>();
        String query = "SELECT * FROM partida";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Partida partida = new Partida();
                partida.setId(rs.getInt("id"));
                partida.setData(new Date(rs.getDate("data").getTime()));
                // Carregar o estádio
                Conn db = new Conn();
                EstadioOP estadioOP = new EstadioOP(db);
                Estadio estadio = estadioOP.buscarEstadioPorId(rs.getInt("estadio_id"));
                partida.setEstadio(estadio);
                // Carregar as seleções
                SelecaoOP selecaoOP = new SelecaoOP(db);
                Selecao selecaoCasa = selecaoOP.buscarSelecaoPorId(rs.getInt("selecao_casa_id"));
                Selecao selecaoFora = selecaoOP.buscarSelecaoPorId(rs.getInt("selecao_fora_id"));
                partida.setSelecaoCasa(selecaoCasa);
                partida.setSelecaoFora(selecaoFora);
                partida.setGolsCasa(rs.getInt("gols_casa"));
                partida.setGolsFora(rs.getInt("gols_fora"));
                partidas.add(partida);
            }
        }
        return partidas;
    }

    public Partida obterPartidaPorId(int partidaId) throws SQLException {
        Partida partida = null;
        Conn db = new Conn();
        String query = "SELECT * FROM partida WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, partidaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    partida = new Partida();
                    partida.setId(rs.getInt("id"));
                    partida.setData(new Date(rs.getDate("data").getTime()));
                    EstadioOP estadioOP = new EstadioOP(db);
                    Estadio estadio = estadioOP.buscarEstadioPorId(rs.getInt("estadio_id"));
                    partida.setEstadio(estadio);
                    SelecaoOP selecaoOP = new SelecaoOP(db);
                    Selecao selecaoCasa = selecaoOP.buscarSelecaoPorId(rs.getInt("selecao_casa_id"));
                    Selecao selecaoFora = selecaoOP.buscarSelecaoPorId(rs.getInt("selecao_fora_id"));
                    partida.setSelecaoCasa(selecaoCasa);
                    partida.setSelecaoFora(selecaoFora);
                    partida.setGolsCasa(rs.getInt("gols_casa"));
                    partida.setGolsFora(rs.getInt("gols_fora"));
                }
            }
        }
        return partida;
    }
}
