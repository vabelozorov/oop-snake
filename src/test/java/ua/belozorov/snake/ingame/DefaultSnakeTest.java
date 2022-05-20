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
    void noSelfCollision() {
        snake.turnUp();
        snake.move();
        snake.turnLeft();
        snake.move();

        assertFalse(snake.isHeadBodyCollision(), "should not self-collide");
    }

    @Test
    void isSelfCollision() {
        snake.turnUp();
        snake.move();
        snake.turnLeft();
        snake.move();
        snake.turnDown();
        snake.move();

        assertTrue(snake.isHeadBodyCollision(), "should self-collide");
    }

    @Test
    void growTail() {
        DefaultSnake snake = SnakeData.facesRight();
        snake.growTail();

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
}