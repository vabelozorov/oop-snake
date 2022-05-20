package ua.belozorov.snake.ingame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultSnakeTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void noSelfCollision() {
        DefaultSnake snake = SnakeData.facesRight();
        snake.turnUp();
        snake.move();
        snake.turnLeft();
        snake.move();

        assertFalse(snake.isHeadBodyCollision(), "should not self-collide");
    }

    @Test
    void isSelfCollision() {
        DefaultSnake snake = SnakeData.facesRight();
        snake.turnUp();
        snake.move();
        snake.turnLeft();
        snake.move();
        snake.turnDown();
        snake.move();

        assertTrue(snake.isHeadBodyCollision(), "should self-collide");
    }
}