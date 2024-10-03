package Swing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que gerencia as entradas do teclado para os comandos do jogo.
 */
public class Keyboard extends KeyAdapter {
    private Map<Integer, Comando> comandos = loadComandos(); // Mapeamento de teclas para comandos
    private Game game;

    public Keyboard(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        Comando comando = comandos.get(keyCode);
        if (comando != null) {
            game.execute(comando);
        }
    }

    private Map<Integer, Comando> loadComandos() {
        Map<Integer, Comando> map = new HashMap<>();
        map.put(KeyEvent.VK_ENTER, Comando.NEW); // Novo jogador
        map.put(KeyEvent.VK_UP, Comando.MOVE_UP_P1);
        map.put(KeyEvent.VK_DOWN, Comando.MOVE_DOWN_P1);
        map.put(KeyEvent.VK_LEFT, Comando.MOVE_LEFT_P1);
        map.put(KeyEvent.VK_RIGHT, Comando.MOVE_RIGHT_P1);
        map.put(KeyEvent.VK_W, Comando.MOVE_UP_P2);
        map.put(KeyEvent.VK_S, Comando.MOVE_DOWN_P2);
        map.put(KeyEvent.VK_A, Comando.MOVE_LEFT_P2);
        map.put(KeyEvent.VK_D, Comando.MOVE_RIGHT_P2);
        return map;
    }
}
