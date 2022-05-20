package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.NotifyingObject;

public class DefaultGameField extends NotifyingObject<GameField> implements GameField {

    private final int width;
    private final int height;

    private final Snake snake;

    public DefaultGameField(Snake snake) {
        width = 30;
        height = 30;
        this.snake = snake;
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
