package ua.belozorov.snake.ingame;

import ua.belozorov.snake.gui.GameCanvas;
import ua.belozorov.snake.core.GameView;

public class GameFieldView implements GameView<GameField> {
    private final GameCanvas canvas;

    public GameFieldView(GameCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void display(GameField model) {
        canvas.clear();
        canvas.drawCenteredText("Ya Snake");
    }
}
