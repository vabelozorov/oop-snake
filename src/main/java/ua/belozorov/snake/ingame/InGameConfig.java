package ua.belozorov.snake.ingame;

import ua.belozorov.snake.core.GamePhase;
import ua.belozorov.snake.core.GameController;
import ua.belozorov.snake.core.GamePhaseConfig;
import ua.belozorov.snake.gui.GuiFactory;

public class InGameConfig implements GamePhaseConfig {

    private DefaultGameField gameField;

    @Override
    public InGamePhase phase() {
        GameField gameField = gameField();
        var gameFieldView = new GameFieldView(GuiFactory.instance().gameCanvas());
        gameField.addListener(gameFieldView::display);
        return new InGamePhase(gameField, 700);
    }

    private DefaultGameField gameField() {
        if (gameField == null) {
            gameField = new DefaultGameField();
        }
        return gameField;
    }

    @Override
    public GameController controller() {
        return new InGameController(gameField());
    }

    @Override
    public Class<? extends GamePhase> phaseId() {
        return InGamePhase.class;
    }
}
