package ua.belozorov.snake.gameover;

import ua.belozorov.snake.gui.CanvasFactory;
import ua.belozorov.snake.gui.GameCanvas;
import ua.belozorov.snake.core.GameView;

public class GameOverView implements GameView<GameOver> {
    private final CanvasFactory canvasFactory;

    public GameOverView(CanvasFactory canvasFactory) {
        this.canvasFactory = canvasFactory;
    }

    @Override
    public void display(GameOver model) {
        GameCanvas canvas = canvasFactory.gameCanvas();
        canvas.clear();
        canvas.drawCenteredText("Game Over");
    }
}
