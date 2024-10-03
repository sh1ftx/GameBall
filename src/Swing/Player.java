package Swing;

import java.awt.Color;

/**
 * Classe que representa um jogador no jogo, incluindo sua pontuação e movimento.
 */
public class Player extends GameElement {
    private int score; // Pontuação do jogador

    public Player(int ID, int x, int y, Color color) {
        super(ID, x, y, color);
        this.score = 0; // Inicializa a pontuação em zero
    }
    
    public void horizontalMove(int xPos) {
        this.x += xPos;
    }
    
    public void verticalMove(int yPos) {
        this.y += yPos;
    }

    public int getScore() {
        return this.score;
    }

    public void scored() {
        this.score++;
    }

    // Método para definir a posição
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
