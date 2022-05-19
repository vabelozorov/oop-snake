package ua.belozorov.snake.countdown;

import ua.belozorov.snake.core.GameView;
import ua.belozorov.snake.gui.CanvasFactory;
import ua.belozorov.snake.gui.GameCanvas;

public class CountDownView implements GameView<InitialCountdown> {

    private final CanvasFactory canvasFactory;

    public CountDownView(CanvasFactory canvasFactory) {
        this.canvasFactory = canvasFactory;
    }

    @Override
    public void display(InitialCountdown model) {
        int value = model.value();
        String text = value != 0 ? String.valueOf(value) : "START";

        GameCanvas gameCanvas = canvasFactory.gameCanvas();
        gameCanvas.clear();
        gameCanvas.drawCenteredText(text);
    }
}
