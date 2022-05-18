package ua.belozorov.snake.gameover;

import ua.belozorov.snake.gui.GameCanvas;
import ua.belozorov.snake.core.GameView;

public class GameOverView implements GameView<GameOver> {
    private final GameCanvas canvas;

    public GameOverView(GameCanvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void display(GameOver model) {
        canvas.clear();
        canvas.drawCenteredText("Game Over");
    }
}
