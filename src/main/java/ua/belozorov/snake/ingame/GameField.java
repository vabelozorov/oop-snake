package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Notifying;
import ua.belozorov.snake.core.Point;

public interface GameField extends Notifying<GameField> {

    Snake getSnake();

    Point getApple();

    int getWidth();

    int getHeight();

    void newApple();
}
