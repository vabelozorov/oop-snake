package ua.belozorov.snake.ingame;

import org.junit.jupiter.api.Test;
import ua.belozorov.snake.core.Point;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DefaultSnakeFieldBoundaryTest {

    @Test
    void isSnakeToBoundaryCollision_right() {
        assertSnakeCrossedBoundary(Point.xy(24, 1), Point.xy(1, 1));
    }

    @Test
    void isSnakeToBoundaryCollision_left() {
        assertSnakeCrossedBoundary(Point.xy(1, 1), Point.xy(24, 1));
    }

    @Test
    void isSnakeToBoundaryCollision_up() {
        assertSnakeCrossedBoundary(Point.xy(1, 24), Point.xy(1, 1));
    }

    @Test
    void isSnakeToBoundaryCollision_down() {
        assertSnakeCrossedBoundary(Point.xy(1, 1), Point.xy(1, 24));
    }

    private void assertSnakeCrossedBoundary(Point headPosition, Point tailPosition) {
         Snake snake = SnakeData.atCoordinates(headPosition, tailPosition);
        assertTrue(snake.tryMove(26, 26));

        assertFalse(snake.tryMove(26, 26));
    }

    @Test
    void noSelfCollision() {
        Snake snake = SnakeData.atCoordinates(Point.xy(5,1), Point.xy(1,1));

        snake.turnUp();
        snake.tryMove(20, 20);
        snake.turnLeft();
        snake.tryMove(20, 20);

        assertTrue(snake.tryMove(20, 20));
    }

    @Test
    void isSelfCollision() {
        Snake snake = SnakeData.atCoordinates(Point.xy(5,1), Point.xy(1,1));

        snake.turnUp();
        snake.tryMove(20, 20);
        snake.turnLeft();
        snake.tryMove(20, 20);
        snake.turnDown();

        assertFalse(snake.tryMove(20, 20));
    }
}