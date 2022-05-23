package ua.belozorov.snake.ingame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.belozorov.snake.core.GameContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InGamePhaseTest {

    private InGamePhase phase;
    private Ticker tickerMock;
    private GameField fieldMock;
    private Snake snakeMock;
    private final GameContext gameContext = new GameContext();

    @BeforeEach
    void setUp() throws InterruptedException {
        tickerMock = mock(Ticker.class);
        fieldMock = mock(GameField.class);
        snakeMock = mock(Snake.class);

        phase = new InGamePhase(new TestGameFieldFactory(), tickerMock, 0);

        when(tickerMock.waitPlayerAction(anyLong())).thenReturn(true, false);
    }

    @Test
    void onRunTickerStartsAndSnakeTriesEatApple() throws InterruptedException {
        phase.run(gameContext);

        verify(tickerMock).start();
        verify(fieldMock).snakeTriesEatApple();
    }

    @Test
    void appleEaten() throws InterruptedException {
        when(fieldMock.snakeTriesEatApple()).thenReturn(true);

        phase.run(gameContext);

        verify(fieldMock).newApple();
        verify(fieldMock).notifyListeners();
    }

    @Test
    void noApple_validMove() throws InterruptedException {
        when(fieldMock.tryMoveSnake()).thenReturn(true);
        phase.run(gameContext);

        verify(fieldMock).notifyListeners();
        verify(tickerMock, times(2)).waitPlayerAction(anyLong());
    }

    @Test
    void noApple_gameOverMove() throws InterruptedException {
        when(snakeMock.length()).thenReturn(10);
        when(fieldMock.tryMoveSnake()).thenReturn(false);

        phase.run(gameContext);

        verify(fieldMock, times(0)).notifyListeners();
        verify(tickerMock, times(1)).waitPlayerAction(anyLong());
        assertEquals(4, gameContext.getScore());
    }

    private class TestGameFieldFactory extends GameFieldFactory {

        protected TestGameFieldFactory() {
        }

        @Override
        public GameField createField(Snake snake) {
            return fieldMock;
        }

        @Override
        public Snake createSnake() {
            return snakeMock;
        }
    }
}