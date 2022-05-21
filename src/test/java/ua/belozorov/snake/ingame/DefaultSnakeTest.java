package ua.belozorov.snake.ingame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.belozorov.snake.core.Point;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DefaultSnakeTest {

    private DefaultSnake snake;

    @BeforeEach
    void setUp() {
        snake = SnakeData.facesRight();
    }

    @Test
    void tryEatApple_success() {
        assertTrue(snake.tryEatApple(Point.xy(5, 1)));

        SnakeData.assertSnakeSegments(
                List.of(
                        Point.xy(5, 1),
                        Point.xy(4, 1),
                        Point.xy(3, 1),
                        Point.xy(2, 1),
                        Point.xy(1, 1),
                        Point.xy(0, 1)
                ),
                snake
        );
    }

    @Test
    void tryEatApple_no() {
        assertFalse(snake.tryEatApple(Point.xy(6, 1)));

        SnakeData.assertSnakeSegments(
                List.of(
                        Point.xy(5, 1),
                        Point.xy(4, 1),
                        Point.xy(3, 1),
                        Point.xy(2, 1),
                        Point.xy(1, 1)
                ),
                snake
        );
    }

    @Test
    void snakeSpeedsUpAfterEatenAppleThresholds() {
        long initialRestInterval = snake.restInterval();

        snake.tryEatApple(snake.head());
        assertFalse(initialRestInterval > snake.restInterval());

        snake.tryEatApple(snake.head());
        assertTrue(initialRestInterval > snake.restInterval());
    }
}