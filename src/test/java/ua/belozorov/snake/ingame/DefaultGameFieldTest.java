package ua.belozorov.snake.ingame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.belozorov.snake.core.Params;
import ua.belozorov.snake.core.Point;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DefaultGameFieldTest {

    private Supplier<GameField> fieldSupplier;

    @BeforeEach
    void setUp() {
        fieldSupplier = () -> GameFieldFactory.instance().create();
    }

    @Test
    void isSnakeToBoundaryCollision_right() {
        assertSnakeCrossedBoundary(Point.xy(24, 1), Point.xy(1, 1));
    }

    @Test
    void isSnakeToBoundaryCollision_left() {
        assertSnakeCrossedBoundary(Point.xy(0, 1), Point.xy(24, 1));
    }

    @Test
    void isSnakeToBoundaryCollision_up() {
        assertSnakeCrossedBoundary(Point.xy(1, 24), Point.xy(1, 1));
    }

    @Test
    void isSnakeToBoundaryCollision_down() {
        assertSnakeCrossedBoundary(Point.xy(1, 0), Point.xy(1, 24));
    }

    private void assertSnakeCrossedBoundary(Point headPosition, Point tailPosition) {
        Params.instance()
                .initialSnakeHeadPosition(headPosition)
                .initialSnakeTailPosition(tailPosition);

        GameField gameField = fieldSupplier.get();
        assertFalse(gameField.hasSnakeCrossedBoundary(), "snake not yet crossed a boundary");

        gameField.getSnake().move();
        assertTrue(gameField.hasSnakeCrossedBoundary(), "snake expected to cross a boundary");
    }
}