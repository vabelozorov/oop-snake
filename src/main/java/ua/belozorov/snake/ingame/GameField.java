package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Notifying;

public interface GameField extends Notifying<GameField> {

    boolean isWallCollision();

    Snake getSnake();

    long snakeRestInterval();
}
