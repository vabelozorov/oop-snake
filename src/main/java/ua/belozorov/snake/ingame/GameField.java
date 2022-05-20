package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Notifying;

public interface GameField extends Notifying<GameField> {

    Snake getSnake();

    long snakeRestInterval();

    boolean hasSnakeCrossedBoundary();
}
