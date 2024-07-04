-- Tabela Pais
CREATE TABLE pais (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL
);

-- Tabela Cidade
CREATE TABLE cidade (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
pais_id INT,
FOREIGN KEY (pais_id) REFERENCES pais(id)
);

-- Tabela Estadio
CREATE TABLE estadio (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
cidade_id INT,
FOREIGN KEY (cidade_id) REFERENCES cidade(id)
);

-- Tabela Grupo
CREATE TABLE grupo (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
);

-- Tabela dat.Selecao
CREATE TABLE selecao (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
pais_id INT,
FOREIGN KEY (pais_id) REFERENCES pais(id),
);

-- Tabela Grupo_Selecao (relacionamento muitos-para-muitos entre Grupo e dat.Selecao)
CREATE TABLE grupo_selecao (
id INT PRIMARY KEY AUTO_INCREMENT,
grupo_id INT,
selecao_id INT,
jogos INT DEFAULT 0,
vitorias INT DEFAULT 0,
empates INT DEFAULT 0,
derrotas INT DEFAULT 0,
pontos INT DEFAULT 0,
gols_marcados INT DEFAULT 0,
gols_sofridos INT DEFAULT 0,
FOREIGN KEY (grupo_id) REFERENCES grupo(id),
FOREIGN KEY (selecao_id) REFERENCES selecao(id)
);

-- Tabela Jogador
CREATE TABLE jogador (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
data_nascimento DATE,
posicao VARCHAR(100),
selecao_id INT,
FOREIGN KEY (selecao_id) REFERENCES selecao(id)
);

-- Tabela Partida
CREATE TABLE partida (
id INT PRIMARY KEY AUTO_INCREMENT,
data DATE,
estadio_id INT,
selecao_casa_id INT,
selecao_fora_id INT,
gols_casa INT,
gols_fora INT,
FOREIGN KEY (estadio_id) REFERENCES estadio(id),
FOREIGN KEY (selecao_casa_id) REFERENCES selecao(id),
FOREIGN KEY (selecao_fora_id) REFERENCES selecao(id)
);
-- Tabela Estatisticas_Individuais
CREATE TABLE estatisticas_individuais (
jogador_id INT PRIMARY KEY,
golos INT DEFAULT 0,
remates INT DEFAULT 0,
fora_jogos INT DEFAULT 0,
faltas INT DEFAULT 0,
assistencias INT DEFAULT 0,
passes INT DEFAULT 0,
partida_id INT,
FOREIGN KEY (jogador_id) REFERENCES jogador(id),
FOREIGN KEY (partida_id) REFERENCES partida(id)
);
