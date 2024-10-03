package Swing;

import java.awt.Color;

/**
 * Classe que representa um prêmio no jogo, que pode ser coletado pelos jogadores.
 */
public class Prize extends GameElement {
    public Prize(int ID, int x, int y, Color color) {
        super(ID, x, y, color);
    }

    // Método para reposicionar o prêmio
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
