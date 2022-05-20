package ua.belozorov.snake.ingame;

public interface Ticker {
    void start();

    void stop();

    boolean waitPlayerAction(long ms) throws InterruptedException;
}
