package ua.belozorov.snake.countdown;

import ua.belozorov.snake.gui.GameCanvas;
import ua.belozorov.snake.core.GameView;

public class CountDownView implements GameView<InitialCountDown> {

    private final GameCanvas canvas;

    public CountDownView(GameCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void display(InitialCountDown model) {
        int value = model.value();
        String text = value != 0 ? String.valueOf(value) : "START";

        canvas.clear();
        canvas.drawCenteredText(text);
    }

}
