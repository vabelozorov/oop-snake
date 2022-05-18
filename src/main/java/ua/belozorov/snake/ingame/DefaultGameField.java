package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.NotifyingObject;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultGameField extends NotifyingObject<GameField> implements GameField {

    private final int width;
    private final int height;
    private final AtomicInteger collisionHack = new AtomicInteger(0);
    private final Snake snake;

    public DefaultGameField() {
        width = 30;
        height = 30;
        snake = new DefaultSnake();
    }

    @Override
    public void moveSnake() {
        snake.move();
    }

    @Override
    public boolean isWallCollision() {
        return collisionHack.incrementAndGet() > 2;
    }

    @Override
    public Snake getSnake() {
        return snake;
    }

}
