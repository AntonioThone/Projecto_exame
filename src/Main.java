import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import db.Conn;
import dat.*;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Conn connection = new Conn();
    static PaisOP paisOP = new PaisOP(connection);
    static CidadeOP cidadeOP = new CidadeOP(connection);
    static EstadioOP estadioOP = new EstadioOP(connection);
    static SelecaoOP selecaoOP = new SelecaoOP(connection);
    static JogadorOP jogadorOP = new JogadorOP(connection);
    static GrupoOP grupoOP = new GrupoOP(connection);
    static EstatisticasIndividuaisOP estatisticasIndividuaisOP = new EstatisticasIndividuaisOP(connection);
    static GrupoSelecaoOP grupoSelecaoOP = new GrupoSelecaoOP(connection);
    static PartidaOP partidaOP = new PartidaOP(connection);
    static SimularPartidaOP simularPartidaOP = new SimularPartidaOP(connection);

    public static void main(String[] args) throws SQLException {
        int opcao;

        do {
            exibirMenuPrincipal();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    menuGerenciarEntidades();
                    break;
                case 2:
                    menuGerenciarGrupos();
                    break;
                case 3:
                    menuGerenciarPartidas();
                    break;
                case 4:
                    menuExibirEstatisticas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== EURO CAMPEONATO ===");
        System.out.println("Bem-vindo ao sistema de gerenciamento do EURO CAMPEONATO.");
        System.out.println("Aqui você pode gerenciar todas as informações relacionadas ao campeonato europeu de seleções.");
        System.out.println("Escolha uma das opções abaixo para continuar:");
        System.out.println("1. Gerenciar Entidades");
        System.out.println("2. Gerenciar Grupos");
        System.out.println("3. Gerenciar Partidas");
        System.out.println("4. Exibir Estatísticas");
        System.out.println("0. Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    private static void menuGerenciarEntidades() {
        int opcao;

        do {
            System.out.println("\n=== Gerenciar Entidades ===");
            System.out.println("1. Criar País");
            System.out.println("2. Criar Cidade");
            System.out.println("3. Criar Estádio");
            System.out.println("4. Criar Seleção");
            System.out.println("5. Criar Jogador");
            System.out.println("6. Listar Entidades");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    criarPais();
                    break;
                case 2:
                    criarCidade();
                    break;
                case 3:
                    criarEstadio();
                    break;
                case 4:
                    criarSelecao();
                    break;
                case 5:
                    criarJogador();
                    break;
                case 6:
                    listarEntidades();
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void menuGerenciarGrupos() {
        int opcao;

        do {
            System.out.println("\n=== Gerenciar Grupos ===");
            System.out.println("1. Criar Grupo");
            System.out.println("2. Adicionar Seleção a um Grupo");
            System.out.println("3. Ver Classificação dos Grupos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    criarGrupo();
                    break;
                case 2:
                    adicionarSelecaoAGrupo();
                    break;
                case 3:
                    verClassificacaoGrupos();
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void menuGerenciarPartidas() {
        int opcao;

        do {
            System.out.println("\n=== Gerenciar Partidas ===");
            System.out.println("1. Ver Partidas");
            System.out.println("2. Realizar Partida");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    try {
                        verPartidas();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Erro ao exibir partidas.");
                    }
                    break;
                case 2:
                    try {
                        realizarPartida();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Erro ao realizar partida.");
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void menuExibirEstatisticas() {
        int opcao;

        do {
            System.out.println("\n=== Exibir Estatísticas ===");
            System.out.println("1. Listar Melhores Marcadores");
            System.out.println("2. Listar Melhores Assistentes");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("\nEscolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    try {
                        listarMelhoresMarcadores();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Erro ao listar melhores marcadores.");
                    }
                    break;
                case 2:
                    try {
                        listarMelhoresAssistentes();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Erro ao listar melhores assistentes.");
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }


    private static void criarPais() {
            System.out.println("Digite o nome do pais");
            String nome  = scanner.next();
            Pais pais  = new Pais();
            pais.setNome(nome);
            paisOP.inserirPais(pais);
            System.out.println("Inserido com sucesso");

        }

        private static void criarCidade() {
            System.out.println("Digite o nome da cidade ");
            String nome  = scanner.next();
            System.out.println("Digite o id do pais ");
            int pais_id  = scanner.nextInt();

            Cidade cidade  = new Cidade();
            cidade.setNome(nome);
            cidade.setPaisId(pais_id);
            cidadeOP.inserirCidade(cidade);
            System.out.println("Inserido com sucesso");

        }

        private static void criarEstadio() {
            System.out.println("Digite o nome do estadio ");
            String nome  = scanner.next();
            System.out.println("Digite o id da cidade ");
            int cidade_id  = scanner.nextInt();
            Estadio estadio  = new Estadio();
            estadio.setNome(nome);
            estadio.setCidade_id(cidade_id);
            estadioOP.inserirEstadio(estadio);
            System.out.println("Inserido com sucesso");
        }

        private static void criarSelecao() {
            System.out.println("Digite o nome da selecao");
            String nome  = scanner.next();
            System.out.println("Digite o id do pais");
            int pais_id  = scanner.nextInt();
            Selecao selecao  = new Selecao();
            selecao.setNome(nome);
            selecao.setPaisId(pais_id);
            selecaoOP.inserirSelecao(selecao);
            System.out.println("Inserido com sucesso");
        }

        private static void criarGrupo() {
            System.out.println("Digite o nome do grupo");
            String nome  = scanner.next();
            Grupo grupo  = new Grupo();
            grupo.setNome(nome);
            grupoOP.inserirGrupo(grupo);
            System.out.println("Inserido com sucesso");
        }

        private static void criarJogador(){
            // Solicitar os dados do jogador
            System.out.println("Digite o nome do jogador:");
            String nome = scanner.nextLine();

            System.out.println("Digite a data de nascimento do jogador (YYYY-MM-DD):");
            String dataNascimento = scanner.nextLine();

            System.out.println("Digite a posição do jogador:");
            String posicao = scanner.nextLine();

            System.out.println("Digite o ID da seleção do jogador:");
            int selecaoId = scanner.nextInt();

            // Criar um novo jogador
            Jogador jogador = new Jogador();
            jogador.setNome(nome);
            jogador.setDataNascimento(dataNascimento);
            jogador.setPosicao(posicao);
            jogador.setSelecao_id(selecaoId);

            // Inserir jogador no banco de dados
            try {
                jogadorOP.inserirJogador(jogador);
                System.out.println("Jogador inserido com sucesso!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao inserir jogador no banco de dados.");
            }
        }

        private static void adicionarSelecaoAGrupo() {

        }

        private static void verClassificacaoGrupos() {
            grupoSelecaoOP.listarClassificacaoGrupos();
        }
        private static void verPartidas() throws SQLException {
            List<Partida> partidas = partidaOP.listarPartidas();
            for (Partida partida : partidas){

                if(partida.getSelecaoCasa() != null && partida.getSelecaoFora() != null){
                    System.out.println("Partida " + partida.getId() +
                            " Resultado: " +
                            " op.Selecao Casa :" + partida.getSelecaoCasa().getNome() + partida.getGolsCasa() + " X "
                            + partida.getGolsCasa() + " op.Selecao Fora" + partida.getSelecaoFora().getNome() +
                            " Estadio:  " + partida.getEstadio());
                }
            }
        }
        private static void realizarPartida() throws SQLException {
            listarGrupos();
            System.out.println("Digite o ID do Grupo: ");
            int grupo_id = scanner.nextInt();
            simularPartidaOP.simularPartidasDoGrupo(grupo_id ,connection);
        }

        private static void listarEntidades() {
            Scanner scanner = new Scanner(System.in);
            int opcaoListar;

            do {
                System.out.println("\n=== Listar Entidades ===");
                System.out.println("1. Listar Países");
                System.out.println("2. Listar Cidades");
                System.out.println("3. Listar Estádios");
                System.out.println("4. Listar Seleções");
                System.out.println("0. Voltar ao Menu Principal");
                System.out.print("\nEscolha uma opção: ");

                opcaoListar = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                switch (opcaoListar) {
                    case 1:
                        listarPaises();
                        break;
                    case 2:
                        listarCidades();
                        break;
                    case 3:
                        listarEstadios();
                        break;
                    case 4:
                        listarSelecoes();
                        break;
                    case 0:
                        System.out.println("Voltando ao Menu Principal...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcaoListar != 0);

            scanner.close();
        }

        private static void listarPaises() {
            List<Pais> paises =  paisOP.listarPaises();
            for (Pais pais : paises){
                System.out.println("Id: "+pais.getId());
                System.out.println("Nome do Pais: "+pais.getNome());
            }
        }

        private static void listarGrupos() {
            List<Grupo> grupos =  grupoOP.listarGrupos();
            for (Grupo grupo : grupos){
                System.out.println("Id: "+grupo.getId());
                System.out.println("Nome do Pais: "+grupo.getNome());
            }
        }

        private static void listarCidades() {
            List<Cidade> cidades =  cidadeOP.listarCidades();
            for (Cidade cidade : cidades){
                System.out.println("Id: "+cidade.getId());
                System.out.println("Nome do Pais: "+cidade.getNome());
            }
        }

        private static void listarEstadios() {
            List<Estadio> estadios =  estadioOP.listarEstadios();
            for (Estadio estadio : estadios){
                System.out.println("Id: "+estadio.getId());
                System.out.println("Nome do Pais: "+estadio.getNome());
            }
        }

        private static void listarSelecoes() {
            List<Selecao> selecoes =  selecaoOP.listarSelecoes();
            for (Selecao selecao : selecoes){
                System.out.println("Id: "+selecao.getId());
                System.out.println("Nome do Pais: "+selecao.getNome());
            }
        }
        private static void listarMelhoresMarcadores() throws SQLException {
            List<EstatisticasIndividuais> estatisticasIndividuais =  estatisticasIndividuaisOP.obterMelhoresMarcadores();
            for (EstatisticasIndividuais estatisticas : estatisticasIndividuais) {
                System.out.println("Jogador: " + estatisticas.getJogador().getNome() + " - golos:  " + estatisticas.getGolos());
            }
        }
        private static void listarMelhoresAssistentes() throws SQLException {
            List<EstatisticasIndividuais> estatisticasIndividuais =  estatisticasIndividuaisOP.obterMelhoresAssistentes();
            for (EstatisticasIndividuais estatisticas : estatisticasIndividuais){
                System.out.println("Jogador: "+estatisticas.getJogador().getNome() + " - assistencias:  " + estatisticas.getGolos());
            }
        }

}
