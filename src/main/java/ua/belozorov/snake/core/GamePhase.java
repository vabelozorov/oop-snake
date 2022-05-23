package ua.belozorov.snake.core;

public interface GamePhase {
    void reInit();

    void run(GameContext gameContext) throws Exception;

    default void stop() {}

    long delayAfterMs();

    int order();
}
