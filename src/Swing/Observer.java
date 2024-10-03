package Swing;

/**
 * Interface que define um observador que é atualizado com o novo estado do jogo.
 */
public interface Observer {
    void update(GameState gameState);
}
