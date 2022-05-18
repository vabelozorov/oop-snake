package ua.belozorov.snake.core;

public class GameLoop extends Thread {

    private final GamePhases gamePhases;

    public GameLoop(GamePhases gamePhases) {
        this.gamePhases = gamePhases;
    }

    public void run() {
        try {
            gamePhases.run();
        } catch (InterruptedException e) {
            // just finish the thread execution
        } catch (Exception e) {
            System.err.println("error during game loop: " + e.getMessage());
        }
    }
}
