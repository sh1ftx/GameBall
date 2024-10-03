package Swing;

import java.awt.Color;

/**
 * Classe abstrata que representa um elemento do jogo.
 * Possui propriedades comuns como posição, cor e ID.
 */
public abstract class GameElement {
    
    // Tamanho padrão dos elementos no jogo
    public static final int SIZE = 20; // Aumentado para melhorar a visualização
    
    protected int x, y; // Posições x e y
    private Color color; // Cor do elemento
    protected final int ID; // Identificador único para cada elemento

    /**
     * Construtor para inicializar o GameElement.
     *
     * @param ID    Identificador único do elemento
     * @param x     Coordenada x
     * @param y     Coordenada y
     * @param color Cor do elemento
     */
    protected GameElement(int ID, int x, int y, Color color) {
        this.ID = ID;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    // Métodos getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    // Método para atualizar a cor do elemento
    public void setColor(Color color) {
        this.color = color;
    }

    public int getID() {
        return ID;
    }
}
