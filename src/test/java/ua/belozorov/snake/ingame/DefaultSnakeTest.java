package ua.belozorov.snake.ingame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
}