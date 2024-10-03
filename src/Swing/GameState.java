package Swing;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que mantém o estado do jogo, incluindo jogadores e prêmios.
 */
public class GameState {
    private List<Player> players = new ArrayList<>(); // Lista de jogadores
    private List<Prize> prizes = new ArrayList<>();   // Lista de prêmios

    public List<Player> getPlayers() {
        return this.players;
    }

    public List<Prize> getPrizes() {
        return prizes;
    }

    // Adiciona um jogador ao estado do jogo
    public void addPlayer(Player player) {
        players.add(player);
    }

    // Adiciona um prêmio ao estado do jogo
    public void addPrize(Prize prize) {
        prizes.add(prize);
    }
}
