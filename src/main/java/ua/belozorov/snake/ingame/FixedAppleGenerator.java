package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Point;

public class FixedAppleGenerator implements AppleGenerator {
    @Override
    public Point next() {
        return Point.xy(10, 10);
    }
}
