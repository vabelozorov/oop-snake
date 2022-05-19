package ua.belozorov.snake.ingame;

import ua.belozorov.snake.gui.CanvasFactory;
import ua.belozorov.snake.gui.GameCanvas;
import ua.belozorov.snake.core.GameView;

public class GameFieldView implements GameView<GameField> {
    private final CanvasFactory canvasFactory;

    public GameFieldView(CanvasFactory canvasFactory) {
        this.canvasFactory = canvasFactory;
    }

    @Override
    public void display(GameField model) {
        GameCanvas canvas = canvasFactory.gameCanvas();

        canvas.clear();
        model.getSnake().segments()
                .forEach(canvas::drawSnakeSegment);
    }
}
