package ua.belozorov.snake.gui;

import ua.belozorov.snake.core.Point;

public interface GameCanvas {
    void drawCenteredText(String s);

    void drawText(String s, TextDrawSpec spec);

    void clear();

    void drawSnakeSegment(Point segment);

    void drawApple(Point point);

    LineLayout getTextBounds(String s, FontSpec fontSpec);

    Rectangle getDimensions();

}
