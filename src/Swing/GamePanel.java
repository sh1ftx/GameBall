package Swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

/**
 * Painel de jogo que exibe jogadores e prêmios, atualizando conforme o estado do jogo muda.
 */
public class GamePanel extends JPanel implements Observer {
    private static final long serialVersionUID = 1L;
    private Dimension dimension; // Dimensão do painel
    private GameState gameState; // Estado atual do jogo

    public GamePanel(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public Dimension getPreferredSize() {
        return dimension;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayers(g);
        drawPrizes(g);
    }

    private void drawPlayers(Graphics g) {
        List<Player> players = gameState.getPlayers();
        for (Player player : players) {
            g.setColor(player.getColor());
            g.fillOval(player.getX(), player.getY(), Player.SIZE, Player.SIZE); // Usa o tamanho definido

            // Exibe o placar do jogador
            g.setColor(Color.BLACK);
            g.drawString("Player " + player.getID() + ": " + player.getScore(), 10, 12 * (player.getID() + 1));
        }
    }

    private void drawPrizes(Graphics g) {
        List<Prize> prizes = gameState.getPrizes();
        for (Prize prize : prizes) {
            g.setColor(prize.getColor());
            g.fillOval(prize.getX(), prize.getY(), Prize.SIZE, Prize.SIZE); // Usa o tamanho definido
        }
    }

    @Override
    public void update(GameState gameState) {
        this.gameState = gameState;
        repaint();
    }
}
