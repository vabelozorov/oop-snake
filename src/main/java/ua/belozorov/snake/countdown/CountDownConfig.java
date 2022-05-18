package ua.belozorov.snake.countdown;

import ua.belozorov.snake.core.GamePhase;
import ua.belozorov.snake.core.GamePhaseConfig;
import ua.belozorov.snake.gui.GuiFactory;

public class CountDownConfig implements GamePhaseConfig {

    @Override
    public InitialCountDownPhase phase() {
        InitialCountDown countDown = new InitialCountDown();
        var countDownView = new CountDownView(GuiFactory.instance().gameCanvas());
        countDown.addListener(countDownView::display);
        return new InitialCountDownPhase(countDown);
    }

    @Override
    public CountDownController controller() {
        return new CountDownController();
    }

    @Override
    public Class<? extends GamePhase> phaseId() {
        return InitialCountDownPhase.class;
    }
}
