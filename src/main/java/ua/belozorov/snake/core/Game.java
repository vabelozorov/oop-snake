package ua.belozorov.snake.core;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Game {
    private Thread currentThread;
    private final GamePhaseManager gamePhaseManager;

    Game(GamePhaseManager gamePhaseManager) {
        this.gamePhaseManager = gamePhaseManager;
    }

    public void run() {
        try {
            gamePhaseManager.run();
        } catch (InterruptedException e) {
            // just finish the thread execution
        } catch (Exception e) {
            System.err.println("error during game loop: " + e.getMessage());
        }
    }

    public void startNew() {
        currentThread = new Thread(this::run);
        currentThread.start();
    }

    public void restart() {
        currentThread.interrupt();
        gamePhaseManager.stop();
        gamePhaseManager.reInit();
        startNew();
    }
}
