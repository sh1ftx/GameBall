package Swing;

/**
 * Enumeração que representa os comandos que podem ser executados no jogo.
 */
public enum Comando {
    NEW, 
    MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN,
    MOVE_LEFT_P1(0), MOVE_RIGHT_P1(0), MOVE_UP_P1(0), MOVE_DOWN_P1(0),
    MOVE_LEFT_P2(1), MOVE_RIGHT_P2(1), MOVE_UP_P2(1), MOVE_DOWN_P2(1);

    private Integer playerID; // ID do jogador associado ao comando, se aplicável

    // Construtor para comandos gerais (sem jogador específico)
    private Comando() {
        this.playerID = null; // Comandos gerais não têm ID de jogador
    }

    // Construtor para comandos específicos de jogadores
    private Comando(Integer playerID) {
        this.playerID = playerID; // Armazena o ID do jogador associado ao comando
    }

    // Retorna o ID do jogador associado ao comando
    public Integer getPlayerID() {
        return this.playerID; 
    }

    /**
     * Retorna o comando equivalente geral se o comando atual for específico de um jogador.
     *
     * @return Comando equivalente geral
     */
    public Comando getEquivalente() {
        // Se o comando for específico para um jogador (P1 ou P2), remova a parte do jogador e encontre o comando geral
        if (this.playerID != null) {
            String baseCommand = this.name().replaceAll("_P[12]", ""); // Remove "_P1" ou "_P2"
            return Comando.valueOf(baseCommand); // Retorna o comando geral correspondente
        }
        return this; // Caso já seja um comando geral, retorna ele mesmo
    }
}
