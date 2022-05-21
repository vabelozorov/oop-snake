package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Point;

import java.util.List;

public interface PointGenerator {
    Point next(List<Point> excludedPoint, int width, int height);
}
