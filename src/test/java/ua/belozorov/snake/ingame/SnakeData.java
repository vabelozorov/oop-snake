package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.Point;

import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeData {

    public static void assertSnakeSegments(List<Point> expected, Snake snake) {
        assertEquals(expected, snake.segments());
    }

    public static void assertSnakeMoved(List<Point> expected, Snake snake) {
        snake.move(20,20 );

        assertSnakeSegments(expected, snake);
    }

    public static void assertSnakeTurned(List<Point> expected, Snake snake, Consumer<Snake> turnFn) {
        turnFn.accept(snake);
        snake.move(20,20 );

        assertSnakeSegments(expected, snake);
    }

    public static DefaultSnake facesRight() {
        return new DefaultSnake(0, Point.xy(5, 1), Point.xy(1, 1));
    }

    public static DefaultSnake facesLeft() {
        return new DefaultSnake(0, Point.xy(1, 1), Point.xy(5, 1));
    }

    public static DefaultSnake facesUp() {
        return new DefaultSnake(0, Point.xy(1, 5), Point.xy(1, 1));
    }

    public static DefaultSnake facesDown() {
        return new DefaultSnake(0, Point.xy(1, 1), Point.xy(1, 5));
    }
}
