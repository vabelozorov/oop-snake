package ua.belozorov.snake.gui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.belozorov.snake.countdown.CountDownView;
import ua.belozorov.snake.countdown.InitialCountdown;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CountDownViewTest {

    private GameCanvas canvasMock;
    private CountDownView view;

    @BeforeEach
    void setUp() {
        canvasMock = mock(GameCanvas.class);

        view = new CountDownView(() -> canvasMock);
    }

    @Test
    void countAboveZero_displayCounter() {
        InitialCountdown countDown = new InitialCountdown();
        countDown.countDown();

        view.display(countDown);

        verify(canvasMock).drawCenteredText("3");
    }

    @Test
    void countZero_displayStart() {
        InitialCountdown countDown = new InitialCountdown();
        countDown.countDown();
        countDown.countDown();
        countDown.countDown();
        countDown.countDown();

        view.display(countDown);

        verify(canvasMock).drawCenteredText("START");
    }
}