package Swing;

import java.awt.Color;
import java.awt.Dimension;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Classe que gerencia a lógica do jogo, incluindo a movimentação dos jogadores e verificação de colisões.
 */
public class GameEngine {
    private Dimension dimension; // Dimensão do campo de jogo
    private Map<Comando, BiConsumer<Player, Dimension>> strategies = loadStrategies(); // Estratégias de movimento

    public GameEngine(Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Executa o comando de movimento para o jogador.
     *
     * @param player  Jogador a ser movido
     * @param comando Comando a ser executado
     */
    public void executeCommand(Player player, Comando comando) {
        strategies.get(comando.getEquivalente()).accept(player, dimension);
        updatePlayerColor(player); // Atualiza a cor do jogador
    }

    /**
     * Verifica se houve colisão entre o jogador e um prêmio.
     *
     * @param player Jogador a ser verificado
     * @param prizes Lista de prêmios
     * @return O prêmio colidido ou null se não houver colisão
     */
    public Prize checkCollision(Player player, List<Prize> prizes) {
        return prizes.stream()
                .filter(prize -> player.getX() == prize.getX() && player.getY() == prize.getY())
                .findFirst()
                .map(prize -> {
                    player.scored();
                    return prize;
                })
                .orElse(null);
    }

    /**
     * Reposiciona um prêmio em uma nova posição aleatória.
     *
     * @param prize Prêmio a ser reposicionado
     */
    public void repositionPrize(Prize prize) {
        SecureRandom rand = new SecureRandom();
        prize.setPosition(rand.nextInt(dimension.width / Prize.SIZE) * Prize.SIZE,
                          rand.nextInt(dimension.height / Prize.SIZE) * Prize.SIZE);
    }

    /**
     * Atualiza a cor do jogador com base em sua posição no campo.
     *
     * @param player Jogador a ter a cor atualizada
     */
    private void updatePlayerColor(Player player) {
        int halfWidth = dimension.width / 2;
        int halfHeight = dimension.height / 2;

        if (player.getY() > halfHeight) {
            player.setColor(player.getX() < halfWidth ? Color.BLUE : Color.RED); // Quadrantes inferiores
        } else {
            player.setColor(player.getX() < halfWidth ? Color.GREEN : Color.ORANGE); // Quadrantes superiores
        }
    }

    /**
     * Carrega as estratégias de movimento para cada comando.
     *
     * @return Mapa de comandos e suas respectivas ações
     */
    private Map<Comando, BiConsumer<Player, Dimension>> loadStrategies() {
        Map<Comando, BiConsumer<Player, Dimension>> map = new HashMap<>();

        map.put(Comando.MOVE_LEFT, (player, dimension) -> movePlayer(player, -Player.SIZE, 0));
        map.put(Comando.MOVE_RIGHT, (player, dimension) -> movePlayer(player, Player.SIZE, 0));
        map.put(Comando.MOVE_UP, (player, dimension) -> movePlayer(player, 0, -Player.SIZE));
        map.put(Comando.MOVE_DOWN, (player, dimension) -> movePlayer(player, 0, Player.SIZE));

        return map;
    }

    /**
     * Move o jogador dentro dos limites do campo.
     *
     * @param player Jogador a ser movido
     * @param dx     Mudança na posição x
     * @param dy     Mudança na posição y
     */
    private void movePlayer(Player player, int dx, int dy) {
        int newX = player.getX() + dx;
        int newY = player.getY() + dy;

        if (newX >= 0 && newX + Player.SIZE <= dimension.getWidth()) {
            player.horizontalMove(dx);
        }
        if (newY >= 0 && newY + Player.SIZE <= dimension.getHeight()) {
            player.verticalMove(dy);
        }
    }
}
