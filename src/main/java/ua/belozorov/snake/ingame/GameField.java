package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Notifying;
import ua.belozorov.snake.core.Point;

public interface GameField extends Notifying<GameField> {

    Snake getSnake();

    long snakeRestInterval();

    boolean hasSnakeCrossedBoundary();

    Point getApple();

    boolean isAppleEaten();
}
