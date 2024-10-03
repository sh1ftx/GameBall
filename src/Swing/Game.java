package Swing;

import java.awt.Color;
import java.awt.Dimension;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal que representa a lógica do jogo e gerencia jogadores e prêmios.
 */
public class Game {
    private final List<Observer> observers = new ArrayList<>(); // Lista de observadores
    private final GameState gameState = new GameState(); // Estado atual do jogo
    private final Dimension dimensao; // Dimensões da área de jogo
    private final GameEngine gameEngine; // Instância do motor do jogo

    // Construtor que inicializa as dimensões do jogo e o motor do jogo
    public Game(Dimension dimension) {
        this.dimensao = dimension;
        this.gameEngine = new GameEngine(dimension);
    }

    /**
     * Retorna um jogador com base no seu ID.
     *
     * @param idPlayer ID do jogador
     * @return Player correspondente ao ID
     */
    public Player getPlayer(int idPlayer) {
        return gameState.getPlayers().get(idPlayer);
    }

    /**
     * Adiciona um novo jogador ao jogo, se houver menos de dois jogadores presentes.
     */
    public void newPlayer() {
        if (gameState.getPlayers().size() < 2) {
            // Cria um jogador na posição (0, 0) com a cor inicial azul
            Player player = new Player(gameState.getPlayers().size(), 0, 0, Color.BLUE);
            gameState.addPlayer(player); // Uso do método para adicionar jogador
        }
    }

    /**
     * Adiciona um novo prêmio ao jogo, se nenhum prêmio já estiver presente.
     */
    public void newPrize() {
        if (gameState.getPrizes().isEmpty()) {
            SecureRandom rand = new SecureRandom();
            // Gera posições aleatórias para o prêmio dentro dos limites do jogo
            int xPrize = rand.nextInt(dimensao.width / Prize.SIZE) * Prize.SIZE;
            int yPrize = rand.nextInt(dimensao.height / Prize.SIZE) * Prize.SIZE;
            // Cria o prêmio com a cor rosa
            Prize prize = new Prize(gameState.getPrizes().size(), xPrize, yPrize, Color.PINK);
            gameState.addPrize(prize); // Uso do método para adicionar prêmio
        }
    }

    /**
     * Executa um comando dado, seja para criar um novo jogador/prêmio ou movimentar um jogador.
     *
     * @param comando Comando a ser executado
     */
    public void execute(Comando comando) {
        if (comando == Comando.NEW) {
            newPlayer(); // Cria um novo jogador
            newPrize(); // Cria um novo prêmio
        } else {
            Player player = getPlayer(comando.getPlayerID()); // Obtém o jogador correspondente
            gameEngine.executeCommand(player, comando); // Executa o movimento
            Prize prize = gameEngine.checkCollision(player, gameState.getPrizes()); // Verifica colisão

            if (prize != null) {
                gameEngine.repositionPrize(prize); // Reposiciona o prêmio em caso de colisão
            }
        }
        updateAll(); // Atualiza o estado para todos os observadores
    }

    /**
     * Inscreve um observador para ser notificado sobre mudanças no estado do jogo.
     *
     * @param observer Observador a ser inscrito
     */
    public void subscribe(Observer observer) {
        observers.add(observer);
        observer.update(gameState); // Notifica imediatamente o observador
    }

    /**
     * Remove um observador da lista de inscritos.
     *
     * @param observer Observador a ser removido
     */
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifica todos os observadores com o estado atual do jogo.
     */
    private void updateAll() {
        observers.forEach(observer -> observer.update(gameState));
    }
}
