package dat;
import db.Conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupoSelecaoOP {
    private Connection connection;

    public GrupoSelecaoOP(Conn conn) {
        try {
            this.connection = conn.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirGrupoSelecao(GrupoSelecao grupoSelecao) {
        String query = "INSERT INTO grupo_selecao (grupo_id, selecao_id, pontos, jogos, vitorias, empates, derrotas, gols_marcados, gols_sofridos) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, grupoSelecao.getGrupoId());
            stmt.setInt(2, grupoSelecao.getSelecaoId());
            stmt.setInt(3, grupoSelecao.getPontos());
            stmt.setInt(4, grupoSelecao.getJogos());
            stmt.setInt(5, grupoSelecao.getVitorias());
            stmt.setInt(6, grupoSelecao.getEmpates());
            stmt.setInt(7, grupoSelecao.getDerrotas());
            stmt.setInt(8, grupoSelecao.getGolsMarcados());
            stmt.setInt(9, grupoSelecao.getGolsSofridos());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GrupoSelecao> obterSelecoesPorGrupo(int grupoId) {
        List<GrupoSelecao> selecoes = new ArrayList<>();
        String query = "SELECT * FROM grupo_selecao WHERE grupo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, grupoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GrupoSelecao grupoSelecao = new GrupoSelecao();
                grupoSelecao.setId(rs.getInt("id"));
                grupoSelecao.setGrupoId(rs.getInt("grupo_id"));
                grupoSelecao.setSelecaoId(rs.getInt("selecao_id"));
                grupoSelecao.setPontos(rs.getInt("pontos"));
                grupoSelecao.setJogos(rs.getInt("jogos"));
                grupoSelecao.setVitorias(rs.getInt("vitorias"));
                grupoSelecao.setEmpates(rs.getInt("empates"));
                grupoSelecao.setDerrotas(rs.getInt("derrotas"));
                grupoSelecao.setGolsMarcados(rs.getInt("gols_marcados"));
                grupoSelecao.setGolsSofridos(rs.getInt("gols_sofridos"));
                selecoes.add(grupoSelecao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selecoes;
    }
    public List<Selecao> obterSelecoesGrupo(int grupoId) throws SQLException {
        List<Selecao> selecoes = new ArrayList<>();
        String query = "SELECT s.* FROM selecao s INNER JOIN grupo_selecao sg ON s.id = sg.selecao_id WHERE sg.grupo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, grupoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Selecao selecao = new Selecao();
                    selecao.setId(rs.getInt("id"));
                    selecao.setNome(rs.getString("nome"));
                    selecao.setPaisId(rs.getInt("pais_id"));
                    selecoes.add(selecao);
                }
            }
        }
        return selecoes;
    }


    // Método para inserir uma seleção em um novo grupo
    private void inserirSelecaoEmNovoGrupo(int selecaoId, int numeroDeGrupos) {
        // Encontra o grupo com o menor número de seleções para adicionar a nova seleção
        int grupoMenosSelecoes = encontrarGrupoComMenosSelecoes(numeroDeGrupos);

        if (grupoMenosSelecoes != -1) {
            // Insere a seleção no grupo encontrado
            GrupoSelecao grupoSelecao = new GrupoSelecao();
            grupoSelecao.setGrupoId(grupoMenosSelecoes);
            grupoSelecao.setSelecaoId(selecaoId);
            inserirGrupoSelecao(grupoSelecao);
        } else {
            System.out.println("Não foi possível inserir a seleção " + selecaoId + " em nenhum grupo.");
        }
    }

    // Método auxiliar para encontrar o grupo com o menor número de seleções
    private int encontrarGrupoComMenosSelecoes(int numeroDeGrupos) {
        int grupoComMenosSelecoes = -1;
        int menorNumeroDeSelecoes = Integer.MAX_VALUE;

        // Itera sobre os grupos para encontrar o que tem menos seleções
        for (int grupoId = 1; grupoId <= numeroDeGrupos; grupoId++) {
            int numeroDeSelecoesNoGrupo = contarSelecoesNoGrupo(grupoId);
            if (numeroDeSelecoesNoGrupo < menorNumeroDeSelecoes) {
                menorNumeroDeSelecoes = numeroDeSelecoesNoGrupo;
                grupoComMenosSelecoes = grupoId;
            }
        }

        return grupoComMenosSelecoes;
    }

    // Método para contar o número de seleções em um grupo específico
    private int contarSelecoesNoGrupo(int grupoId) {
        int numeroDeSelecoes = 0;
        String query = "SELECT COUNT(*) AS total FROM grupo_selecao WHERE grupo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, grupoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                numeroDeSelecoes = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numeroDeSelecoes;
    }

    public List<GrupoSelecao> listarGruposSelecoes() {
        List<GrupoSelecao> gruposSelecoes = new ArrayList<>();
        String query = "SELECT * FROM grupo_selecao";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GrupoSelecao grupoSelecao = new GrupoSelecao();
                grupoSelecao.setId(rs.getInt("id"));
                grupoSelecao.setGrupoId(rs.getInt("grupo_id"));
                grupoSelecao.setSelecaoId(rs.getInt("selecao_id"));
                grupoSelecao.setPontos(rs.getInt("pontos"));
                grupoSelecao.setJogos(rs.getInt("jogos"));
                grupoSelecao.setVitorias(rs.getInt("vitorias"));
                grupoSelecao.setEmpates(rs.getInt("empates"));
                grupoSelecao.setDerrotas(rs.getInt("derrotas"));
                grupoSelecao.setGolsMarcados(rs.getInt("gols_marcados"));
                grupoSelecao.setGolsSofridos(rs.getInt("gols_sofridos"));
                gruposSelecoes.add(grupoSelecao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gruposSelecoes;
    }

    public void listarClassificacaoGrupos() {
        String query = "SELECT g.id AS grupo_id, g.nome AS grupo_nome, s.id AS selecao_id, s.nome AS selecao_nome, " +
                "gs.pontos, gs.jogos, gs.vitorias, gs.empates, gs.derrotas, gs.gols_marcados, gs.gols_sofridos " +
                "FROM Grupo g " +
                "JOIN grupo_selecao gs ON g.id = gs.grupo_id " +
                "JOIN Selecao s ON gs.selecao_id = s.id " +
                "ORDER BY g.id, gs.pontos DESC, (gs.gols_marcados - gs.gols_sofridos) DESC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            int currentGroupId = 0;
            System.out.println("=== Classificação dos Grupos ===");
            while (rs.next()) {
                int grupoId = rs.getInt("grupo_id");
                if (grupoId != currentGroupId) {
                    // Nova linha de grupo
                    System.out.println("\nGrupo " + rs.getString("grupo_nome"));
                    currentGroupId = grupoId;
                }
                System.out.printf("%s - Pontos: %d | Jogos: %d | Vitórias: %d | Empates: %d | Derrotas: %d | GM: %d | GS: %d\n",
                        rs.getString("selecao_nome"), rs.getInt("pontos"), rs.getInt("jogos"),
                        rs.getInt("vitorias"), rs.getInt("empates"), rs.getInt("derrotas"),
                        rs.getInt("gols_marcados"), rs.getInt("gols_sofridos"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void atualizarEstatisticasGrupo(int grupoId, int selecaoId, int golsMarcados, int golsSofridos) {
        // Consulta para obter as estatísticas atuais do grupo para a seleção específica
        String query = "SELECT * FROM grupo_selecao WHERE grupo_id = ? AND selecao_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, grupoId);
            stmt.setInt(2, selecaoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Atualiza as estatísticas do grupo com base no resultado do jogo
                int pontos = rs.getInt("pontos");
                int jogos = rs.getInt("jogos");
                int vitorias = rs.getInt("vitorias");
                int empates = rs.getInt("empates");
                int derrotas = rs.getInt("derrotas");
                int golsMarcadosAtual = rs.getInt("gols_marcados");
                int golsSofridosAtual = rs.getInt("gols_sofridos");

                jogos++;
                golsMarcadosAtual += golsMarcados;
                golsSofridosAtual += golsSofridos;

                if (golsMarcados > golsSofridos) {
                    vitorias++;
                    pontos += 3;
                } else if (golsMarcados == golsSofridos) {
                    empates++;
                    pontos += 1;
                } else {
                    derrotas++;
                }

                // Atualiza as estatísticas no banco de dados
                String updateQuery = "UPDATE grupo_selecao SET pontos = ?, jogos = ?, vitorias = ?, " +
                        "empates = ?, derrotas = ?, gols_marcados = ?, gols_sofridos = ? " +
                        "WHERE grupo_id = ? AND selecao_id = ?";
                try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                    updateStmt.setInt(1, pontos);
                    updateStmt.setInt(2, jogos);
                    updateStmt.setInt(3, vitorias);
                    updateStmt.setInt(4, empates);
                    updateStmt.setInt(5, derrotas);
                    updateStmt.setInt(6, golsMarcadosAtual);
                    updateStmt.setInt(7, golsSofridosAtual);
                    updateStmt.setInt(8, grupoId);
                    updateStmt.setInt(9, selecaoId);
                    updateStmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarGrupoSelecao(GrupoSelecao grupoSelecao) {
        String query = "UPDATE grupo_selecao SET grupo_id = ?, selecao_id = ?, pontos = ?, jogos = ?, " +
                "vitorias = ?, empates = ?, derrotas = ?, gols_marcados = ?, gols_sofridos = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, grupoSelecao.getGrupoId());
            stmt.setInt(2, grupoSelecao.getSelecaoId());
            stmt.setInt(3, grupoSelecao.getPontos());
            stmt.setInt(4, grupoSelecao.getJogos());
            stmt.setInt(5, grupoSelecao.getVitorias());
            stmt.setInt(6, grupoSelecao.getEmpates());
            stmt.setInt(7, grupoSelecao.getDerrotas());
            stmt.setInt(8, grupoSelecao.getGolsMarcados());
            stmt.setInt(9, grupoSelecao.getGolsSofridos());
            stmt.setInt(10, grupoSelecao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
