package ua.belozorov.snake.ingame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.belozorov.snake.core.Params;
import ua.belozorov.snake.core.Point;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultSnakeFieldBoundaryTest {

    private GameFieldFactory factory;

    @BeforeEach
    void setUp() {
        factory = new GameFieldFactory() {
            @Override
            public PointGenerator appleGenerator() {
                return new FixedPointGenerator(Point.xy(7, 1));
            }
        };
    }

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
        Params.instance()
                .initialSnakeHeadPosition(headPosition)
                .initialSnakeTailPosition(tailPosition);

        Snake snake = factory.createSnake();
        assertDoesNotThrow(() -> snake.move(26, 26));

        assertThrows(InvalidSnakeMoveException.class, () -> snake.move(26, 26));
    }

    @Test
    void noSelfCollision() {
        Params.instance()
                .initialSnakeHeadPosition(Point.xy(5,1))
                .initialSnakeTailPosition(Point.xy(1,1));

        Snake snake = factory.createSnake();

        snake.turnUp();
        snake.move(20, 20);
        snake.turnLeft();
        snake.move(20, 20);

        assertDoesNotThrow(() -> snake.move(20, 20));
    }

    @Test
    void isSelfCollision() {
        Params.instance()
                .initialSnakeHeadPosition(Point.xy(5,1))
                .initialSnakeTailPosition(Point.xy(1,1));

        Snake snake = factory.createSnake();

        snake.turnUp();
        snake.move(20, 20);
        snake.turnLeft();
        snake.move(20, 20);
        snake.turnDown();

        assertThrows(InvalidSnakeMoveException.class, () -> snake.move(20, 20));
    }
}