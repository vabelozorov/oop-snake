package ua.belozorov.snake.ingame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.belozorov.snake.core.Point;

import java.util.List;

class DefaultSnakeTest {

    private DefaultSnake snake;

    @BeforeEach
    void setUp() {
        snake = SnakeData.facesRight();
    }


    @Test
    void tryEatApple_success() {
        snake.tryEatApple(Point.xy(5, 1));

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
        snake.tryEatApple(Point.xy(6, 1));

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
}