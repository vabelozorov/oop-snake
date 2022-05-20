package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Point;

import java.util.List;

public interface Snake {
    Point tail();

    void move(int fieldWidth, int fieldHeight);

    Point head();

    void turnUp();

    void turnDown();

    void turnRight();

    void turnLeft();

    long restInterval();

    List<Point> segments();

    void tryEatApple(Point apple);
}
