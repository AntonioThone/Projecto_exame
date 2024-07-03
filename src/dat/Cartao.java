package dat;

public class Cartao {
    private int id;
    private int partida_id;
    private int selecao_id;
    private int jogador_id;
    private String cor;
    private int tempo;
    private Jogador jogador;
    private Selecao selecao;


    public int getId() {
        return id;
    }
    public int getJogador_id() {
        return jogador_id;
    }

    public int getPartida_id() {
        return partida_id;
    }
    public int getSelecao_id() {
        return selecao_id;
    }
    public void setJogador_id(int jogador_id) {
        this.jogador_id = jogador_id;
    }
    public void setPartida_id(int partida_id) {
        this.partida_id = partida_id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setSelecao_id(int selecao_id) {
        this.selecao_id = selecao_id;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setSelecao(Selecao selecao) {
        this.selecao = selecao;
    }

    public Selecao getSelecao() {
        return selecao;
    }
}

