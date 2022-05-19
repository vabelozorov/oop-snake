package ua.belozorov.snake.countdown;

import ua.belozorov.snake.core.GamePhase;
import ua.belozorov.snake.core.GamePhaseConfig;
import ua.belozorov.snake.gui.CanvasFactory;

public class CountDownConfig implements GamePhaseConfig {

    private InitialCountDownPhase phase;

    @Override
    public InitialCountDownPhase phase() {
        if (phase == null) {
            var countDownView = new CountDownView(CanvasFactory.instance());
            phase = new InitialCountDownPhase();
            phase.countdown().addListener(countDownView::display);
        }
        return phase;
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
