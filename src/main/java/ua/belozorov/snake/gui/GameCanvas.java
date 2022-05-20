package ua.belozorov.snake.gui;

import ua.belozorov.snake.core.Point;

public interface GameCanvas {
    void drawCenteredText(String s);

    void clear();

    void drawSnakeSegment(Point segment);

    void drawApple(Point point);
}
