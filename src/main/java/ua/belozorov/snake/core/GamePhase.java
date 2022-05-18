package ua.belozorov.snake.core;

public interface GamePhase {
    void run() throws Exception;

    long delayAfterMs();

    int order();
}
