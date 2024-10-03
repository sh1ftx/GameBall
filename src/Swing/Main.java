package Swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Classe principal que inicia o aplicativo.
 */
public class Main {
    public static void main(String[] args) {
        JFrame frontView = new JFrame("Advanced Swing App");
        frontView.setResizable(false);
        frontView.setAlwaysOnTop(true);
        frontView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frontView.setLayout(new BorderLayout());

        Dimension dimensions = new Dimension(600, 600); // Aumentando o tamanho da janela
        Game game = new Game(dimensions);
        GamePanel gamePanel = new GamePanel(dimensions);
        
        game.subscribe(gamePanel);
        
        frontView.add(gamePanel);
        frontView.pack();
        frontView.setLocationRelativeTo(null);
        frontView.setVisible(true);
        
        frontView.addKeyListener(new Keyboard(game));
    }
}
