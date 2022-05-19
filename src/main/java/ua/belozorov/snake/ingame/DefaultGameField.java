package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.NotifyingObject;

import java.util.concurrent.atomic.AtomicInteger;

public class DefaultGameField extends NotifyingObject<GameField> implements GameField {

    private final int width;
    private final int height;
    private final AtomicInteger collisionHack = new AtomicInteger(0);
    private final Snake snake;

    public DefaultGameField(Snake snake) {
        width = 30;
        height = 30;
        this.snake = snake;
    }

    @Override
    public void moveSnake() {
        snake.move();
    }

    @Override
    public boolean isWallCollision() {
        return false;
    }

    @Override
    public Snake getSnake() {
        return snake;
    }

    @Override
    public long snakeRestInterval() {
        return getSnake().restInterval();
    }

}
